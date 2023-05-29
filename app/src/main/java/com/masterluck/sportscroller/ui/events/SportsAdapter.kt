package com.masterluck.sportscroller.ui.events

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.masterluck.sportscroller.R
import com.masterluck.sportscroller.databinding.ItemSportBinding
import com.masterluck.sportscroller.model.data.Sport

class SportsAdapter(
    private var sportsData: List<Sport>,
) : RecyclerView.Adapter<SportsAdapter.SportViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportViewHolder {
        val binding = ItemSportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SportViewHolder(binding)
    }

    override fun getItemCount() = sportsData.size

    override fun onBindViewHolder(holder: SportViewHolder, position: Int) {

        val sport = sportsData[position]

        holder.binding.run {
            tvSportName.text = sport.name
            ivCollapse.setImageResource(chooseIcon(sport.isCollapsed))
            rvEvents.adapter = EventsAdapter(sport.events)
            rvEvents.isVisible = !sport.isCollapsed

            ivCollapse.setOnClickListener {
                sport.isCollapsed = !sport.isCollapsed
                rvEvents.isVisible = !sport.isCollapsed
                ivCollapse.setImageResource(chooseIcon(sport.isCollapsed))
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setupData(data: List<Sport>) {
        sportsData = data
        notifyDataSetChanged()
    }

    class SportViewHolder(val binding: ItemSportBinding): ViewHolder(binding.root)

    private fun chooseIcon(isCollapsed: Boolean): Int {
        return if (isCollapsed)
            R.drawable.baseline_keyboard_arrow_down_24
        else
            R.drawable.baseline_keyboard_arrow_up_24
    }

}