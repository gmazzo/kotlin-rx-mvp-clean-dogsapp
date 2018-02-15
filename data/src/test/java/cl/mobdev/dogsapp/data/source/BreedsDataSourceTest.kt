package cl.mobdev.dogsapp.data.source

import cl.mobdev.dogsapp.data.Breed
import cl.mobdev.dogsapp.data.Image
import okhttp3.OkHttpClient
import okhttp3.mock.ClasspathResources.resource
import okhttp3.mock.HttpCodes
import okhttp3.mock.MockInterceptor
import okhttp3.mock.Rule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

internal fun String.breed() = Breed(this)
internal fun String.image() = Image(URL(this))

/**
 * Created by guillermo.mazzola on 14/02/2018.
 */

class BreedsDataSourceTest {
    private val interceptor = MockInterceptor()
    private lateinit var source: BreedsDataSource

    @Before
    fun setup() {
        interceptor.reset()

        source = BreedsDataSource(Retrofit.Builder()
                .client(OkHttpClient.Builder()
                        .addInterceptor(interceptor)
                        .build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://dummyserver/api/")
                .build())
    }

    @Test
    fun testListAll() {
        interceptor.addRule(Rule.Builder()
                .path("/api/breeds/list")
                .respond(resource("breeds_list.json")))

        val values = source.listAll().toList().blockingGet()

        assertEquals(80, values.size)
        assertEquals(listOf(
                "affenpinscher",
                "african",
                "airedale").map { it.breed() },
                values.subList(0, 3))
    }

    @Test(expected = HttpException::class)
    fun testListAll_Failure() {
        interceptor.addRule(Rule.Builder()
                .path("/api/breeds/list")
                .respond(HttpCodes.HTTP_500_INTERNAL_SERVER_ERROR))

        source.listAll().toList().blockingGet()
    }

    @Test
    fun testListImages() {
        interceptor.addRule(Rule.Builder()
                .path("/api/breed/hound/images")
                .respond(resource("breed_hound_images.json")))

        val values = source.listImages("hound").toList().blockingGet()

        assertEquals(1099, values.size)
        assertEquals(listOf(
                "https://dog.ceo/api/img/hound-Ibizan/n02091244_100.jpg",
                "https://dog.ceo/api/img/hound-Ibizan/n02091244_1000.jpg",
                "https://dog.ceo/api/img/hound-Ibizan/n02091244_1025.jpg").map { it.image() },
                values.subList(0, 3))
    }

    @Test(expected = HttpException::class)
    fun testListImages_Failure() {
        interceptor.addRule(Rule.Builder()
                .path("/api/breed/unknown/images")
                .respond(HttpCodes.HTTP_500_INTERNAL_SERVER_ERROR))

        source.listImages("unknown").toList().blockingGet()
    }

}
