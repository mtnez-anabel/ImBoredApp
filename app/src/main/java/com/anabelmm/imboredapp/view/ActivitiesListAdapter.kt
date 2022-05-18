package com.anabelmm.imboredapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.anabelmm.imboredapp.R
import com.anabelmm.imboredapp.model.ActivityCard

class ActivitiesListAdapter(private var activities: List<ActivityCard?>) :
    RecyclerView.Adapter<ActivitiesListAdapter.ActivitiesListViewHolder>() {
    private var expandPosition = -1

    inner class ActivitiesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivitiesListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ActivitiesListViewHolder(
            layoutInflater.inflate(
                R.layout.item_activity,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ActivitiesListViewHolder, position: Int) {
        val itemCard = activities[position]
        val activityItemTextView: TextView = holder.itemView.findViewById(R.id.item_textView)
        val accessibilityItemTextView: TextView =
            holder.itemView.findViewById(R.id.text_accessibility)
        val participantsItemTextView: TextView =
            holder.itemView.findViewById(R.id.text_participants)
        val typeItemTextView: TextView = holder.itemView.findViewById(R.id.text_type)
        val priceItemTextView: TextView = holder.itemView.findViewById(R.id.text_price)
        val keyItemTextView: TextView = holder.itemView.findViewById(R.id.text_key)
        val linkItemTextView: TextView = holder.itemView.findViewById(R.id.text_link2)
        val expandedView: LinearLayout = holder.itemView.findViewById(R.id.expanded_linearLayout)
        val cardLayout: CardView = holder.itemView.findViewById(R.id.item_cardView)

        if (itemCard != null) {
            activityItemTextView.text = itemCard.activity
            accessibilityItemTextView.text = itemCard.accessibility.toString()
            participantsItemTextView.text = itemCard.participants.toString()
            typeItemTextView.text = itemCard.type
            priceItemTextView.text = itemCard.price.toString()
            keyItemTextView.text = itemCard.price.toString()
            linkItemTextView.text = itemCard.link
            expandedView.visibility = if (expandPosition == position) View.VISIBLE else View.GONE

            cardLayout.setOnClickListener {
                expandPosition = if (expandPosition == position) -1 else position
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int = activities.size

}
