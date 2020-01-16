package dev.kowalski.independentmvvm.api.retrofit

import dev.kowalski.independentmvvm.api.api.model.LyricsSearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LyricsApi {

    @GET("/v1/{artist}/{title}")
    fun getLyrics(@Path("artist") artist: String, @Path("title") title: String): Call<LyricsSearchResult>

}