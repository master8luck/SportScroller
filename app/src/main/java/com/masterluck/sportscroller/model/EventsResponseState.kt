package com.masterluck.sportscroller.model

import com.masterluck.sportscroller.model.data.Sport

sealed class EventsResponseState {
    object StateLoading : EventsResponseState()
    class StateSuccess(val sportsData: List<Sport>) : EventsResponseState()
    class StateError(val message: String) : EventsResponseState()
}
