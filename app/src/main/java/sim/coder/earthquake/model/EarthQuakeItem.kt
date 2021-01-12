package sim.coder.earthquake.model

import com.google.gson.annotations.SerializedName

data class EarthQuakeItem (
    @SerializedName("properties")
    var earthQuake:EarthQuake=EarthQuake(),

    @SerializedName("geometry")
    var geo:Geo=Geo()
)