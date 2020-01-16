package dev.kowalski.independentmvvm.api.repository

import androidx.lifecycle.MutableLiveData
import dev.kowalski.independentmvvm.api.api.model.LyricsSearchResult
import dev.kowalski.independentmvvm.api.retrofit.LyricsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LyricsSearchRepository {

    private val api = Retrofit.Builder()
        .baseUrl("https://api.lyrics.ovh")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(LyricsApi::class.java)

    val searchResult = MutableLiveData<LyricsSearchResult>()

    fun getLyrics(artist: String, title: String) {
        api.getLyrics(artist, title).enqueue(object : Callback<LyricsSearchResult> {
            override fun onFailure(call: Call<LyricsSearchResult>?, t: Throwable?) {
                // ToDo Implement Error case
            }

            override fun onResponse(
                call: Call<LyricsSearchResult>?,
                response: Response<LyricsSearchResult>?
            ) {
                searchResult.value = response!!.body()!!
            }
        })
    }

}