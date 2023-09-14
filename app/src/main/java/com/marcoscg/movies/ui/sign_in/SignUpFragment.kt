package com.marcoscg.movies.ui.sign_in

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.marcoscg.movies.R
import com.marcoscg.movies.common.utils.gone
import com.marcoscg.movies.common.utils.setAnchorId
import com.marcoscg.movies.common.utils.visible
import com.marcoscg.movies.data.Resource
import com.marcoscg.movies.databinding.FragmentAccountBinding
import com.marcoscg.movies.databinding.FragmentSignInBinding
import com.marcoscg.movies.databinding.FragmentSignUpBinding
import com.marcoscg.movies.model.Movie
import com.marcoscg.movies.model.RegisterStatus
import com.marcoscg.movies.model.UserRegister
import com.marcoscg.movies.ui.home.AccountFragment
import com.marcoscg.movies.ui.sign_in.viewmodel.SignUpViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val signUpViewModel: SignUpViewModel by sharedViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
                    signUpViewModel.register(
                        UserRegister(
                            binding.userName.editText!!.text.toString(),
                            binding.textEmail.editText!!.text.toString(),
                            binding.password.editText!!.text.toString()
                        )
                    )
                }.onFailure {
                    binding.progress.gone()
                    binding.containedButton.visible()
                    Snackbar.make(binding.containedButton, "error: " + it.message, Snackbar.LENGTH_LONG)
                        .setAnchorId(R.id.bottom_navigation).show()                }
            }
        }
    }

    private fun initData() {
        viewLifecycleOwner.lifecycleScope.launch {
            signUpViewModel.userSignUpState.collect {
                handleFavoriteMoviesDataState(it)
            }
        }
    }
    private fun handleFavoriteMoviesDataState(state: Resource<RegisterStatus>) {
        when (state.status) {
            Resource.Status.LOADING -> {
                binding.progress.visible()
                binding.containedButton.gone()
            }
            Resource.Status.SUCCESS -> {
                Snackbar.make(binding.containedButton, "Success", Snackbar.LENGTH_LONG)
                    .setAnchorId(R.id.bottom_navigation).show()
                findNavController().popBackStack()

            }
            Resource.Status.ERROR -> {
                binding.progress.gone()
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
        if (binding.userName.editText?.text?.toString()?.isBlank() == true) {
            Toast.makeText(activity, "Please enter a user name", Toast.LENGTH_SHORT).show()
            return false
        }
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
        signUpViewModel.disposable?.dispose()
    }
    companion object {
        fun newInstance(): SignUpFragment {
            val fragment = SignUpFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}