package com.example.android.firebaseui_login_sample.viewmodels

import androidx.lifecycle.ViewModel
import com.example.android.firebaseui_login_sample.datamodels.Workers

class WorkersViewModel() : ViewModel(){
    private var worker:Workers = Workers()
    private lateinit var fRegData: RegistrationViewModel

    constructor(registrationViewModel: RegistrationViewModel) : this() {
        this.fRegData = registrationViewModel
    }

    fun addWorker(): Workers {

        worker = Workers(fRegData.username.toString(), fRegData.phone.toString(), fRegData.name.toString()
        , fRegData.dob.toString(), fRegData.pincode?.toInt(), fRegData.skill.toString()
        ,"photo",fRegData.mSal.toDouble(),fRegData.hSal.toDouble(), 0, 0.0)

        return worker
    }
}