package cl.mobdev.dogsapp.data.source

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import java.net.URL

/**
 * Created by guillermo.mazzola on 14/02/2018.
 */

internal interface BreedsAPI {

    @GET("breeds/list")
    fun listAll(): Single<Response<Map<String, List<String>>>>

    @GET("breed/{breed}/images")
    fun listImages(@Path("breed") name: String): Single<Response<List<URL>>>

    data class Response<T>(@SerializedName("status") var status: String, @SerializedName("message") var message: T) {

        fun get(): T = if (STATUS_SUCCESS == status) message else throw IllegalStateException("unsuccessful response: $status")

    }

    companion object {

        const val STATUS_SUCCESS = "success"

    }

}
