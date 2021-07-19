package com.example.android.firebaseui_login_sample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.firebaseui_login_sample.R
import com.example.android.firebaseui_login_sample.datamodels.Rating
import com.google.firebase.firestore.Query
import me.zhanghai.android.materialratingbar.MaterialRatingBar

class RatingAdapter(query: Query?) :
    FirestoreAdapter<RatingAdapter.ViewHolder?>(query) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_profile_summary, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(getSnapshot(position).toObject(Rating::class.java))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var nameView: TextView
//        var ratingBar: MaterialRatingBar
//        var textView: TextView
//        fun bind(rating: Rating?) {
//            nameView.setText(rating.getUserName())
//            ratingBar.setRating(rating.getRating() as Float)
//            textView.setText(rating.getText())
//        }
//
//        init {
//            nameView = itemView.findViewById(R.id.rating_item_name)
//            ratingBar = itemView.findViewById(R.id.rating_item_rating)
//            textView = itemView.findViewById(R.id.rating_item_text)
//        }
    }
}