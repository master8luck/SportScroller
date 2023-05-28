package com.masterluck.sportscroller.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.masterluck.sportscroller.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    val eventsLiveData = repository.eventsLiveData

}