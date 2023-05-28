package com.masterluck.sportscroller.retrofit

import com.masterluck.sportscroller.model.data.Sport
import retrofit2.Response
import retrofit2.http.GET

interface EventsApi {

    @GET("api/v1/demo")
    suspend fun getEvents(): Response<List<Sport>>

}