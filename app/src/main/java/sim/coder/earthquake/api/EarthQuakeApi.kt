package sim.coder.earthquake.api

import retrofit2.Call
import retrofit2.http.GET

interface EarthQuakeApi {

    @GET("/fdsnws/event/1//query?format=geojson&limit=10")
    fun fetchContents(): Call<EarthQuakeResponse>


}