package com.kma.movies.ui.sign_in

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kma.movies.R
import com.kma.movies.common.utils.gone
import com.kma.movies.common.utils.invisible
import com.kma.movies.common.utils.setAnchorId
import com.kma.movies.common.utils.visible
import com.kma.movies.data.Resource
import com.kma.movies.data.sources.remote.CommonSharedPreferences
import com.kma.movies.databinding.FragmentSignInBinding
import com.kma.movies.model.LoginResponse
import com.kma.movies.model.UserLogin
import com.kma.movies.ui.sign_in.viewmodel.LoginViewModel
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
            Snackbar.make(binding.containedButton, "Please enter email", Snackbar.LENGTH_LONG)
                .setAnchorId(R.id.bottom_navigation).show()
            return false
        }
        if (!isValidEmail(binding.textEmail.editText?.text?.toString())) {
            Snackbar.make(binding.containedButton, "Please enter valid email ", Snackbar.LENGTH_LONG)
                .setAnchorId(R.id.bottom_navigation).show()
            return false
        }

        if (binding.password.editText?.text?.toString()?.isBlank() == true) {
            Snackbar.make(binding.containedButton, "Please enter password", Snackbar.LENGTH_LONG)
                .setAnchorId(R.id.bottom_navigation).show()
            return false

        }
        return true
    }
    fun isValidEmail(target: CharSequence?): Boolean {
        return if (TextUtils.isEmpty(target)) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
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