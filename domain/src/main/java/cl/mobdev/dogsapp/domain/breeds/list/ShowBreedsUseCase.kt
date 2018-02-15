package cl.mobdev.dogsapp.domain.breeds.list

import cl.mobdev.dogsapp.data.Breed
import cl.mobdev.dogsapp.data.source.BreedsRepository
import cl.mobdev.dogsapp.domain.UseCase
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by guillermo.mazzola on 14/02/2018.
 */

class ShowBreedsUseCase(private val repository: BreedsRepository) : UseCase<Unit, Observable<Breed>>() {

    override fun execute(request: Unit) = repository.listAll()
            .subscribeOn(Schedulers.io())

}
