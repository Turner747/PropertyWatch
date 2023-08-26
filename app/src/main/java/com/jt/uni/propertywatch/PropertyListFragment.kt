package com.jt.uni.propertywatch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jt.uni.propertywatch.adapters.PropertyAdapter
import com.jt.uni.propertywatch.api.PropertyItem
import com.jt.uni.propertywatch.database.Property
import com.jt.uni.propertywatch.database.PropertyRepository

private const val TAG = "PropertyListFragment"

class PropertyListFragment : Fragment() {

    private lateinit var mPropertyListViewModel: PropertyListViewModel
    private lateinit var mPropertyWatchrViewModel: PropertyWatchrViewModel

    companion object {
        fun newInstance() = PropertyListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = activity as ViewModelStoreOwner
        mPropertyListViewModel = ViewModelProvider(context).get(PropertyListViewModel::class.java)

        val watchrLiveData: LiveData<List<PropertyItem>> = WatchrFetchr().fetchProperties()
        watchrLiveData.observe(
            this,
            Observer { propertyItems ->
                var properties: MutableList<Property> = mutableListOf()
                Log.d(TAG, "Response received: $propertyItems")
                for (property in propertyItems){
                    var prop = Property(
                        property.id,
                        property.address,
                        property.price,
                        property.phone,
                        property.lat,
                        property.lon
                    )
                    properties.add(prop)
                }
                PropertyRepository.get().addProperties(properties)
            })
        mPropertyWatchrViewModel = ViewModelProvider(context).get(PropertyWatchrViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val recyclerView = inflater.inflate(R.layout.fragment_list, container, false) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        mPropertyListViewModel.propertyList.observe (
            viewLifecycleOwner, Observer { propertyList ->

                if (propertyList.isEmpty()) {
                    PropertyRepository.loadData()   // triggers observer to run again as data will change
                    return@Observer
                }
                recyclerView.adapter = PropertyAdapter(propertyList)
            })

        return recyclerView
    }

}