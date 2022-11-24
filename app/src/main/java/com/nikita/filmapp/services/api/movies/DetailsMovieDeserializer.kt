package com.nikita.filmapp.services.api.movies

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.nikita.filmapp.models.DetailedMovie
import java.lang.reflect.Type

class DetailsMovieDeserializer : JsonDeserializer<DetailedMovie> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): DetailedMovie {
        json?.apply {
            try {
                val jsonObject = this.asJsonObject
                val id = jsonObject.get("id").asNumber
                val backdrop = jsonObject.get("backdrop_path").asString
                val poster = jsonObject.get("poster_path").asString
                val title = jsonObject.get("title").asString
                val overview = jsonObject.get("overview").asString
                val productionCompany =
                    jsonObject.get("production_companies").asJsonArray[0].asJsonObject.get("name").asString
                return DetailedMovie(
                    id = id,
                    backdrop = backdrop,
                    poster = poster,
                    title = title,
                    overview = overview,
                    productionCompany = productionCompany
                )
            } catch (e: Exception) {
                Log.e("Deserialisation Error", e.message ?: "")
            }
        }
        return DetailedMovie()
    }
}