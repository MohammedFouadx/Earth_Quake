package sim.coder.earthquake

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sim.coder.earthquake.api.EarthQuakeApi
import sim.coder.earthquake.api.EarthQuakeResponse
import sim.coder.earthquake.model.EarthQuakeItem

class EarthQuakeFetchr {

    private var  earthQuakeApi:EarthQuakeApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://earthquake.usgs.gov")
            .build()
        earthQuakeApi = retrofit.create(EarthQuakeApi::class.java)
    }


    fun fetchData(): MutableLiveData<List<EarthQuakeItem>> {
        val responseLiveData: MutableLiveData<List<EarthQuakeItem>> = MutableLiveData()
        val earthQuakeHomePageRequest: Call<EarthQuakeResponse> = earthQuakeApi.fetchContents()
        earthQuakeHomePageRequest.enqueue(object : Callback<EarthQuakeResponse> {
            override fun onResponse(call: Call<EarthQuakeResponse>, response: Response<EarthQuakeResponse>) {


                var earthResponse=response.body()
                var eaathQuakes= earthResponse?.earthItem
                    ?: mutableListOf()
                responseLiveData.value=eaathQuakes



            }

            override fun onFailure(call: Call<EarthQuakeResponse>, t: Throwable) {


            }

        })

        return responseLiveData

    }




}