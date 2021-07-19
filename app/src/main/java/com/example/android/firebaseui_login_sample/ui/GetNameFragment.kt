package com.example.android.firebaseui_login_sample.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.android.firebaseui_login_sample.R
import com.example.android.firebaseui_login_sample.databinding.FragmentGetNameBinding
import com.example.android.firebaseui_login_sample.viewmodels.RegistrationViewModel

class GetNameFragment : Fragment() {
    private lateinit var binding:FragmentGetNameBinding
    lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGetNameBinding.inflate(inflater)
        registrationViewModel = (activity as MainActivity).registrationViewModel
        binding.editTextName.hint = "Enter your stupid name"
        binding.Next2.setOnClickListener {
            Log.d("function to upd name:", "*****" + binding.editTextName.text.toString() + "*****")
            registrationViewModel.updateName(binding.editTextName.text.toString())
            NavHostFragment.findNavController(this).navigate(R.id.action_getNameFragment_to_getDobFragment)
        }


        return binding.root
    }
}