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
    fun setSavedInfo(savedInfo: String){
        var editor = sharef!!.edit()
        editor.putString("SavedInfo", savedInfo)
        editor.commit()
    }
    fun getSavedInfo(): String?{
        return sharef!!.getString("SavedInfo", "")
    }

    fun setFirstTime(firsttime: Boolean){
        var editor = sharef!!.edit()
        editor.putBoolean("FirstTime", firsttime)
        editor.commit()
    }
    fun getFirstTime(): Boolean{
        return sharef!!.getBoolean("FirstTime", false)
    }
}