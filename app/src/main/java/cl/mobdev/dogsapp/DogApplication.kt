package cl.mobdev.dogsapp

import cl.mobdev.dogsapp.di.DaggerInjector
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by guillermo.mazzola on 14/02/2018.
 */

class DogApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerInjector.builder().create(this)
    }

}
