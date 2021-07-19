package com.example.android.firebaseui_login_sample.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment
import com.android.volley.RequestQueue
import com.example.android.firebaseui_login_sample.R
import com.example.android.firebaseui_login_sample.databinding.FragmentGetWorkDetailsBinding
import com.example.android.firebaseui_login_sample.network.PinCodeApi
import com.example.android.firebaseui_login_sample.utility.HelperLibrary
import com.example.android.firebaseui_login_sample.viewmodels.RegistrationViewModel
import com.example.android.firebaseui_login_sample.viewmodels.WorkDetailsViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber

class GetWorkDetailsFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var _binding: FragmentGetWorkDetailsBinding
    private lateinit var registrationViewModel: RegistrationViewModel
//    private lateinit var workDetailsVM: WorkDetailsViewModel
//    private var helper:HelperLibrary = HelperLibrary()
    // creating a variable for request queue.
//    private val mRequestQueue: RequestQueue? = null
//    var returnedAddress: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGetWorkDetailsBinding.inflate(inflater)
        registrationViewModel = (activity as MainActivity).registrationViewModel
//        workDetailsVM = WorkDetailsViewModel()


//        ArrayAdapter.createFromResource(
//            activity!!, R.array.Skills,
//        android.R.layout.simple_spinner_item)
//            .also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            _binding.getSkillSet.adapter = adapter
//        }
        var adapter = ArrayAdapter.createFromResource(
            requireActivity(), R.array.Skills,
        android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        _binding.getSkillSet.adapter = adapter
        _binding.getSkillSet.onItemSelectedListener = this



        _binding.Next2.setOnClickListener {
            NavHostFragment.findNavController(GetWorkDetailsFragment())
                      .navigate(R.id.action_getWorkDetailsFragment_to_getSalaryFragment)


//            putting all function logic here to see if a proper flow is possible
//*************************************************************************

//            this.lifecycleScope.launch {
//                try {
//                    val listResult = PinCodeApi.retrofitService.getPinCodes()
//                    val rootObject = listResult.get(0).asJsonObject
//                    val postOfficeArray = rootObject.getAsJsonArray("PostOffice")
//
//
//                    if (rootObject.get("Status").equals("Error")) {
//                        // validating if the response status is success or failure.
//                        // in this method the response status is having error and
//                        // we are setting text to TextView as invalid pincode.
//                        Toast.makeText(activity, "Pin code is not valid.", Toast.LENGTH_SHORT)
//                            .show();
//                        Log.d("Inside:", "Status Error within try")
//                    } else {
//                        // if the status is success we are calling this method
//                        // in which we are getting data from post office object
//                        // here we are calling first object of our json array.
//                        val obj = postOfficeArray.get(0).asJsonObject
//                        // inside our json array we are getting district name,
//                        // state and country from our data.
//
//                        // inside our json array we are getting district name,
//                        // state and country from our data.
//                        val district = obj.get("District").toString()
//                        val state = obj.get("State").toString()
//                        val country = obj.get("Country").toString()
//                        returnedAddress = district + ", " + state + ", " + country
//
//                    }
//
//
//                    Toast.makeText(
//                        activity,
//                        "returnedAddress: ${returnedAddress} Mars properties retrieved",
//                        Toast.LENGTH_LONG
//                    ).show()
//                } catch (e: Exception) {
////                    returnedAddress = "Failure: ${e.message}"
//                    Toast.makeText(activity, "Failure: ${e.message}", Toast.LENGTH_LONG).show()
//                    Timber.i("${e.message}")
//                }
//
//
////            ********************************************************************
//
//                if (returnedAddress == null) {
//                    //                ask for a valid pin
//                    Toast.makeText(
//                        activity,
//                        "Pin code is not valid. Please enter a valid pincode",
//                        Toast.LENGTH_LONG
//                    ).show();
//                } else {
//
//                    Toast.makeText(
//                        activity,
//                        "Address received: ${returnedAddress}",
//                        Toast.LENGTH_LONG
//                    ).show();
////                registrationViewModel.updateSkillPin(
////                      _binding.selectedSkill.text.toString(),
////                      Integer.valueOf(_binding.editNumPin.text.toString())
////                  )
////
////                  registrationViewModel.updateAddressFromPin(workDetailsVM.response.toString())
////
////                  NavHostFragment.findNavController(GetWorkDetailsFragment())
////                      .navigate(R.id.action_getWorkDetailsFragment_to_getSalaryFragment)
//                }
//
////            validate the pin and get the address before proceeding forward
////            var activity:Activity = this.activity!!
////          helper.getDataFromPincode(
////                  _binding.editNumPin.text.toString(),
////                  activity,
////                  mRequestQueue
////              ) { address ->
////                  registrationViewModel.updateSkillPin(
////                      _binding.selectedSkill.text.toString(),
////                      Integer.valueOf(_binding.editNumPin.text.toString())
////                  )
////
////                  registrationViewModel.updateAddressFromPin(address)
////
////                  NavHostFragment.findNavController(GetWorkDetailsFragment())
////                      .navigate(R.id.action_getWorkDetailsFragment_to_getSalaryFragment)
////
////              }
//
//
////              Log.d("inside workdetails:", address)
////              if (address != " ") {
//////               pin is valid, can update the address and proceed forward
////
////                  registrationViewModel.updateSkillPin(
////                      _binding.selectedSkill.text.toString(),
////                      Integer.valueOf(_binding.editNumPin.text.toString())
////                  )
////
////                  registrationViewModel.updateAddressFromPin(address)
////
////                  NavHostFragment.findNavController(GetWorkDetailsFragment())
////                      .navigate(R.id.action_getWorkDetailsFragment_to_getSalaryFragment)
////
////
////              } else {
//////                ask for a valid pin
////                  Toast.makeText(
////                      activity,
////                      "Pin code is not valid. Please enter a valid pincode",
////                      Toast.LENGTH_LONG
////                  ).show();
////              }
//
//            }
        }


        return _binding.root
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        Toast.makeText(this.activity,"item selected" + p0?.getItemAtPosition(p2).toString(),Toast.LENGTH_SHORT).show()
//        val testVM = workDetailsVM
//        Toast.makeText(this.activity,"Calling network",Toast.LENGTH_SHORT).show()



        _binding.selectedSkill.setText(p0?.getItemAtPosition(p2).toString())
        Log.d("Updated VM data:", "Name:" + registrationViewModel.name.toString() + " Dob: " + registrationViewModel.dob)


    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Toast.makeText(this.activity,"Nothing selected",Toast.LENGTH_SHORT).show()
    }

}