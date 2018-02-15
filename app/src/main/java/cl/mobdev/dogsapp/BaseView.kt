package cl.mobdev.dogsapp

/**
 * Created by guillermo.mazzola on 15/02/2018.
 */

interface BaseView {

    fun displayError(throwable: Throwable) {
        throwable.printStackTrace()
    }

}
