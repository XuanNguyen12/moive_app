package com.marcoscg.movies.ui.sign_in

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.marcoscg.movies.R
import com.marcoscg.movies.databinding.FragmentAccountBinding
import com.marcoscg.movies.databinding.FragmentSignInBinding
import com.marcoscg.movies.ui.home.AccountFragment
import com.marcoscg.movies.ui.home.AccountFragmentDirections

/**
 * A simple [Fragment] subclass.
 * Use the [SignInFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignInFragment : Fragment(R.layout.fragment_sign_in) {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signUp.setOnClickListener {
            val action = SignInFragmentDirections.navigateToSignUp()
            findNavController().navigate(action)
        }
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