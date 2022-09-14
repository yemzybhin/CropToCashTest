package ade.yemi.croptocashassessment.RestExhibitsLoader

import ade.yemi.croptocashassessment.Adapter.ExhibitAdapter
import ade.yemi.croptocashassessment.Model.Exhibit
import ade.yemi.croptocashassessment.Model.ExhibitsLoader
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
        var exhibitLoaderInstance = ExhibitsLoader.getExhibitList()
        var call = exhibitLoaderInstance


      //  Toast.makeText(context, "Entered", Toast.LENGTH_SHORT).show()
        call?.enqueue(object : Callback<List<Exhibit?>?> {
            override fun onResponse(call: Call<List<Exhibit?>?>, response: Response<List<Exhibit?>?>) {

                Toast.makeText(context, "1", Toast.LENGTH_SHORT).show()
                var exhibits: List<Exhibit>? = response.body() as List<Exhibit>
                linearLayout.visibility = View.GONE

                manager = LinearLayoutManager(context)
                recyclerView.apply {
                    myAdapter = ExhibitAdapter(exhibits!!)
                    layoutManager = manager
                    adapter = myAdapter
                }
            }

            override fun onFailure(call: Call<List<Exhibit?>?>, t: Throwable) {
                linearLayout.visibility = View.GONE
                Toast.makeText(context, "${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }


}