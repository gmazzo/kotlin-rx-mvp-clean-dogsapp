package cl.mobdev.dogsapp.domain.breeds.images

import cl.mobdev.dogsapp.data.source.BreedsRepository
import cl.mobdev.dogsapp.domain.breeds.images.model.Image
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.net.URL

/**
 * Created by guillermo.mazzola on 14/02/2018.
 */

@RunWith(MockitoJUnitRunner::class)
class ListBreedImagesUseCaseTest {
    lateinit var useCase: ListBreedImagesUseCase

    @Mock
    lateinit var repository: BreedsRepository

    @Before
    fun setup() {
        useCase = ListBreedImagesUseCase(repository)
    }

    @Test
    fun testExecute() {
        val list = listOf("sharpei1", "sharpei2", "sharpei3")
                .map { URL("http://dummyserver/images/$it.jpg") }

        `when`(repository.listImages("sharpei")).thenReturn(Observable.fromIterable(
                list.map { cl.mobdev.dogsapp.data.Image(it) }))

        val values = useCase.execute("sharpei").toList().blockingGet()

        assertEquals(list.map { Image(it) }, values)
        verify(repository).listImages("sharpei")
    }

    @Test
    fun testExecute_NoResults() {
        `when`(repository.listImages("unknown")).thenReturn(Observable.empty())

        val values = useCase.execute("unknown").toList().blockingGet()

        assertEquals(emptyList<Image>(), values)
        verify(repository).listImages("unknown")
    }

    @Test(expected = IllegalStateException::class)
    fun testExecute_Failure() {
        `when`(repository.listImages("sharpei")).thenThrow(IllegalStateException::class.java)

        useCase.execute("sharpei").toList().blockingGet()

        verify(repository).listImages("sharpei")
    }

}
