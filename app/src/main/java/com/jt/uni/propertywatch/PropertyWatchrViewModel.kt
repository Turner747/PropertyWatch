package com.jt.uni.propertywatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jt.uni.propertywatch.api.PropertyItem

class PropertyWatchrViewModel : ViewModel() {

    val propertyItemLiveData: LiveData<List<PropertyItem>>

    init {
        propertyItemLiveData = WatchrFetchr().fetchProperties()
    }

}