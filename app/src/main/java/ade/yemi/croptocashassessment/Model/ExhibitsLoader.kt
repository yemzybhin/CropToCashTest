package ade.yemi.croptocashassessment.Model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface  ExhibitsLoader{
    @get:GET("Reyst/exhibit_db/list")
    val allExhibits: Call<List<Exhibit?>>?

    companion object{
        const val BASE_URL = "https://my-json-server.typicode.com"

        fun getExhibitList():Call<List<Exhibit?>>?{
            var builder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            var API = builder.create(ExhibitsLoader::class.java)
            var listItems =API.allExhibits
            return listItems
        }
    }
}