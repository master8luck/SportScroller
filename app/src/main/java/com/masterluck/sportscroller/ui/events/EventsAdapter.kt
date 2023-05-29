package com.masterluck.sportscroller.ui.events

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.masterluck.sportscroller.R
import com.masterluck.sportscroller.databinding.ItemEventBinding
import com.masterluck.sportscroller.model.data.Event
import java.text.SimpleDateFormat
import java.util.Timer
import java.util.TimerTask

class EventsAdapter(
    private val eventsData: MutableList<Event>,
) : Adapter<EventsAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun getItemCount() = eventsData.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(position)

    }

    inner class EventViewHolder(val binding: ItemEventBinding) : ViewHolder(binding.root) {

        private var currentPosition = 0
        private var isTimerStarted = false

        @SuppressLint("NotifyDataSetChanged")
        fun bind(position: Int) {
            currentPosition = position
            val event = eventsData[currentPosition]
            binding.run {
                tvName.text = event.name
                tvTime.text = getTimeRemainingFormatted(eventsData[currentPosition].time)
                ivFavorite.setImageResource(chooseIcon(event.isFavorite))

                ivFavorite.setOnClickListener {
                    event.isFavorite = !event.isFavorite
                    ivFavorite.setImageResource(chooseIcon(event.isFavorite))
                    eventsData.sortBy { event ->
                        !event.isFavorite
                    }
                    notifyDataSetChanged()
                }
            }
            if (!isTimerStarted)
                startRefreshTimer()
        }

        private fun startRefreshTimer() {
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    Handler(Looper.getMainLooper()).post {
                        binding.tvTime.text =
                            getTimeRemainingFormatted(eventsData[currentPosition].time)
                    }
                }
            }, 0, 1000)
        }

    }

    private fun chooseIcon(isFavorite: Boolean): Int {
        return if (isFavorite)
            R.drawable.baseline_favorite_24
        else
            R.drawable.baseline_favorite_border_24
    }

    companion object {

        @SuppressLint("SimpleDateFormat")
        private val sdf = SimpleDateFormat("hh:mm:ss")

        private fun getTimeRemainingFormatted(time: Long): String {
            val timeRemaining = time * 1000 - System.currentTimeMillis()
            return if (timeRemaining > 0) sdf.format(timeRemaining) else "Started!"
        }

    }

}