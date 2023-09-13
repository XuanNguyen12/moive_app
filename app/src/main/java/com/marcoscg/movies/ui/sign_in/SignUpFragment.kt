package com.marcoscg.movies.ui.sign_in

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marcoscg.movies.R
import com.marcoscg.movies.databinding.FragmentAccountBinding
import com.marcoscg.movies.databinding.FragmentSignInBinding
import com.marcoscg.movies.ui.home.AccountFragment

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {
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