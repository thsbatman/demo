package com.example.demo

class LocationAdapter {
    package com.example.demo

    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.TextView
    import androidx.recyclerview.widget.RecyclerView

    class LocationAdapter(private val locations: List<Location>) :
        RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_2, parent, false)
            return LocationViewHolder(view)
        }

        override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
            val location = locations[position]
            holder.nameText.text = location.name
            holder.coordText.text = "Lat: ${location.latitude}, Lng: ${location.longitude}"
        }

        override fun getItemCount(): Int = locations.size

        class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val nameText: TextView = itemView.findViewById(android.R.id.text1)
            val coordText: TextView = itemView.findViewById(android.R.id.text2)
        }
    }

}