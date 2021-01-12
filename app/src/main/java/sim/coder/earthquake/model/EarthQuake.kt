package sim.coder.earthquake.model

import com.google.gson.annotations.SerializedName

data class EarthQuake(
    @SerializedName("mag")
    var degree: Double =0.0,
    @SerializedName("title")
    var title:String="",
    @SerializedName("time")
    var time:Long=3,
    @SerializedName("place")
    var place:String=""

    ){
}