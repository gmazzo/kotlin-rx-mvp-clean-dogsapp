package cl.mobdev.dogsapp.domain

/**
 * Created by guillermo.mazzola on 14/02/2018.
 */

abstract class UseCase<in IN, out OUT> {

    abstract fun execute(request: IN): OUT

}
