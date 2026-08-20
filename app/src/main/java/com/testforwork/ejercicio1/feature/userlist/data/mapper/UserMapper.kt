package com.testforwork.ejercicio1.feature.userlist.data.mapper

import com.testforwork.ejercicio1.feature.userlist.data.remote.dto.UserDto
import com.testforwork.ejercicio1.feature.userlist.domain.model.User

fun UserDto.toDomain(): User {
    return User(
        id = login.uuid,
        fullName = "${name.first} ${name.last}",
        email = email,
        phone = phone,
        country = location.country,
        city = location.city,
        pictureUrl = picture.large,
        latitude = location.coordinates.latitude,
        longitude = location.coordinates.longitude
    )
}