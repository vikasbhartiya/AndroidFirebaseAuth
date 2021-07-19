package com.example.android.firebaseui_login_sample.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class RegistrationViewModel: ViewModel() {

     var username: String? = null
    var phone:String? = null
     var password: String? = null
     var skill:String? = null
    var pincode:Number? = 0
    var address:String? = null
     var mSal:Number = 0
     var hSal:Number = 0
//    private var acceptedTCs: Boolean? = null
     var name:String? = null
//    private val zoneId:ZoneId = ZoneId.of("Europe/Paris")
//    var dob: ZonedDateTime? = ZonedDateTime.now(zoneId)
//    private val myFormat = "MM/dd/yyyy"
    var dob:String? = null

//    var dob:LocalDate? = LocalDate.now()




    fun updateName(name: String) {
        this.name = name
//        Toast.makeText(this.activity!!,"Name was updated with - " + this.name, Toast.LENGTH_LONG).show()
        Log.d("Name updated with:", "****" + name + "****")
    }
    fun updateDob(dob: String) {

        this.dob = dob
        Log.d("Dob updated with:", "****" + dob + "****")
    }
    fun updateSkillPin(skill: String, pin:Number) {
        this.skill = skill
        this.pincode = pin

        Log.d("skill updated with:", "****" + skill + "****")
        Log.d("pin updated with:", "****" + pin + "****")
    }
    fun updateSalary(mSal: Number, hSal:Number) {
        this.mSal = mSal
        this.hSal = hSal

        Log.d("mSal updated with:", "****" + mSal + "****")
        Log.d("hSal updated with:", "****" + hSal + "****")
    }

    fun updateUserData(username: String, phone:String) {
        this.username = username
        this.phone = phone

        Log.d("username updated with:", "****" + username + "****")
        Log.d("phone updated with:", "****" + phone + "****")
    }

    fun updateAddressFromPin(address:String){
        this.address = address
        Log.d("address updated with:", "****" + address + "****")
    }
}
