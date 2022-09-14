package ade.yemi.croptocashassessment.Model

import com.google.gson.annotations.SerializedName

data class Exhibit(
    @SerializedName("title")val title: String? = null,
    @SerializedName("images")val images: ArrayList<String>
)