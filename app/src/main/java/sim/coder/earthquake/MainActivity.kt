package sim.coder.earthquake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import sim.coder.earthquake.fragment.EarthQuakeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.frame_container, EarthQuakeFragment.newInstance())
                .commit()
        }


    }
}