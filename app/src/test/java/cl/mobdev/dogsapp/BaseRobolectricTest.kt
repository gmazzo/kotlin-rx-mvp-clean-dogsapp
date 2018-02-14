package cl.mobdev.dogsapp

import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Created by guillermo.mazzola on 14/02/2018.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
abstract class BaseRobolectricTest {

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

}
