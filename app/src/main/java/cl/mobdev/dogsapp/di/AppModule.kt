package cl.mobdev.dogsapp.di

import android.content.Context
import cl.mobdev.dogsapp.DogsApplication
import cl.mobdev.dogsapp.MainActivity
import cl.mobdev.dogsapp.breeds.images.BreedImagesListFragment
import cl.mobdev.dogsapp.breeds.images.BreedImagesListModule
import cl.mobdev.dogsapp.breeds.list.BreedsListFragment
import cl.mobdev.dogsapp.breeds.list.BreedsListModule
import cl.mobdev.dogsapp.domain.di.DomainModule
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

/**
 * Created by guillermo.mazzola on 14/02/2018.
 */
@Module(includes = [AndroidInjectionModule::class, NetModule::class, DomainModule::class])
interface AppModule {

    @Binds
    fun bindContext(application: DogsApplication): Context

    @ContributesAndroidInjector
    fun mainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [BreedsListModule::class])
    fun breedsListFragment(): BreedsListFragment

    @ContributesAndroidInjector(modules = [BreedImagesListModule::class])
    fun breedImagesListFragment(): BreedImagesListFragment

}
