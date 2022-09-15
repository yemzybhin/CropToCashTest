package ade.yemi.croptocashassessment.RestExhibitsLoader

import ade.yemi.croptocashassessment.Adapter.ExhibitAdapter
import ade.yemi.croptocashassessment.Data.SavedInfo
import ade.yemi.croptocashassessment.Model.Exhibit
import ade.yemi.croptocashassessment.Model.ExhibitsLoader
import ade.yemi.croptocashassessment.Utilities.ExhibitsToJson
import ade.yemi.croptocashassessment.Utilities.GenerateOfflineExhibits
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestExhibitsLoader {
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var  myAdapter: RecyclerView.Adapter<*>

    fun callAllData(context: Context, linearLayout: LinearLayout, recyclerView: RecyclerView){
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
                //saving for offline view
                var jsonExhibit = ExhibitsToJson(exhibits!!)
                SavedInfo(context).setSavedInfo(jsonExhibit)
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

                } else {
                    Toast.makeText(context, "Could not load Exhibits. Check connection", Toast.LENGTH_SHORT).show()
                }

            }
        })
    }


}