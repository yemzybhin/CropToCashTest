package ade.yemi.croptocashassessment.Activity

import ade.yemi.croptocashassessment.R
import ade.yemi.croptocashassessment.RestExhibitsLoader.RestExhibitsLoader
import ade.yemi.croptocashassessment.Utilities.clicking
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var myInfoOpened = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView = findViewById<RecyclerView>(R.id.exhibits_recycler)
        var unloadedDataView = findViewById<LinearLayout>(R.id.unloadedDataView)
        var reload = findViewById<TextView>(R.id.reloadData)
        var myInfo = findViewById<TextView>(R.id.my_info)

        var myInfoLayout = findViewById<LinearLayout>(R.id.my_info_layout)
        var cancelMyInfo = findViewById<CardView>(R.id.cancel_myInfo)
        var firstTimePrompt = findViewById<TextView>(R.id.firsttime_prompt)

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
}