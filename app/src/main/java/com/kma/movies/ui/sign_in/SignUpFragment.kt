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
import com.kma.movies.databinding.FragmentSignUpBinding
import com.kma.movies.model.RegisterStatus
import com.kma.movies.model.UserRegister
import com.kma.movies.ui.sign_in.viewmodel.SignUpViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val signUpViewModel:
            SignUpViewModel by sharedViewModel()
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
                binding.containedButton.invisible()
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
            Snackbar.make(binding.containedButton, "Please enter a user name", Snackbar.LENGTH_LONG)
                .setAnchorId(R.id.bottom_navigation).show()
            return false
        }
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
    private fun isValidEmail(target: CharSequence?): Boolean {
        return if (TextUtils.isEmpty(target)) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
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