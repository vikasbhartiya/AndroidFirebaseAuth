     package com.example.android.firebaseui_login_sample.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.android.firebaseui_login_sample.viewmodels.LoginViewModel
import com.example.android.firebaseui_login_sample.R
import com.example.android.firebaseui_login_sample.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private lateinit var _binding: FragmentStartBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStartBinding.inflate(inflater, container, false)

//        binding = FragmentStartBinding.inflate(layoutInflater)
       return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAuthenticationState()
    }

    private fun observeAuthenticationState() {

        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> {
//                    NavHostFragment.findNavController(this).navigate(R.id.action_startFragment_to_profileSummaryFragment)
//                    navhostfragment call above works - for better practice - checking if the call below works or not

//                    view?.findNavController()
//                        ?.navigate(R.id.action_startFragment_to_profileSummaryFragment)
//                 below is same call as above - except it uses Navigation Direction classes generated
//                    by safeargs plugin
                    view?.findNavController()
                        ?.navigate(StartFragmentDirections.actionStartFragmentToProfileSummaryFragment())
                }
                else -> {
                    //  if there is no logged-in user,

//                    Vikas
//                    move the user to the welcome screen using navcontroller

                    NavHostFragment.findNavController(this).navigate(R.id.action_startFragment_to_welcomeScreen)

                }
            }
        })
    }
}