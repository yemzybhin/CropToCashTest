package ade.yemi.croptocashassessment.Data

import android.content.Context
import android.content.SharedPreferences

class SavedInfo {
    var context: Context? = null
    var sharef: SharedPreferences? = null
    constructor(context: Context){
        this.context = context
        this.sharef = context.getSharedPreferences("PreferenceStuff", Context.MODE_PRIVATE)
    }

    //Shared preference for the exhibits saved offline after the object has been converted to a JSON string
    fun setSavedInfo(savedInfo: String){
        var editor = sharef!!.edit()
        editor.putString("SavedInfo", savedInfo)
        editor.commit()
    }
    fun getSavedInfo(): String?{
        return sharef!!.getString("SavedInfo", "")
    }

    //First time opening the app. A prompt to let user know that each image is clickable for a broader view
    fun setFirstTime(firsttime: Boolean){
        var editor = sharef!!.edit()
        editor.putBoolean("FirstTime", firsttime)
        editor.commit()
    }
    fun getFirstTime(): Boolean{
        return sharef!!.getBoolean("FirstTime", false)
    }
}