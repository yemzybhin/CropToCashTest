package ade.yemi.croptocashassessment.Model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface  ExhibitsLoader{
    @GET("Reyst/exhibit_db/list")
    fun getExhibitList():Call<List<Exhibit?>>?

    companion object{
        const val BASE_URL = "https://my-json-server.typicode.com"
    }
}