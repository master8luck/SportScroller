package com.masterluck.sportscroller.viewmodel

import androidx.lifecycle.ViewModel
import com.masterluck.sportscroller.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SportEventsViewModel @Inject constructor(
    repository: Repository,
) : ViewModel() {

    val eventsLiveData = repository.eventsLiveData

}