package cl.mobdev.dogsapp.breeds.list

import dagger.Binds
import dagger.Module

/**
 * Created by guillermo.mazzola on 15/02/2018.
 */

@Module
interface BreedsListModule {

    @Binds
    fun bindPresenter(impl: BreedsListPresenter): BreedsListContract.Presenter

}
