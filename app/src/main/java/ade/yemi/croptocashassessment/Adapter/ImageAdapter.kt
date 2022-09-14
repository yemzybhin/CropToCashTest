package ade.yemi.croptocashassessment.Adapter

import ade.yemi.croptocashassessment.Model.Exhibit
import ade.yemi.croptocashassessment.R
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImageAdapter(var context: Context, var exhibit : Exhibit) :
        RecyclerView.Adapter<ImageAdapter.ItemHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): ItemHolder {
        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.image_layout, parent, false)
        return ItemHolder(itemHolder)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.title.text = exhibit.title

        var imageurl = exhibit.images[position]
        Glide.with(context).load(imageurl).centerCrop().into(holder.image)
    }

    override fun getItemCount(): Int {
        return  exhibit.images.size
    }
    inner class ItemHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        var title = itemView.findViewById<TextView>(R.id.Exhibit_name)
        var image = itemView.findViewById<ImageView>(R.id.exhibit_image)
    }
}
