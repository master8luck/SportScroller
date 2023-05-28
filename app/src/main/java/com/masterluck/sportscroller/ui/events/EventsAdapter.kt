package com.masterluck.sportscroller.ui.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.masterluck.sportscroller.databinding.ItemEventBinding
import com.masterluck.sportscroller.model.data.Sport

class EventsAdapter(
    private var sportsData: List<Sport>,
) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun getItemCount() = sportsData.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.binding.tvName.text = sportsData[position].toString()
    }

    class EventViewHolder(val binding: ItemEventBinding): ViewHolder(binding.root)

    fun setupData(data: List<Sport>) {
        sportsData = data
        notifyDataSetChanged()
    }

}