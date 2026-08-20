package com.testforwork.ejercicio1.feature.userlist.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RandomUserResponseDto(
    @SerializedName("results") val results: List<UserDto>
)

data class UserDto(
    @SerializedName("name") val name: NameDto,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("location") val location: LocationDto,
    @SerializedName("picture") val picture: PictureDto,
    @SerializedName("login") val login: LoginDto
)

data class NameDto(
    @SerializedName("first") val first: String,
    @SerializedName("last") val last: String
)

data class LocationDto(
    @SerializedName("country") val country: String,
    @SerializedName("city") val city: String,
    @SerializedName("coordinates") val coordinates: CoordinatesDto
)

data class CoordinatesDto(
    @SerializedName("latitude") val latitude: String,
    @SerializedName("longitude") val longitude: String
)

data class PictureDto(
    @SerializedName("large") val large: String
)

data class LoginDto(
    @SerializedName("uuid") val uuid: String
)