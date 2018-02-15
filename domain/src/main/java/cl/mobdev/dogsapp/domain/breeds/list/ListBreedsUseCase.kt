package cl.mobdev.dogsapp.domain.breeds.list

import cl.mobdev.dogsapp.data.source.BreedsRepository
import cl.mobdev.dogsapp.domain.UseCase
import cl.mobdev.dogsapp.domain.breeds.list.model.Breed
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by guillermo.mazzola on 14/02/2018.
 */

class ListBreedsUseCase @Inject constructor(private val repository: BreedsRepository) : UseCase<Unit, Observable<Breed>>() {

    override fun execute(request: Unit) = repository.listAll()
            .map { Breed(it.name) }
            .subscribeOn(Schedulers.io())

}
