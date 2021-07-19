package com.example.android.firebaseui_login_sample.datamodels

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class Workers {
  private  var userID:String? = null
  private  var phone:String? = null
  private  var name: String? = null
    private  var dob:String? = null
    private  var areaPin: Number? = 0
    private var skill: String? = null
    private  var photo: String? = null
    private  var mSal:Double? = 0.0
    private var hSal:Double? = 0.0
    private var numRatings:Int? = 0
    private  var avgRating:Double? = 0.0

    companion object {
        const val FIELD_CITY = "city"
        const val FIELD_CATEGORY = "category"
        const val FIELD_PRICE = "price"
        const val FIELD_POPULARITY = "numRatings"
        const val FIELD_AVG_RATING = "avgRating"
    }

    constructor() {}
    constructor(userID:String?,phone:String?,
        name: String?, dob: String?, areaPin: Int?, skill: String?, photo: String?,
        mSal: Double?, hSal:Double?, numRatings: Int?, avgRating: Double
    ) {
        this.userID = userID
        this.phone = phone
        this.name = name
        this.dob = dob
        this.areaPin = areaPin
        this.skill = skill
        this.photo = photo
        this.mSal  = mSal
        this.hSal  = hSal
        this.numRatings = numRatings
        this.avgRating = avgRating
    }

    fun getUserID(): String? {
        return userID
    }

    fun setUserID(userID: String?) {
        this.userID = userID
    }

    fun getPhone(): String? {
        return phone
    }

    fun setPhone(phone: String?) {
        this.phone = phone
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getDob(): String? {
        return dob
    }

    fun setDob(dob: String?) {
        this.dob = dob
    }

    fun getAreaPin(): Number? {
        return areaPin
    }

    fun setAreaPin(areaPin: Int?) {
        this.areaPin = areaPin
    }

    fun getSkill(): String? {
        return skill
    }

    fun setSkill(skill: String?) {
        this.skill = skill
    }

    fun getPhoto(): String? {
        return photo
    }

    fun setPhoto(photo: String?) {
        this.photo = photo
    }

    fun getMSal(): Double? {
        return mSal
    }

    fun setMSal(mSal: Double?) {
        this.mSal = mSal
    }
    fun getHSal(): Double? {
        return hSal
    }

    fun setHSal(hSal: Double?) {
        this.hSal = hSal
    }

    fun getNumRatings(): Int {
        return numRatings!!
    }

    fun setNumRatings(numRatings: Int) {
        this.numRatings = numRatings
    }

    fun getAvgRating(): Double {
        return avgRating!!
    }

    fun setAvgRating(avgRating: Double) {
        this.avgRating = avgRating
    }
}