package com.jt.uni.propertywatch.api

import com.google.gson.annotations.SerializedName

class PropertyResponse {
    @SerializedName("property")
    lateinit var propertyItems: List<PropertyItem>
}
