package cl.mobdev.dogsapp.domain.breeds.list

import cl.mobdev.dogsapp.data.source.BreedsRepository
import cl.mobdev.dogsapp.domain.breeds.images.model.Image
import cl.mobdev.dogsapp.domain.breeds.list.model.Breed
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by guillermo.mazzola on 14/02/2018.
 */

@RunWith(MockitoJUnitRunner::class)
class ListBreedsUseCaseTest {
    lateinit var useCase: ListBreedsUseCase

    @Mock
    lateinit var repository: BreedsRepository

    @Before
    fun setup() {
        useCase = ListBreedsUseCase(repository)
    }

    @Test
    fun testExecute() {
        val list = listOf("toy", "sharpei", "doberman")

        `when`(repository.listAll()).thenReturn(Observable.fromIterable(list.map { cl.mobdev.dogsapp.data.Breed(it) }))

        val values = useCase.execute(Unit).toList().blockingGet()

        assertEquals(list.map { Breed(it) }, values)
        verify(repository).listAll()
    }

    @Test
    fun testExecute_NoResults() {
        `when`(repository.listAll()).thenReturn(Observable.empty())

        val values = useCase.execute(Unit).toList().blockingGet()

        assertEquals(emptyList<Image>(), values)
        verify(repository).listAll()
    }

    @Test(expected = IllegalStateException::class)
    fun testExecute_Failure() {
        `when`(repository.listAll()).thenThrow(IllegalStateException::class.java)

        useCase.execute(Unit).toList().blockingGet()
    }

}
