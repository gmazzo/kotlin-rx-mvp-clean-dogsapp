package cl.mobdev.dogsapp.di

import cl.mobdev.dogsapp.DogsApplication
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Created by guillermo.mazzola on 14/02/2018.
 */

@Singleton
@Component(modules = [AppModule::class])
interface Injector : AndroidInjector<DogsApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DogsApplication>()

}
