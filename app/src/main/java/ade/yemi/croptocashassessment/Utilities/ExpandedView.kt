package ade.yemi.croptocashassessment.Utilities

import ade.yemi.croptocashassessment.Model.Exhibit
import ade.yemi.croptocashassessment.R
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun ExpandedView(context: Context, exhibit: Exhibit, currentIndex : Int){
    var load = Dialog(context)
    load.setCancelable(true)
    load.setContentView(R.layout.expandedview)
    load.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    load.show()

    var currentPosition = currentIndex

    val title = load.findViewById<TextView>(R.id.item_title)
    val image = load.findViewById<ImageView>(R.id.item_image)
    val previous = load.findViewById<CardView>(R.id.previous_image)
    val next = load.findViewById<CardView>(R.id.next_image)
    val cancel = load.findViewById<ImageView>(R.id.cancel_expansion)

    val imagesUrl = exhibit.images

    title.text = exhibit.title

    Glide.with(context).load(imagesUrl[currentPosition])
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    image.setImageResource(R.drawable.noimage)
                    return true
                }
                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    return false
                }
            }).into(image)
    when(currentPosition){
        0 ->{
            previous.visibility = View.GONE
            next.visibility = View.VISIBLE
        }
        imagesUrl.size - 1 -> {
            previous.visibility = View.VISIBLE
            next.visibility = View.GONE
        }
    }

    previous.setOnClickListener {
        previous.vibrate()
        if (currentPosition > 0){
            previous.visibility = View.VISIBLE
            next.visibility = View.VISIBLE
            currentPosition--
            if (currentPosition == 0){
                previous.visibility = View.GONE
                next.visibility = View.VISIBLE
            }
            Glide.with(context).load(imagesUrl[currentPosition])
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                            image.setImageResource(R.drawable.noimage)
                            return true
                        }
                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            return false
                        }
                    }).into(image)
        }
    }

    next.setOnClickListener {
        next.vibrate()
        if (currentPosition < exhibit.images.size -1){
            previous.visibility = View.VISIBLE
            next.visibility = View.VISIBLE
            currentPosition++
            if (currentPosition == exhibit.images.size - 1){
                previous.visibility = View.VISIBLE
                next.visibility = View.GONE
            }
            Glide.with(context).load(imagesUrl[currentPosition])
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                            image.setImageResource(R.drawable.noimage)
                            return true
                        }
                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            return false
                        }
                    }).into(image)
        }
    }

    cancel.setOnClickListener {
        cancel.clicking()
        load.dismiss()
    }
}