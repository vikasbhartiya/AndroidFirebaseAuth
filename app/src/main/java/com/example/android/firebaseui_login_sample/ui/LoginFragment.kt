package com.example.android.firebaseui_login_sample.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.android.firebaseui_login_sample.viewmodels.LoginViewModel
import com.example.android.firebaseui_login_sample.R
import com.example.android.firebaseui_login_sample.databinding.FragmentLoginBinding
import com.example.android.firebaseui_login_sample.utility.HelperLibrary
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth


class LoginFragment() : Fragment() {

    companion object {
        const val TAG = "MainFragment"
        const val SIGN_IN_REQUEST_CODE = 1001

    }

    // Get a reference to the ViewModel scoped to this Fragment
    private val viewModel by viewModels<LoginViewModel>()
    private lateinit var _binding: FragmentLoginBinding
    private val Helper:HelperLibrary = HelperLibrary()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        _binding = FragmentLoginBinding.inflate(inflater, container, false)


        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAuthenticationState()

        _binding.authButton.setOnClickListener {
            launchSignInFlow()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN_REQUEST_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                // User successfully signed in.
                Log.i(TAG, "Successfully signed in user ${FirebaseAuth.getInstance().currentUser?.displayName}!")
            } else {
                // Sign in failed. If response is null, the user canceled the
                // sign-in flow using the back button. Otherwise, check
                // the error code and handle the error.
                Log.i(TAG, "Sign in unsuccessful ${response?.error?.errorCode}")
            }
        }
    }

    /**
     * Observes the authentication state and changes the UI accordingly.
     * If there is a logged in user: (1) show a logout button and (2) display their name.
     * If there is no logged in user: show a login button
     */
    private fun observeAuthenticationState() {
        val factToDisplay = viewModel.getFactToDisplay(requireContext())


        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> {
                    _binding.authButton.text = getString(R.string.logout_button_text)
                    _binding.authButton.setOnClickListener {
                        AuthUI.getInstance().signOut(requireContext())
                    }

                    _binding.welcomeText.text = getFactWithPersonalization(factToDisplay)
                    _binding.welcomeText.text = FirebaseAuth.getInstance().currentUser?.phoneNumber.toString()+FirebaseAuth.getInstance().currentUser?.uid.toString()+FirebaseAuth.getInstance().currentUser?.email
                }
                else -> {
                    _binding.authButton.text = getString(R.string.login_button_text)
                    _binding.authButton.setOnClickListener {launchSignInFlow() }
                    _binding.welcomeText.text = factToDisplay
                }
            }
        })
    }


    private fun getFactWithPersonalization(fact: String): String {
        return String.format(
            resources.getString(
                R.string.welcome_message_authed,
                FirebaseAuth.getInstance().currentUser?.displayName,
                Character.toLowerCase(fact[0]) + fact.substring(1)
            )
        )
    }

    private fun launchSignInFlow() {
        // Create and launch the sign-in intent.
        // We listen to the response of this activity with the
        // SIGN_IN_REQUEST_CODE.
        Toast.makeText(this.activity,"launching AUTHUI",Toast.LENGTH_SHORT).show()
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(Helper.providers)
                .build(),
            SIGN_IN_REQUEST_CODE
        )
    }
}