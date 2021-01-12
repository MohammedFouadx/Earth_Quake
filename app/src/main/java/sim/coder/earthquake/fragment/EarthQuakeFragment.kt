package sim.coder.earthquake.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sim.coder.earthquake.EarthQuakeFetchr
import sim.coder.earthquake.R
import sim.coder.earthquake.model.EarthQuakeItem
import sim.coder.earthquake.model.EarthQuakeViewModel
import java.text.SimpleDateFormat
import java.util.*


class EarthQuakeFragment : Fragment() {

    private lateinit var earthQuakeViewModel: EarthQuakeViewModel
    private lateinit var earthQuakeRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //reenterTransition = true
        earthQuakeViewModel =
            ViewModelProviders.of(this).get(EarthQuakeViewModel::class.java)






    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var earthQuakeFetchr = EarthQuakeFetchr()
        val earthquakeLiveData=earthQuakeFetchr.fetchData()
        earthquakeLiveData.observe(viewLifecycleOwner, Observer {

            earthQuakeRecyclerView.adapter = EarthQuakeAdapter(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_earth_quake, container, false)
        earthQuakeRecyclerView=view.findViewById(R.id.earth_quake_recyclerView)
        earthQuakeRecyclerView.layoutManager= LinearLayoutManager(context)

        return view
    }

    companion object {
        fun newInstance() = EarthQuakeFragment()
    }
    private  inner class EarthQuakeHolder(view: View) : RecyclerView.ViewHolder(view){

        val earthQuakeTextView=view.findViewById(R.id.EarthQuake_degree) as TextView
        val titleTextView=view.findViewById(R.id.title) as TextView
        val distanceTextView=view.findViewById(R.id.distance) as TextView
        val dateTextView=view.findViewById(R.id.date) as TextView
        val timeTextView=view.findViewById(R.id.time) as TextView
        val cardView=view.findViewById(R.id.card) as CardView
        private var longtude: Double = 0.0
        private var latitude: Double = 0.0
        var  earthQuake=EarthQuakeItem()

        fun bind(earthQuake: EarthQuakeItem){
            this.earthQuake=earthQuake
            earthQuakeTextView.text=earthQuake.earthQuake.degree.toString()
            titleTextView.text=earthQuake.earthQuake.title
            distanceTextView.text=earthQuake.earthQuake.place


            var coordinates:List<Double> =earthQuake.geo.geo
            longtude = coordinates[0]
            latitude = coordinates[1]

            cardView.setOnClickListener {
                val uri = Uri.parse("$latitude,$longtude")
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = uri
                }
                requireActivity().startActivity(intent)
            }


            when{

                earthQuake.earthQuake.degree in 2.0..3.9
                -> earthQuakeTextView.setBackgroundResource(R.color.green)

                earthQuake.earthQuake.degree in 4.0..4.9
                -> earthQuakeTextView.setBackgroundResource(R.color.yallow)

                earthQuake.earthQuake.degree in 5.0..5.9
                -> earthQuakeTextView.setBackgroundResource(R.color.orange)

                earthQuake.earthQuake.degree in 6.0..10.0
                -> earthQuakeTextView.setBackgroundResource(R.color.red)

                else -> earthQuakeTextView.setBackgroundResource(R.color.blue)

            }



            var date =earthQuake.earthQuake.time
            val calendar = Calendar.getInstance()
            calendar.time = Date(date)

            val getDate = "${calendar.get(Calendar.YEAR)}-" +
                    "${calendar.get(Calendar.MONTH)}-" +
                    "${calendar.get(Calendar.DAY_OF_MONTH)}"

            val getTime =
                SimpleDateFormat("HH:mm",Locale.US).format(calendar.time)
                "${calendar.get(Calendar.HOUR_OF_DAY)}:" +
                    "${calendar.get(Calendar.MINUTE)}"


            dateTextView.text = getDate
            timeTextView.text = getTime

        }



    }



    inner class EarthQuakeAdapter(private val earthQuakeAdapter: List<EarthQuakeItem>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view:View = layoutInflater.inflate(
                R.layout.list_item_earth_quake,
                parent, false
            )

            return EarthQuakeHolder(view)

        }




        override fun getItemCount(): Int {

            return earthQuakeAdapter.size

        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            val earthquake=earthQuakeAdapter[position]
            if(holder is EarthQuakeHolder)
                holder.bind(earthquake)


        }
    }

}