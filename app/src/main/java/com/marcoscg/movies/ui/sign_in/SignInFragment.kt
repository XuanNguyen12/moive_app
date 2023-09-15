package com.marcoscg.movies.ui.sign_in

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.marcoscg.movies.R
import com.marcoscg.movies.common.utils.gone
import com.marcoscg.movies.common.utils.invisible
import com.marcoscg.movies.common.utils.setAnchorId
import com.marcoscg.movies.common.utils.visible
import com.marcoscg.movies.data.sources.remote.CommonSharedPreferences
import com.marcoscg.movies.data.Resource
import com.marcoscg.movies.databinding.FragmentSignInBinding
import com.marcoscg.movies.model.LoginResponse
import com.marcoscg.movies.model.UserLogin
import com.marcoscg.movies.ui.sign_in.viewmodel.LoginViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber


class SignInFragment : Fragment(R.layout.fragment_sign_in) {
    private val loginViewModel: LoginViewModel by sharedViewModel()
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signUp.setOnClickListener {
            val action = SignInFragmentDirections.navigateToSignUp()
            findNavController().navigate(action)
        }
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAction()
        initData()
    }

    private fun initView() {
    }

    private fun initAction() {
        binding.containedButton.setOnClickListener {
            if (checkDataRegister()) {
                kotlin.runCatching {
                    loginViewModel.register(
                        UserLogin(
                            binding.textEmail.editText!!.text.toString(),
                            binding.password.editText!!.text.toString()
                        )
                    )
                }.onFailure {
                    binding.progress.gone()
                    binding.containedButton.visible()
                    Snackbar.make(binding.containedButton, "error: " + it.message, Snackbar.LENGTH_LONG)
                        .setAnchorId(R.id.bottom_navigation).show()
                }
            }
        }
    }

    private fun initData() {
        viewLifecycleOwner.lifecycleScope.launch {
            loginViewModel.userSignUpState.collect {
                handleFavoriteMoviesDataState(it)
            }
        }
    }
    private fun handleFavoriteMoviesDataState(state: Resource<LoginResponse>) {
        when (state.status) {
            Resource.Status.LOADING -> {
                binding.progress.visible()
                binding.containedButton.invisible()
            }
            Resource.Status.SUCCESS -> {
                Snackbar.make(binding.containedButton, "Success", Snackbar.LENGTH_LONG)
                    .setAnchorId(R.id.bottom_navigation).show()
                findNavController().popBackStack()
                state.data?.accessToken?.let { CommonSharedPreferences.getInstance().setTokenUser(it) }
            }
            Resource.Status.ERROR -> {
                binding.progress.invisible()
                binding.containedButton.visible()
                Snackbar.make(binding.containedButton, getString(R.string.error_message_pattern, state.message), Snackbar.LENGTH_LONG)
                    .setAnchorId(R.id.bottom_navigation).show()
            }
            Resource.Status.EMPTY -> {
                Timber.d("Empty state.")
            }
        }
    }
    private fun checkDataRegister(): Boolean {
        if (binding.textEmail.editText?.text?.toString()?.isBlank() == true) {
            Toast.makeText(activity, "Please enter email", Toast.LENGTH_SHORT).show()
            return false
        }

        if (binding.password.editText?.text?.toString()?.isBlank() == true) {
            Toast.makeText(activity, "Please enter password", Toast.LENGTH_SHORT).show()
            return false

        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        loginViewModel.disposable?.dispose()
    }
    companion object {
        fun newInstance(): SignInFragment {
            val fragment = SignInFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}