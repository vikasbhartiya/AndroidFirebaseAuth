package com.example.android.firebaseui_login_sample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.android.firebaseui_login_sample.R
import com.example.android.firebaseui_login_sample.databinding.FragmentWelcomeScreenBinding

class WelcomeScreen : Fragment(), Animation.AnimationListener {
   lateinit var  animFadeIn:Animation
    lateinit var  animZoomIn:Animation
    lateinit var  animZoomOut:Animation
    lateinit var _binding: FragmentWelcomeScreenBinding

    override fun onAnimationStart(animation: Animation?) {

        if (animation == animFadeIn){
            Toast.makeText(this.activity,"launching animation",Toast.LENGTH_SHORT).show()
        }



    }

    override fun onAnimationEnd(animation: Animation?) {

        // Take any action after completing the animation
        // check for fade in animation
        if (animation == animFadeIn) {
            Toast.makeText(this.activity,"ending fadein animation",Toast.LENGTH_SHORT).show()
            // set animation listener
            animZoomIn?.setAnimationListener(this)

            // start the animation
            _binding.welcomeTextView.startAnimation(animZoomIn)
        }

        if (animation == animZoomIn) {
            Toast.makeText(this.activity,"starting zoomin animation",Toast.LENGTH_SHORT).show()
            // set animation listener
            animZoomOut?.setAnimationListener(this)

            // start the animation
            _binding.welcomeTextView.startAnimation(animZoomOut)
        }
        if (animation == animZoomOut) {
            NavHostFragment.findNavController(this).navigate(R.id.action_welcomeScreen_to_starterOne)
        }

    }

    override fun onAnimationRepeat(p0: Animation?) {
        Toast.makeText(this.activity,"repeating animation",Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWelcomeScreenBinding.inflate(inflater, container, false)
        animFadeIn = AnimationUtils.loadAnimation(this.activity, R.anim.fade_in)
        animZoomIn = AnimationUtils.loadAnimation(this.activity, R.anim.zoom_in)
        animZoomOut = AnimationUtils.loadAnimation(this.activity, R.anim.zoom_out)

       _binding.welcomeTextView.visibility = View.INVISIBLE
        animFadeIn?.setAnimationListener(this)
        _binding.welcomeTextView.startAnimation(animFadeIn)

        return _binding.root
    }

    }
