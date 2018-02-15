package cl.mobdev.dogsapp.data.di

import cl.mobdev.dogsapp.data.source.BreedsDataSource
import cl.mobdev.dogsapp.data.source.BreedsRepository
import dagger.Binds
import dagger.Module

/**
 * Created by guillermo.mazzola on 15/02/2018.
 */

@Module
interface DataModule {

    @Binds
    fun bindBreedsRepository(impl: BreedsDataSource): BreedsRepository

}
