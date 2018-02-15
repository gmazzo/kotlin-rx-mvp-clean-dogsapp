package cl.mobdev.dogsapp

import android.os.Bundle
import cl.mobdev.dogsapp.breeds.list.BreedsListFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by guillermo.mazzola on 15/02/2018.
 */

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.content, BreedsListFragment.create(), null)
                    .commitNow()
        }
    }

}
