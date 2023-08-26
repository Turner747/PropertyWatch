package com.jt.uni.propertywatch.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.jt.uni.propertywatch.MapsActivity
import com.jt.uni.propertywatch.R
import com.jt.uni.propertywatch.database.Property

class PropertyAdapter(var properties: List<Property>) : RecyclerView.Adapter<PropertyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false);

        val viewModelStoreOwner = parent.context as ViewModelStoreOwner

        return ViewHolder(view)
    }

    override fun getItemCount() = properties.size

    override fun onBindViewHolder(propertyHolder: ViewHolder, position: Int) {

        val property = properties[position]
        propertyHolder.bind(property)
    }


    inner class ViewHolder (val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        lateinit var property: Property
        private var v = view

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {

            val intent = Intent(v.context, MapsActivity::class.java)
            intent.putExtra("property_data", property)

            v.context.startActivity(intent)
        }


        fun bind(property: Property) {
            this.property = property

            val addressView: TextView = view.findViewById(R.id.address)
            val priceView: TextView = view.findViewById(R.id.price)
            val phoneView: TextView = view.findViewById(R.id.phone)
            val emailButton: Button = view.findViewById(R.id.emailButton)

            addressView.text = property.address
            priceView.text = "Price: $" + property.price
            phoneView.text = "Phone: " + property.phone

            emailButton.setOnClickListener {
                sendEmail()
            }
        }

        private fun sendEmail(){
            var emailSubject = v.context.resources.getString(R.string.email_subject)
            var emailBody = v.context.resources.getString(R.string.email_body, property.address, property.price.toString())


            val intent = Intent(Intent.ACTION_SEND)

            intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject)
            intent.putExtra(Intent.EXTRA_TEXT, emailBody)

            intent.type = "text/plain"

            v.context.startActivity(Intent.createChooser(intent, "Send using: "))
        }
    }
}