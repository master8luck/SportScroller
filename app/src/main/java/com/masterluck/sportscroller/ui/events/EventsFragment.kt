package com.masterluck.sportscroller.ui.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.masterluck.sportscroller.databinding.FragmentEventsBinding
import com.masterluck.sportscroller.model.EventsResponseState
import com.masterluck.sportscroller.model.data.Sport
import com.masterluck.sportscroller.viewmodel.EventsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventsFragment : Fragment() {

    private lateinit var binding: FragmentEventsBinding
    private val viewModel: EventsViewModel by viewModels()

    private lateinit var adapter: EventsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = EventsAdapter(emptyList())
        binding.rvSports.adapter = adapter

        viewModel.eventsLiveData.observe(viewLifecycleOwner) { response ->
            when(response) {
                is EventsResponseState.StateLoading -> showLoading()
                is EventsResponseState.StateSuccess -> showData(response.sportsData)
                is EventsResponseState.StateError -> showErrorMessage(response.message)
            }
        }

    }

    private fun showData(sportsData: List<Sport>) {
        binding.flLoading.isVisible = false
        binding.tvError.isVisible = false
        binding.rvSports.isVisible = true
        adapter.setupData(sportsData)
    }

    private fun showErrorMessage(message: String) {
        binding.flLoading.isVisible = false
        binding.tvError.isVisible = true
        binding.rvSports.isVisible = false
        binding.tvError.text = message

    }

    private fun showLoading() {
        binding.flLoading.isVisible = true
    }
}