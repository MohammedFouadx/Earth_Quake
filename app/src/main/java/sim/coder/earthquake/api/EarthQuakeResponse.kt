package sim.coder.earthquake.api

import com.google.gson.annotations.SerializedName
import sim.coder.earthquake.model.EarthQuakeItem

data class EarthQuakeResponse (
    @SerializedName("features")
    var earthItem:List<EarthQuakeItem>
)