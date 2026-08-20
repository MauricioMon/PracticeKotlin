package com.testforwork.ejercicio1.feature.userlist.data.remote

import com.testforwork.ejercicio1.feature.userlist.data.remote.dto.RandomUserResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {

    @GET("api/")
    suspend fun getUsers(
        @Query("results") count: Int
    ): RandomUserResponseDto
}