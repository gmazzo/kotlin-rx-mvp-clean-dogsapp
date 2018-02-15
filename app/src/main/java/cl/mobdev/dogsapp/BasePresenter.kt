package cl.mobdev.dogsapp

/**
 * Created by guillermo.mazzola on 15/02/2018.
 */

interface BasePresenter<in T : BaseView> {

    fun bindView(view: T)

    fun unbindView()

}
