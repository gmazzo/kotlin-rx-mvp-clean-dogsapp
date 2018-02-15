package cl.mobdev.dogsapp.data.source

import cl.mobdev.dogsapp.data.Breed
import cl.mobdev.dogsapp.data.Image
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by guillermo.mazzola on 14/02/2018.
 */
class BreedsDataSource @Inject constructor(retrofit: Retrofit) : BreedsRepository {
    private val api = retrofit.create(BreedsAPI::class.java)

    override fun listAll(): Observable<Breed> = api.listAll()
            .flatMapObservable { Observable.fromIterable(it.get()) }
            .map { Breed(it) }

    override fun listImages(breedName: String): Observable<Image> = api.listImages(breedName)
            .map { it.get() }
            .flatMapObservable { Observable.fromIterable(it) }
            .map { Image(it) }

}
