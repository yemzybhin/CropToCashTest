package ade.yemi.croptocashassessment.Adapter

import ade.yemi.croptocashassessment.Model.Exhibit
import ade.yemi.croptocashassessment.R
import ade.yemi.croptocashassessment.Utilities.ExpandedView
import ade.yemi.croptocashassessment.Utilities.clicking
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import java.io.Console

class ImageAdapter(var context: Context, var exhibit : Exhibit) :
        RecyclerView.Adapter<ImageAdapter.ItemHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): ItemHolder {
        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.image_layout, parent, false)
        return ItemHolder(itemHolder)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.title.text = exhibit.title

        var imageurl = exhibit.images[position]

        Glide.with(context).load(imageurl)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        e?.logRootCauses("Glide_Exception_Cause")
                        holder.image.setImageResource(R.drawable.noimage)
                        return true
                    }
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        return false
                    }
                })
                .centerCrop().into(holder.image)

        holder.itemView.setOnClickListener {
            holder.itemView.clicking()
            ExpandedView(context, exhibit, position)
        }
    }

    override fun getItemCount(): Int {
        return  exhibit.images.size
    }
    inner class ItemHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        var title = itemView.findViewById<TextView>(R.id.Exhibit_name)
        var image = itemView.findViewById<ImageView>(R.id.exhibit_image)
    }
}
