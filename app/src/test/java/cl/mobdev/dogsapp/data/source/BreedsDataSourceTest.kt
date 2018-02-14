package cl.mobdev.dogsapp.data.source

import cl.mobdev.dogsapp.BaseRobolectricTest
import cl.mobdev.dogsapp.BuildConfig
import cl.mobdev.dogsapp.data.Breed
import cl.mobdev.dogsapp.data.Image
import cl.mobdev.dogsapp.data.source.BreedsAPI.Companion.STATUS_SUCCESS
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.net.URL

internal fun <T> T.asResponse() = Single.just(BreedsAPI.Response(STATUS_SUCCESS, this))
internal fun String.breed() = Breed(this)

/**
 * Created by guillermo.mazzola on 14/02/2018.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class BreedsDataSourceTest : BaseRobolectricTest() {
    private lateinit var source: BreedsDataSource

    @Mock
    private lateinit var api: BreedsAPI

    @Before
    fun setup() {
        source = BreedsDataSource(api)
    }

    @Test
    fun testListAll() {
        `when`(api.listAll()).thenReturn(
                mapOf("kaniche" to listOf("toy", "minitoy"), "sharpei" to listOf(), "doberman" to listOf()).asResponse())

        val values = source.listAll().toList().blockingGet()

        assertEquals(listOf("kaniche".breed(), "sharpei".breed(), "doberman".breed()), values)
        verify(api).listAll()
    }

    @Test
    fun testListImages1() {
        testListImages("kaniche", "kaniche1", "kaniche2", "kaniche3")
    }

    @Test
    fun testListImages2() {
        testListImages("sharpei", "sharpei1")
    }

    @Test
    fun testListImagesNoImages() {
        testListImages("doberman")
    }

    private fun testListImages(name: String, vararg images: String) {
        val urls = images.map { URL("http://dummyserver/static/$it.jpg") }.toList()

        `when`(api.listImages(name)).thenReturn(urls.asResponse())

        val values = source.listImages(name).toList().blockingGet()

        assertEquals(urls.map { Image(it) }, values)
        verify(api).listImages(name)
    }

}
