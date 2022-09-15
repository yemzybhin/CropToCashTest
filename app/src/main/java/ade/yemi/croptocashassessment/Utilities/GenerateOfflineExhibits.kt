package ade.yemi.croptocashassessment.Utilities

import ade.yemi.croptocashassessment.Model.Exhibit
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun GenerateOfflineExhibits(context: Context, jsonString : String) :  List<Exhibit>{
    val jsonFileString = jsonString
    val gson = Gson()
    val exhibitsType = object : TypeToken<List<Exhibit>>() {}.type
    var offlineExhibits:  List<Exhibit> = gson.fromJson(jsonFileString, exhibitsType)
    return offlineExhibits
}