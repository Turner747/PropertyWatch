package com.jt.uni.propertywatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.jt.uni.propertywatch.database.Property
import com.jt.uni.propertywatch.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    var mMessage: String? = null
    var mLatitude = 0.0
    var mLongitude = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent != null && intent.hasExtra("property_data"))
        {
            val property = intent.getSerializableExtra("property_data") as Property

            mMessage = "$" + property.price.toString()
            mLatitude = property.lat
            mLongitude = property.lon
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val latLon = LatLng(mLatitude, mLongitude)

        mMap.addMarker(MarkerOptions().position(latLon).title(mMessage))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLon))
        mMap.setMinZoomPreference(12f)
        mMap.setMaxZoomPreference(12f)
    }
}