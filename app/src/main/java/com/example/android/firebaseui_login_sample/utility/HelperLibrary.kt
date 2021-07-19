package com.example.android.firebaseui_login_sample.utility


import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.firebase.ui.auth.AuthUI
import org.json.JSONException


class HelperLibrary {

    companion object {
        const val SIGN_IN_REQUEST_CODE = 1001
    }

    val providers = arrayListOf(
//            AuthUI.IdpConfig.EmailBuilder().build(),
//            AuthUI.IdpConfig.GoogleBuilder().build(),
        AuthUI.IdpConfig.PhoneBuilder().setDefaultCountryIso("IN").build()
    )

    fun signInActivityIntentBuilder(): Intent {
      return  AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
    }

//    address from pincode
  suspend  fun getDataFromPincode(
    pinCode: String,
    activity: Activity,
    requestQueue: RequestQueue?
):String {
    // clearing our cache of request queue.
    requestQueue?.cache?.clear()
    // below is the url from where we will be getting
    // our response in the json format.

    val url: String = "https://api.postalpincode.in/pincode/" + pinCode
    Log.d("End point: ", url)
    // below line is use to initialize our request queue.
    val queue = Volley.newRequestQueue(activity)
    var returnedAddress:String = " "

    // in below line we are creating a
    // object request using volley.

    val objectRequest = JsonArrayRequest(Request.Method.GET, url, null,
        Response.Listener { response ->
           try {
               Log.d("inside:", "try")
               Log.d("returnedJson:", response.toString())
//               val rootArray = response.getJSONArray(0)
//               Log.d("assigned:", "rootarray")
//                var responseObject  = response as JsonObject
//               val postOfficeArray = responseObject.getAsJsonArray("PostOffice")
               val rootObject = response.getJSONObject(0)
               Log.d("rootobject:", rootObject.toString())
               val postOfficeArray = rootObject.getJSONArray("PostOffice")

               if (rootObject.getString("Status").equals("Error")) {
                   // validating if the response status is success or failure.
                   // in this method the response status is having error and
                   // we are setting text to TextView as invalid pincode.
                   Toast.makeText(activity, "Pin code is not valid.", Toast.LENGTH_SHORT).show();
                    Log.d("Inside:", "Status Error within try")
               } else {
                   // if the status is success we are calling this method
                   // in which we are getting data from post office object
                   // here we are calling first object of our json array.
                   val obj = postOfficeArray.getJSONObject(0)
                   // inside our json array we are getting district name,
                   // state and country from our data.

                   // inside our json array we are getting district name,
                   // state and country from our data.
                   val district = obj.getString("District")
                   val state = obj.getString("State")
                   val country = obj.getString("Country")
                   returnedAddress = district + ", " + state + ", " + country
                   Log.d("returnedAddress value:", returnedAddress)
               }
           } catch (e:JSONException) {
               // if we gets any error then it
               // will be printed in log cat.
               e.printStackTrace();
               Toast.makeText(activity, "Pin code is not valid.", Toast.LENGTH_SHORT).show();
               Log.d("inside:", "catch block")
           }
        },
        Response.ErrorListener { error ->
            // below method is called if we get
            // any error while fetching data from API.
            // below line is use to display an error message.
            Toast.makeText(activity, "Pin code is not valid.", Toast.LENGTH_SHORT).show();
            Log.d("Inside:", "Response.ErrorListener" + "  *****" + error.localizedMessage)
        }
    )
    // request to our request queue.
    queue.add(objectRequest)
return returnedAddress
    queue.stop()
    }
}