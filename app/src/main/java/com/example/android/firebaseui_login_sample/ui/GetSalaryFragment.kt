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
import androidx.navigation.fragment.NavHostFragment
import com.example.android.firebaseui_login_sample.R
import com.example.android.firebaseui_login_sample.databinding.FragmentGetSalaryBinding
import com.example.android.firebaseui_login_sample.datamodels.Workers
import com.example.android.firebaseui_login_sample.utility.FirebaseStorageUtil
import com.example.android.firebaseui_login_sample.utility.HelperLibrary
import com.example.android.firebaseui_login_sample.utility.WorkersUtil
import com.example.android.firebaseui_login_sample.viewmodels.RegistrationViewModel
import com.example.android.firebaseui_login_sample.viewmodels.WorkersViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber


class GetSalaryFragment : Fragment() {
    private lateinit var _binding: FragmentGetSalaryBinding
    private lateinit var registrationViewModel: RegistrationViewModel
    private val helper: HelperLibrary = HelperLibrary()
    private var mFirestore: FirebaseFirestore? = null
    private lateinit var mWorkerViewModel:WorkersViewModel

    companion object {
        const val SIGN_IN_RESULT_CODE = 1001
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGetSalaryBinding.inflate(inflater)
        registrationViewModel = (activity as MainActivity).registrationViewModel

        mWorkerViewModel = WorkersViewModel(registrationViewModel)

        Log.d("Updated VM data:", "Name:" + registrationViewModel.name.toString() + " Dob: " + registrationViewModel.dob
        + "skill:" + registrationViewModel.skill + "pincode:" + registrationViewModel.pincode)
        Timber.i("Name:" + registrationViewModel.name.toString() + " Dob: " + registrationViewModel.dob
                + "skill:" + registrationViewModel.skill + "pincode:" + registrationViewModel.pincode)
        _binding.Next2.setOnClickListener {

          registrationViewModel.updateSalary(Integer.valueOf(_binding.editTextSalM.text.toString()), Integer.valueOf(_binding.editTextSalH.text.toString()))

            startActivityForResult(
            helper.signInActivityIntentBuilder(),HelperLibrary.SIGN_IN_REQUEST_CODE
            )
        }


        return _binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GetSalaryFragment.SIGN_IN_RESULT_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                // User successfully signed in.
                Log.i("Signin result", "Successfully signed in user ${FirebaseAuth.getInstance().currentUser?.displayName}!")
//                Update registration username and phone data
                registrationViewModel.updateUserData(FirebaseAuth.getInstance().currentUser?.phoneNumber.toString()
                , FirebaseAuth.getInstance().currentUser?.phoneNumber.toString())

                // Initialize Firestore
                mFirestore = FirebaseStorageUtil().getFirestore()
                // Get a reference to the workers collection
                val workers: CollectionReference? = mFirestore?.collection("workers")


                    val worker: Workers? = mWorkerViewModel.addWorker()

                    // Add a new document to the restaurants collection
//                check if document already exists, proceed only if it doesnt
                var existingPhoneNum = workers?.whereEqualTo("phone", worker?.getPhone())
                if(existingPhoneNum == null) {
                    workers?.add(worker as Any)
                    NavHostFragment.findNavController(this).navigate(R.id.action_getSalaryFragment_to_profileSummaryFragment)

                } else {
                    Toast.makeText(this.activity,"User already exists, transferring to Summary page", Toast.LENGTH_SHORT).show()
                    NavHostFragment.findNavController(this).navigate(R.id.action_getSalaryFragment_to_starterOne)
                }


            } else {
                // Sign in failed. If response is null, the user canceled the
                // sign-in flow using the back button. Otherwise, check
                // the error code and handle the error.
                Log.i("Signin result", "Sign in unsuccessful ${response?.error?.errorCode}")
            }
        }
    }
}