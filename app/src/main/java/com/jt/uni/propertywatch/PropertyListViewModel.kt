package com.jt.uni.propertywatch

import androidx.lifecycle.ViewModel
import com.jt.uni.propertywatch.database.PropertyRepository

class PropertyListViewModel : ViewModel()  {
    private val propertyRepository = PropertyRepository.get()
    val propertyList = propertyRepository.getProperties()
}