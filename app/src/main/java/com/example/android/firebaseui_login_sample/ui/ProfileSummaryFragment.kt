package com.example.android.firebaseui_login_sample.ui

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Picture
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.android.firebaseui_login_sample.R
import com.example.android.firebaseui_login_sample.databinding.FragmentProfileSummaryBinding
import com.example.android.firebaseui_login_sample.utility.FirebaseStorageUtil
import com.example.android.firebaseui_login_sample.viewmodels.RegistrationViewModel
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query


class ProfileSummaryFragment : Fragment() {

    companion object {
        private val LIMIT = 1
        private const val PICK_IMAGE = 100
    }

    private lateinit var binding: FragmentProfileSummaryBinding
    lateinit var registrationViewModel: RegistrationViewModel
    private var mFirestore: FirebaseFirestore? = null
    private var mQuery: Query? = null
    private var firebaseUser: FirebaseUser? = null


//    var pictureListener = object : WebView.PictureListener {
//       fun onProfilePictureUpdated() {
//            val uri = firebaseUser!!.photoUrl
//            Glide.with(activity!!).load(uri).into(binding.profilePhoto)
//
//        }
//
//        override fun onNewPicture(p0: WebView?, p1: Picture?) {
//            val uri = firebaseUser!!.photoUrl
//            Glide.with(activity!!).load(uri).into(binding.profilePhoto)
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileSummaryBinding.inflate(inflater)
        registrationViewModel = (activity as MainActivity).registrationViewModel

        val firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.getCurrentUser() != null) {
            firebaseUser = firebaseAuth.currentUser;
        }
//        binding.profilePhoto.setImageResource(R.drawable.mrb_star_icon_black_36dp);
//        var profilePicture : Uri? = FirebaseAuth.getInstance().currentUser?.getPhotoUrl()

//        binding.profilePhoto.setImageURI(profilePicture)

        Log.d("photo url:", firebaseUser?.photoUrl.toString())
//        binding.Next2.text = firebaseUser?.photoUrl.toString()
        val uri1:Uri = Uri.parse(firebaseUser?.photoUrl.toString())
        Glide.with(this).load(uri1).into(binding.profilePhoto)



        binding.logOut.setOnClickListener {

//            AuthUI.getInstance().signOut(requireContext())
            firebaseAuth.signOut()
            NavHostFragment.findNavController(this).navigate(R.id.action_profileSummaryFragment_to_welcomeScreen)
        }

        mFirestore = FirebaseStorageUtil().getFirestore()
        // Get the 50 highest rated restaurants
        mQuery = mFirestore!!.collection("workers").whereEqualTo("phone", firebaseUser?.phoneNumber.toString())
//            .orderBy("avgRating", Query.Direction.DESCENDING)
//            .limit(ProfileSummaryFragment.LIMIT.toLong())

        mQuery!!.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("returned data:", "${document.id} => ${document.data["phone"]}")
                    binding.profileName.text = document.data["name"].toString()
                    binding.phoneNum.text = document.data["phone"].toString()
                    binding.skillSet.text = document.data["skill"].toString()
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Failure msg:", "Error getting documents: ", exception)
            }


        binding.pickPhoto.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, PICK_IMAGE);
        }

        binding.profilePhoto.setOnClickListener{

               binding.pickPhoto.isVisible = !binding.pickPhoto.isVisible
        }


//        binding.Next2.setOnClickListener {
//            Log.d("function to upd name:", "*****" + binding.editTextName.text.toString() + "*****")
//            registrationViewModel.updateName(binding.editTextName.text.toString())
//            NavHostFragment.findNavController(this).navigate(R.id.action_getNameFragment_to_getDobFragment)
//        }
//        pictureListener = object : WebView.PictureListener() {
//            fun onProfilePictureUpdated() {
//                val uri = firebaseUser!!.photoUrl
//                Glide.with(activity!!).load(uri).into<Target<Drawable>>(imageView)
//                skipBtn.setBackgroundColor(Color.parseColor("#2c8bff"))
//                skipBtn.setText("done")
//            }
//        }
//       var pictureListener:WebView.PictureListener = WebView.PictureListener()
//       { webView: WebView, picture: Picture? ->
//
//       }




        return binding.root
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
//            val imageUri = data.data
//            updateUserProfilePicture(imageUri)
//        }
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            val imageUri: Uri? = data?.data
            if (imageUri != null) {
                updateUserProfilePicture(imageUri)
            }
        }
    }

//    private fun updateUserProfilePicture(uri: Uri) {
//        var picture:Picture? = null
//        val profileChangeRequest = UserProfileChangeRequest.Builder()
//            .setPhotoUri(uri)
//            .build()
//        firebaseUser?.updateProfile(profileChangeRequest)
//            ?.addOnCompleteListener(OnCompleteListener<Void?> { task ->
//                if (task.isSuccessful) {
//                    pictureListener.onNewPicture(WebView(this.activity), picture)
//                }
//            })
//    }

    private fun updateUserProfilePicture(uri: Uri) {
//        loadingDialog.setMessage("Changing profile picture...")
//        loadingDialog.show(getSupportFragmentManager(), "Changing Profile Picture")
        val profileChangeRequest = UserProfileChangeRequest.Builder()
            .setPhotoUri(uri)
            .build()
        firebaseUser!!.updateProfile(profileChangeRequest)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onChangedProfilePicture(uri)
                }
//                loadingDialog.dismiss()
            }
    }

    fun onChangedProfilePicture(uri: Uri?) {
        Glide.with(this).load(uri).into(binding.profilePhoto)
//        Glide.with(activity!!).load(uri).into(binding.profilePhoto)
    }
}