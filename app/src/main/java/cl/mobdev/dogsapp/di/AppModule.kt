package cl.mobdev.dogsapp.di

import cl.mobdev.dogsapp.MainActivity
import cl.mobdev.dogsapp.breeds.list.BreedsListFragment
import cl.mobdev.dogsapp.breeds.list.BreedsListModule
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

/**
 * Created by guillermo.mazzola on 14/02/2018.
 */
@Module(includes = [AndroidInjectionModule::class, NetModule::class])
interface AppModule {

    @ContributesAndroidInjector
    fun mainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [BreedsListModule::class])
    fun breedsListFragment(): BreedsListFragment

}
