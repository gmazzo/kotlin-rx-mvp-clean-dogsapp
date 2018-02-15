package cl.mobdev.dogsapp.breeds.list

import cl.mobdev.dogsapp.BasePresenter
import cl.mobdev.dogsapp.BaseView
import cl.mobdev.dogsapp.domain.breeds.list.model.Breed

/**
 * Created by guillermo.mazzola on 15/02/2018.
 */

interface BreedsListContract {

    interface View : BaseView {

        fun displayItems(items: List<Breed>)

    }

    interface Presenter : BasePresenter<View> {

        fun reloadItems()

    }

}
