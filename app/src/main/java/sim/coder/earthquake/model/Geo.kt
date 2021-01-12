package sim.coder.earthquake.model

import com.google.gson.annotations.SerializedName

data class Geo (
    @SerializedName("coordinates")
    var  geo:List<Double> = emptyList()
)
