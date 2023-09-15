package com.marcoscg.movies.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.ActivityNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.marcoscg.movies.R
import com.marcoscg.movies.common.utils.gone
import com.marcoscg.movies.common.utils.setAnchorId
import com.marcoscg.movies.common.utils.visible
import com.marcoscg.movies.data.Resource
import com.marcoscg.movies.databinding.FragmentAccountBinding
import com.marcoscg.movies.databinding.FragmentMovieListBinding
import com.marcoscg.movies.model.DataUserResponse
import com.marcoscg.movies.model.RegisterStatus
import com.marcoscg.movies.ui.home.viewmodel.ProfileViewModel
import com.marcoscg.movies.ui.sign_in.LogoutDialog
import com.marcoscg.movies.ui.sign_in.SignInFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountFragment : Fragment(R.layout.fragment_account) {
    private val profileViewModel: ProfileViewModel by sharedViewModel()
    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    private fun initAction() {
        binding.buttonNavLogin.setOnClickListener {
            val action = AccountFragmentDirections.navigateToSignIn()
            findNavController().navigate(action)
        }
        binding.textSignOut.setOnClickListener {
            activity?.supportFragmentManager?.let { it1 -> LogoutDialog.newInstance {
                profileViewModel.getData()
            }.show(it1, " dialog") }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()
        initData()
    }

    private fun initData() {
        viewLifecycleOwner.lifecycleScope.launch {
            profileViewModel.profileState.collect {
                handleDataState(it)
            }
        }
        Log.d("XuanNV", "initData: ")
        profileViewModel.getData()
    }

    private fun handleDataState(state: Resource<DataUserResponse>) {
        when (state.status) {
            Resource.Status.LOADING -> {

            }
            Resource.Status.SUCCESS -> {
                Log.d("XuanNV", "SUCCESS: ")
                setDataUser(state.data)
                binding.containedProfile.visible()
                binding.layoutLogin.gone()
            }

            Resource.Status.ERROR -> {
                Log.d("XuanNV", "ERROR: ")
                binding.containedProfile.gone()
                binding.layoutLogin.visible()
            }

            Resource.Status.EMPTY -> {
                Timber.d("Empty state.")
            }
        }
    }

    private fun setDataUser(data: DataUserResponse?) {
        binding.textNameActor.text = data?.username
        binding.textUsername.text = data?.email
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    companion object {
        fun newInstance(): AccountFragment {
            val fragment = AccountFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}