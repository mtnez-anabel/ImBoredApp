package com.anabelmm.imboredapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anabelmm.imboredapp.R
import com.anabelmm.imboredapp.model.ActivityCard

class ActivitiesListAdapter(private var activities: List<ActivityCard?>) :
    RecyclerView.Adapter<ActivitiesListViewHolder>() {
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
        val items = activities[position]
        holder.bind(items)
    }

    override fun getItemCount(): Int = activities.size

    fun updateActivities(activities: List<ActivityCard?>) {
        this.activities = activities
        notifyDataSetChanged()
    }


}

class ActivitiesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val activityItemTextView: TextView = itemView.findViewById(R.id.item_textView)

    fun bind(a: ActivityCard?) {
        if (a != null) {
            activityItemTextView.text = a.activity
        }
    }

    companion object {
        fun create(parent: ViewGroup): ActivitiesListViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_list_activities, parent, false)
            return ActivitiesListViewHolder(view)
        }
    }
}

