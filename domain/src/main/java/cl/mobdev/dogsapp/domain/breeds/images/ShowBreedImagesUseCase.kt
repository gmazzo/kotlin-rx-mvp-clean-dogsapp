package cl.mobdev.dogsapp.domain.breeds.images

import cl.mobdev.dogsapp.data.Image
import cl.mobdev.dogsapp.data.source.BreedsRepository
import cl.mobdev.dogsapp.domain.UseCase
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by guillermo.mazzola on 14/02/2018.
 */

class ShowBreedImagesUseCase(private val repository: BreedsRepository) : UseCase<ShowBreedImagesUseCase.Request, Observable<Image>>() {

    override fun execute(request: Request) = repository.listImages(request.breedName)
            .subscribeOn(Schedulers.io())

    data class Request(val breedName: String)

}
