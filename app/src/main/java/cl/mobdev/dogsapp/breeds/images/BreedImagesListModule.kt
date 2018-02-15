package cl.mobdev.dogsapp.breeds.images

import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by guillermo.mazzola on 15/02/2018.
 */

internal const val ARG_BREED_NAME = "breedName"

@Module(includes = [BreedImagesListModule.Providers::class])
interface BreedImagesListModule {

    @Binds
    fun bindPresenter(impl: BreedImagesListPresenter): BreedImagesListContract.Presenter

    @Module
    class Providers {

        @Provides
        @Named(ARG_BREED_NAME)
        fun provideBreedName(fragment: BreedImagesListFragment) = fragment.arguments.getString(ARG_BREED_NAME)

    }

}
