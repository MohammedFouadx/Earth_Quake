package sim.coder.earthquake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import sim.coder.earthquake.EarthQuakeFetchr

class EarthQuakeViewModel : ViewModel(){

    val earthQuakeLiveData: LiveData<List<EarthQuakeItem>>

    init {
        earthQuakeLiveData = EarthQuakeFetchr().fetchData()
    }


}