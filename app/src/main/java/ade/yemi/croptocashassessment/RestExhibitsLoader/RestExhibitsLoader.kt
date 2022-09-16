package ade.yemi.croptocashassessment.RestExhibitsLoader

import ade.yemi.croptocashassessment.Adapter.ExhibitAdapter
import ade.yemi.croptocashassessment.Data.SavedInfo
import ade.yemi.croptocashassessment.Model.Exhibit
import ade.yemi.croptocashassessment.Model.ExhibitsLoader
import ade.yemi.croptocashassessment.R
import ade.yemi.croptocashassessment.Utilities.ExhibitsToJson
import ade.yemi.croptocashassessment.Utilities.GenerateOfflineExhibits
import ade.yemi.croptocashassessment.Utilities.clicking
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.logging.Handler

class RestExhibitsLoader {
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var  myAdapter: RecyclerView.Adapter<*>

    fun callAllData(context: Context, linearLayout: LinearLayout, firsttimepropt : TextView, recyclerView: RecyclerView){
        manager = LinearLayoutManager(context)

        var exhibitLoaderInstance = ExhibitsLoader.getExhibitList()
        var call = exhibitLoaderInstance

        call?.enqueue(object : Callback<List<Exhibit?>?> {
            override fun onResponse(call: Call<List<Exhibit?>?>, response: Response<List<Exhibit?>?>) {

               // Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
                var exhibits: List<Exhibit>? = response.body() as List<Exhibit>
                linearLayout.visibility = View.GONE
                recyclerView.apply {
                    myAdapter = ExhibitAdapter(exhibits!!)
                    layoutManager = manager
                    adapter = myAdapter
                }

                runCatching {
                    var jsonExhibit = ExhibitsToJson(exhibits!!)
                    SavedInfo(context).setSavedInfo(jsonExhibit)
                }.onSuccess {
                    var notificationText = "Online Exhibit. Data successfully\nsaved For offline view. Put off\nInternet and reload to Confirm"
                    offlineLoadNotification(context, notificationText)
                }.onFailure {
                    var notificationText = "Data could not be saved\nFor offline view"
                    offlineLoadNotification(context, notificationText)
                }

                android.os.Handler().postDelayed({
                    var animation = AnimationUtils.loadAnimation(context, R.anim.slide_in)
                    var firstTimeValue = SavedInfo(context).getFirstTime()
                    if (firstTimeValue == false){
                        firsttimepropt.visibility = View.VISIBLE
                        firsttimepropt.startAnimation(animation)
                        SavedInfo(context).setFirstTime(true)
                        android.os.Handler().postDelayed({
                            firsttimepropt.clicking()
                            firsttimepropt.visibility = View.GONE
                        },5000)
                    }
                },8000)


            }

            override fun onFailure(call: Call<List<Exhibit?>?>, t: Throwable) {
                linearLayout.visibility = View.GONE
                var jsonExhibit = SavedInfo(context).getSavedInfo()
                if (jsonExhibit != "") {
                    var offlineExhibits = GenerateOfflineExhibits(context, jsonExhibit!!)

                    recyclerView.apply {
                        myAdapter = ExhibitAdapter(offlineExhibits!!)
                        layoutManager = manager
                        adapter = myAdapter
                    }

                    var notificationText = "You are viewing the saved offline\nExhibits. Put on internet and reload\nfor online Exhibits"
                    offlineLoadNotification(context, notificationText)

                } else {
                    var notificationText = "Check your internet\nconnection and reload."
                    offlineLoadNotification(context, notificationText)
                }

            }
        })
    }

    private fun offlineLoadNotification(context: Context, notifyText : String){
        var load = Dialog(context)
        load.setCancelable(true)
        load.setContentView(R.layout.offline_notification)
        load.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        load.show()

        var animation = AnimationUtils.loadAnimation(context, R.anim.slide_in2)
        var notificationText = load.findViewById<TextView>(R.id.tv_notificationtextid)
        var cancel = load.findViewById<ImageView>(R.id.notification_cancel)
        notificationText.startAnimation(animation)
        notificationText.text = notifyText
        android.os.Handler().postDelayed({
            load.dismiss()
        },7000)

        cancel.setOnClickListener {
            cancel.clicking()
            load.dismiss()
        }

    }


}