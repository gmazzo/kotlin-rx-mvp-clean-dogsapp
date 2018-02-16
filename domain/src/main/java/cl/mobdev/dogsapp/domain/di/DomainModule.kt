package cl.mobdev.dogsapp.domain.di

import cl.mobdev.dogsapp.data.di.DataModule
import dagger.Module

/**
 * Created by guillermo.mazzola on 15/02/2018.
 */

@Module(includes = [DataModule::class])
interface DomainModule
