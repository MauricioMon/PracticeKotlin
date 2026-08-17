package com.testforwork.ejercicio1.domain.model

data class User(
    val id: String,
    val fullName: String,
    val email: String,
    val phone: String,
    val country: String,
    val city: String,
    val pictureUrl: String,
    val latitude: String,
    val longitude: String
)