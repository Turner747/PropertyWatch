package com.jt.uni.propertywatch.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable
import java.util.*

@Entity
data class Property(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    @NotNull var address: String,
    var price: Int = 0,
    @NotNull var phone: String,
    var lat: Double,
    var lon: Double
) : Serializable
