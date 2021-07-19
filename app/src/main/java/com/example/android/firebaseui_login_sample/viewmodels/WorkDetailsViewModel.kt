/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.firebaseui_login_sample.viewmodels

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.firebaseui_login_sample.network.PinCodeApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import com.android.volley.toolbox.JsonArrayRequest
import com.google.gson.JsonArray

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class WorkDetailsViewModel() : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response
//;    private val fActivity = activity
    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
//    init {
//        getPinCodes(activity)
//    }

//fun callGetPinCodes(activity: Activity) {
//    PinCodeApi.retrofitService.getPinCodes().enqueue(
//        object :Callback<JsonArray> {
//            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
//                val rootObject = response.body()?.get(0)?.asJsonObject
//                val postOfficeArray = rootObject?.getAsJsonArray("PostOffice")
//                if (rootObject != null) {
//                    if(rootObject.get("Status").equals("Error")){
//                        Toast.makeText(activity, "Pin code is not valid.", Toast.LENGTH_SHORT).show();
//                    }
//                    } else {
//                    val obj = postOfficeArray?.get(0)?.asJsonObject
//                    val district = obj?.get("District").toString()
//                    val state = obj?.get("State").toString()
//                    val country = obj?.get("Country").toString()
//                    _response.value = district + ", " + state + ", " + country
//                    Toast.makeText(activity,"returnedAddress: ${_response.value} Mars properties retrieved",Toast.LENGTH_LONG).show()
//
//                }
//                }
//
//            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
//                _response.value = "Failure: " + t.message
//                Toast.makeText(activity,"Failure: ${t.message} ",Toast.LENGTH_LONG).show()
//
//            }
//
//        }
//    )
//}

    fun getPinCodes(activity: Activity) {

        val returnedAddress:String
        viewModelScope.launch {
            try {
                val listResult = PinCodeApi.retrofitService.getPinCodes()
                val rootObject = listResult.get(0).asJsonObject
                val postOfficeArray = rootObject.getAsJsonArray("PostOffice")


                if(rootObject.get("Status").equals("Error")) {
                    // validating if the response status is success or failure.
                    // in this method the response status is having error and
                    // we are setting text to TextView as invalid pincode.
                    Toast.makeText(activity, "Pin code is not valid.", Toast.LENGTH_SHORT).show();
                    Log.d("Inside:", "Status Error within try")
                } else {
                    // if the status is success we are calling this method
                    // in which we are getting data from post office object
                    // here we are calling first object of our json array.
                    val obj = postOfficeArray.get(0).asJsonObject
                    // inside our json array we are getting district name,
                    // state and country from our data.

                    // inside our json array we are getting district name,
                    // state and country from our data.
                    val district = obj.get("District").toString()
                    val state = obj.get("State").toString()
                    val country = obj.get("Country").toString()
                    _response.value = district + ", " + state + ", " + country
                    Log.d("returnedAddress value:", _response.value)
                }


                  Toast.makeText(activity,"returnedAddress: ${_response.value} Mars properties retrieved",Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
                Toast.makeText(activity,"Failure: ${e.message}",Toast.LENGTH_LONG).show()
                Timber.i("${e.message}")
            }

            }
    }
}
