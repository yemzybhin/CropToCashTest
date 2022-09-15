package ade.yemi.croptocashassessment.Utilities

import ade.yemi.croptocashassessment.Model.Exhibit
import com.google.gson.Gson

fun ExhibitsToJson(exhibitDetails: List<Exhibit>) : String{
    val gson = Gson()
    val json = gson.toJson(exhibitDetails)
    return json
}
