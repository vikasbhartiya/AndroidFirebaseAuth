package com.example.android.firebaseui_login_sample.utility

import android.content.Context
import com.example.android.firebaseui_login_sample.R
import com.example.android.firebaseui_login_sample.datamodels.Workers
import java.util.*
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit


class WorkersUtil {

    private val TAG = "WorkersUtil"

    private val EXECUTOR = ThreadPoolExecutor(
        2, 4, 60,
        TimeUnit.SECONDS, LinkedBlockingQueue()
    )

    private val WORKERS_URL_FMT =
        "https://storage.googleapis.com/firestorequickstarts.appspot.com/food_%d.png"

    private val MAX_IMAGE_NUM = 22

    private val NAME_FIRST_WORDS = arrayOf(
        "Foo",
        "Bar",
        "Baz",
        "Qux",
        "Fire",
        "Sam's",
        "World Famous",
        "Google",
        "The Best"
    )

    private val NAME_SECOND_WORDS = arrayOf(
        "Restaurant",
        "Cafe",
        "Spot",
        "Eatin' Place",
        "Eatery",
        "Drive Thru",
        "Diner"
    )


    /**
     * Create a random Restaurant POJO.
     */
    fun getRandom(context: Context): Workers? {
        val restaurant = Workers()
        val random = Random()

        // Cities (first element is 'Any')
//        var cities = context.resources.getStringArray(R.array.cities)
//        cities = Arrays.copyOfRange(cities, 1, cities.size)
//
//        // Categories (first element is 'Any')
//        var categories = context.resources.getStringArray(R.array.categories)
//        categories = Arrays.copyOfRange(categories, 1, categories.size)
//        val prices = intArrayOf(1, 2, 3)
//        restaurant.setName(getRandomName(random))
//        restaurant.setCity(getRandomString(cities, random))
//        restaurant.setCategory(getRandomString(categories, random))
//        restaurant.setPhoto(getRandomImageUrl(random))
//        restaurant.setPrice(getRandomInt(prices, random))
//        restaurant.setAvgRating(getRandomRating(random))
//        restaurant.setNumRatings(random.nextInt(20))
        return restaurant
    }


    /**
     * Get a random image.
     */
    private fun getRandomImageUrl(random: Random): String? {
        // Integer between 1 and MAX_IMAGE_NUM (inclusive)
        val id = random.nextInt(MAX_IMAGE_NUM) + 1
//        return String.format(Locale.getDefault(), RESTAURANT_URL_FMT, id)
        return "Vikas"
    }

    /**
     * Get price represented as dollar signs.
     */
    fun getPriceString(restaurant: Workers): String? {
//        return getPriceString(restaurant.getPrice())
        return "Vikas"
    }

    /**
     * Get price represented as dollar signs.
     */
    fun getPriceString(priceInt: Int): String? {
        return when (priceInt) {
            1 -> "$"
            2 -> "$$"
            3 -> "$$$"
            else -> "$$$"
        }
    }

    private fun getRandomRating(random: Random): Double {
        val min = 1.0
        return min + random.nextDouble() * 4.0
    }

    private fun getRandomName(random: Random): String? {
        return (getRandomString(NAME_FIRST_WORDS, random) + " "
                + getRandomString(NAME_SECOND_WORDS, random))
    }

    private fun getRandomString(array: Array<String>, random: Random): String {
        val ind = random.nextInt(array.size)
        return array[ind]
    }

    private fun getRandomInt(array: IntArray, random: Random): Int {
        val ind = random.nextInt(array.size)
        return array[ind]
    }

}