package cl.mobdev.dogsapp.data.source

import cl.mobdev.dogsapp.data.Breed
import cl.mobdev.dogsapp.data.Image
import io.reactivex.Observable

/**
 * Created by guillermo.mazzola on 14/02/2018.
 */
interface BreedsRepository {

    fun listAll(): Observable<Breed>

    fun listImages(breedName: String): Observable<Image>

}
