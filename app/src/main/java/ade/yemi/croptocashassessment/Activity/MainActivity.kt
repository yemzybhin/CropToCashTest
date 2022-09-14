package ade.yemi.croptocashassessment.Activity

import ade.yemi.croptocashassessment.R
import ade.yemi.croptocashassessment.RestExhibitsLoader.RestExhibitsLoader
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView = findViewById<RecyclerView>(R.id.exhibits_recycler)
        var unloadedDataView = findViewById<LinearLayout>(R.id.unloadedDataView)

        var restExhibitsLoader = RestExhibitsLoader()
        restExhibitsLoader.callAllData(this, unloadedDataView ,recyclerView)

    }
}