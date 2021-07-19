package com.example.android.firebaseui_login_sample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.firebaseui_login_sample.R
import com.example.android.firebaseui_login_sample.datamodels.Workers
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import me.zhanghai.android.materialratingbar.MaterialRatingBar


class WorkersAdapter(query: Query?, private val mListener: OnWorkerSelectedListener) :
    FirestoreAdapter<WorkersAdapter.ViewHolder?>(query) {
    interface OnWorkerSelectedListener {
        fun onRestaurantSelected(restaurant: DocumentSnapshot?)
    }
//
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        return ViewHolder(inflater.inflate(R.layout.item_restaurant, parent, false))
        return ViewHolder(inflater.inflate(R.layout.fragment_profile_summary, parent, false))
    }
//
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(getSnapshot(position), mListener)
    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var imageView: ImageView
//        var nameView: TextView
//        var ratingBar: MaterialRatingBar
//        var numRatingsView: TextView
//        var priceView: TextView
//        var categoryView: TextView
//        var cityView: TextView
//        fun bind(
//            snapshot: DocumentSnapshot,
//            listener: OnWorkerSelectedListener?
//        ) {
//            val restaurant: Workers = snapshot.toObject(Workers::class.java)
//            val resources = itemView.resources
//
//            // Load image
//            Glide.with(imageView.context)
//                .load(restaurant.getPhoto())
//                .into(imageView)
//            nameView.setText(restaurant.getName())
//            ratingBar.setRating(restaurant.getAvgRating() as Float)
//            cityView.setText(restaurant.getCity())
//            categoryView.setText(restaurant.getCategory())
//            numRatingsView.text = resources.getString(
//                R.string.fmt_num_ratings,
//                restaurant.getNumRatings()
//            )
//            priceView.setText(RestaurantUtil.getPriceString(restaurant))
//
//            // Click listener
//            itemView.setOnClickListener { listener?.onRestaurantSelected(snapshot) }
//        }
//
//        init {
//            imageView = itemView.findViewById(R.id.restaurant_item_image)
//            nameView = itemView.findViewById(R.id.restaurant_item_name)
//            ratingBar = itemView.findViewById(R.id.restaurant_item_rating)
//            numRatingsView = itemView.findViewById(R.id.restaurant_item_num_ratings)
//            priceView = itemView.findViewById(R.id.restaurant_item_price)
//            categoryView = itemView.findViewById(R.id.restaurant_item_category)
//            cityView = itemView.findViewById(R.id.restaurant_item_city)
//        }
//    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}