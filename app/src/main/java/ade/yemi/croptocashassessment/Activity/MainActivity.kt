package ade.yemi.croptocashassessment.Activity

import ade.yemi.croptocashassessment.R
import ade.yemi.croptocashassessment.RestExhibitsLoader.RestExhibitsLoader
import ade.yemi.croptocashassessment.Utilities.SocialObject
import ade.yemi.croptocashassessment.Utilities.clicking
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var myInfoOpened = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.exhibits_recycler)
        val unloadedDataView = findViewById<LinearLayout>(R.id.unloadedDataView)
        val reload = findViewById<TextView>(R.id.reloadData)
        val myInfo = findViewById<TextView>(R.id.my_info)

        val myInfoLayout = findViewById<LinearLayout>(R.id.my_info_layout)
        val cancelMyInfo = findViewById<CardView>(R.id.cancel_myInfo)
        val firstTimePrompt = findViewById<TextView>(R.id.firsttime_prompt)

        //socials
        val whatsApp = findViewById<ImageView>(R.id.whatsApp_logo)
        val instagram = findViewById<ImageView>(R.id.instagram_logo)
        socials(whatsApp, instagram)

        myInfoLayout.visibility = View.GONE
        firstTimePrompt.visibility = View.GONE

        var restExhibitsLoader = RestExhibitsLoader()
        restExhibitsLoader.callAllData(this, unloadedDataView, firstTimePrompt ,recyclerView)

        reload.setOnClickListener {
            reload.clicking()
            unloadedDataView.visibility = View.VISIBLE
            restExhibitsLoader.callAllData(this, unloadedDataView , firstTimePrompt ,recyclerView)
        }

        myInfo.setOnClickListener {
            myInfo.clicking()
            if (myInfoOpened == false){
                myInfoLayout.visibility = View.VISIBLE
                myInfoOpened = true
            }else{
                myInfoLayout.visibility = View.GONE
                myInfoOpened = false
            }

        }

        cancelMyInfo.setOnClickListener {
            cancelMyInfo.clicking()
            myInfoLayout.visibility = View.GONE
            myInfoOpened = false
        }
    }

    private fun socials(whatsapp : ImageView, instagram : ImageView){
        val animation = AnimationUtils.loadAnimation(this, R.anim.adscale)

        var socials = listOf<SocialObject>(
                SocialObject(whatsapp, "https://wa.me/+2347088823701"),
                SocialObject(instagram, "https://www.instagram.com/yeminition_tv/")
        )

        for (i in socials){
            i.socialIcon.clicking()
            i.socialIcon.startAnimation(animation)
            i.socialIcon.setOnClickListener {
                runCatching {
                    val uriUri = Uri.parse(i.socialLink)
                    val launchBrowser = Intent(Intent.ACTION_VIEW, uriUri)
                    startActivity(launchBrowser)
                }.onSuccess {
                    Toast.makeText(this, "You are leaving this App", Toast.LENGTH_SHORT).show()
                }.onFailure {
                    Toast.makeText(this, "Could not launch intent", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}