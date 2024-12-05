package com.example.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var recyclerView: RecyclerView
    private lateinit var locationAdapter: LocationAdapter
    private val locations = mutableListOf<Location>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        locationAdapter = LocationAdapter(locations)
        recyclerView.adapter = locationAdapter

        // Load Google Maps
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Generate random locations
        generateRandomLocations()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add markers to map
        locations.forEach { location ->
            val latLng = LatLng(location.latitude, location.longitude)
            mMap.addMarker(MarkerOptions().position(latLng).title(location.name))
        }

        // Move camera to center of Indonesia
        val indonesiaCenter = LatLng(-2.5489, 118.0149)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(indonesiaCenter, 5f))
    }

    private fun generateRandomLocations() {
        val randomNames = listOf("Recycle Center A", "Recycle Center B", "Recycle Center C")
        for (i in 1..10) {
            val randomLat = (-10..6).random() + Math.random()
            val randomLng = (95..141).random() + Math.random()
            val name = randomNames.random()
            locations.add(Location(name, randomLat, randomLng))
        }

        // Notify adapter
        locationAdapter.notifyDataSetChanged()
    }
}

data class Location(val name: String, val latitude: Double, val longitude: Double)
