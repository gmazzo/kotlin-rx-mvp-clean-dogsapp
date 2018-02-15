package cl.mobdev.dogsapp.domain.breeds.images

import cl.mobdev.dogsapp.data.source.BreedsRepository
import cl.mobdev.dogsapp.domain.UseCase
import cl.mobdev.dogsapp.domain.breeds.images.model.Image
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by guillermo.mazzola on 14/02/2018.
 */

class ListBreedImagesUseCase @Inject constructor(private val repository: BreedsRepository) : UseCase<ListBreedImagesUseCase.Request, Observable<Image>>() {

    override fun execute(request: Request) = repository.listImages(request.breedName)
            .map { Image(it.url) }
            .subscribeOn(Schedulers.io())

    data class Request(val breedName: String)

}
