package com.example.android.firebaseui_login_sample.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.example.android.firebaseui_login_sample.R
import com.example.android.firebaseui_login_sample.databinding.FragmentGetDobBinding
import com.example.android.firebaseui_login_sample.viewmodels.RegistrationViewModel
import java.text.SimpleDateFormat
import java.util.*


class GetDobFragment() : Fragment() {
    private lateinit var binding: FragmentGetDobBinding
    private lateinit var registrationViewModel:RegistrationViewModel
    val cal = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGetDobBinding.inflate(inflater)
        registrationViewModel = (activity as MainActivity).registrationViewModel
        Toast.makeText(this.activity,"Name = " + registrationViewModel.name.toString(),Toast.LENGTH_LONG).show()

        // create an OnDateSetListener

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }
        binding.pickDob.setOnClickListener {
            DatePickerDialog(
                activity as MainActivity,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }


        binding.Next2.setOnClickListener {

            NavHostFragment.findNavController(this).navigate(R.id.action_getDobFragment_to_getWorkDetailsFragment)
        }


        return binding.root
    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.dobText.text = sdf.format(cal.getTime())
        Log.d("selected date:", binding.dobText.text as String)
        registrationViewModel.updateDob(binding.dobText.text.toString())
    }
}