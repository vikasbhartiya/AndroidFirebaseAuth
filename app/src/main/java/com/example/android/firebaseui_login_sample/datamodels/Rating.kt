package com.example.android.firebaseui_login_sample.datamodels

import android.text.TextUtils
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ServerTimestamp
import java.util.*


class Rating {

    private var userId: String? = null
    private var userName: String? = null
    private var rating = 0.0
    private var text: String? = null

    @ServerTimestamp
    private var timestamp: Date? = null

    fun Rating() {}

    fun Rating(user: FirebaseUser, rating: Double, text: String?) {
        userId = user.uid
        userName = user.displayName
        if (TextUtils.isEmpty(userName)) {
            userName = user.email
        }
        this.rating = rating
        this.text = text
    }

    fun getUserId(): String? {
        return userId
    }

    fun setUserId(userId: String?) {
        this.userId = userId
    }

    fun getUserName(): String? {
        return userName
    }

    fun setUserName(userName: String?) {
        this.userName = userName
    }

    fun getRating(): Double {
        return rating
    }

    fun setRating(rating: Double) {
        this.rating = rating
    }

    fun getText(): String? {
        return text
    }

    fun setText(text: String?) {
        this.text = text
    }

    fun getTimestamp(): Date? {
        return timestamp
    }

    fun setTimestamp(timestamp: Date?) {
        this.timestamp = timestamp
    }
}