package com.example.android.firebaseui_login_sample.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.android.firebaseui_login_sample.R
import com.example.android.firebaseui_login_sample.databinding.FragmentStarterOneBinding
import com.example.android.firebaseui_login_sample.datamodels.Workers
import com.example.android.firebaseui_login_sample.utility.FirebaseStorageUtil
import com.example.android.firebaseui_login_sample.utility.HelperLibrary
import com.example.android.firebaseui_login_sample.viewmodels.RegistrationViewModel
import com.example.android.firebaseui_login_sample.viewmodels.WorkersViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class StarterOne : Fragment() {

    private lateinit var binding:FragmentStarterOneBinding
    private var helpers:HelperLibrary = HelperLibrary()
    private var mFirestore: FirebaseFirestore? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStarterOneBinding.inflate(layoutInflater)


        binding.signUp.setOnClickListener{
//            start signin Activity - if user exists - transfer to profileSummary otherwise transfer to getName

            startActivityForResult(helpers.signInActivityIntentBuilder(), HelperLibrary.SIGN_IN_REQUEST_CODE)

//            NavHostFragment.findNavController(this).navigate(R.id.action_starterOne_to_getNameFragment)
        }
//        binding.login.setOnClickListener{
////            NavHostFragment.findNavController(this).navigate(R.id.action_starterOne_to_loginFragment)
//            startActivityForResult(helpers.signInActivityIntentBuilder(), HelperLibrary.SIGN_IN_REQUEST_CODE)
//        }
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == HelperLibrary.SIGN_IN_REQUEST_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                // User successfully signed in.
                Log.i(LoginFragment.TAG, "Successfully signed in user ${FirebaseAuth.getInstance().currentUser?.displayName}!")

//                check if document exists in firebase with this phone number, if yes, transfer to summary otherwise
//                start registration process.

                mFirestore = FirebaseStorageUtil().getFirestore()
                // Get a reference to the workers collection
                val workers: CollectionReference? = mFirestore?.collection("workers")

                // Add a new document to the restaurants collection
//                check if document already exists, proceed only if it doesnt
                var existingPhoneNum = workers?.whereEqualTo("phone", FirebaseAuth.getInstance().currentUser?.phoneNumber.toString())
                if(existingPhoneNum == null) {
//                    start registration process
                    NavHostFragment.findNavController(this).navigate(R.id.action_starterOne_to_getNameFragment)
                } else {
//                    show summary page
//                    NavHostFragment.findNavController(this).navigate(R.id.action_starterOne_to_profileSummaryFragment)
                    NavHostFragment.findNavController(this).navigate(R.id.action_starterOne_to_getNameFragment)
                }


            } else {
                // Sign in failed. If response is null, the user canceled the
                // sign-in flow using the back button. Otherwise, check
                // the error code and handle the error.
                Log.i(LoginFragment.TAG, "Sign in unsuccessful ${response?.error?.errorCode}")
            }
        }
    }
}