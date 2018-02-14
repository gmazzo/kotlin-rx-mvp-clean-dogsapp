package cl.mobdev.dogsapp.di

import android.support.v4.app.Fragment
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

/**
 * Created by guillermo.mazzola on 14/02/2018.
 */
@Module(includes = [AndroidInjectionModule::class, NetModule::class])
interface AppModule {

    // TODO delete this
    @ContributesAndroidInjector
    fun dummyFragment(): Fragment

}
