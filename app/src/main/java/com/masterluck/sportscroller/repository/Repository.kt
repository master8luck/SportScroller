package com.masterluck.sportscroller.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.masterluck.sportscroller.model.EventsResponseState
import com.masterluck.sportscroller.retrofit.EventsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import javax.net.ssl.SSLException

@Singleton
class Repository @Inject constructor(
    private val eventsApi: EventsApi,
) {

    private val _eventsLiveData = MutableLiveData<EventsResponseState>()
    val eventsLiveData = _eventsLiveData as LiveData<EventsResponseState>

    init {
        GlobalScope.launch(Dispatchers.IO) {
            getEventsFromApi()
        }
    }

    private suspend fun getEventsFromApi() {
        _eventsLiveData.postValue(EventsResponseState.StateLoading)

        // try/catch block to prevent crashing on emulator due to SSLException
        try {
            val response = eventsApi.getEvents()

            // for loading screen demonstration only
            delay(1000)

            if (response.isSuccessful && response.body() != null) {
                _eventsLiveData.postValue(EventsResponseState.StateSuccess(response.body()!!))
            } else {
                _eventsLiveData.postValue(EventsResponseState.StateError(response.message()))
            }
        } catch (e: SSLException) {
            _eventsLiveData.postValue(EventsResponseState.StateError(e.toString()))
        }

    }

}