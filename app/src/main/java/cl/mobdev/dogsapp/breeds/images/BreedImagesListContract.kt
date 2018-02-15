package cl.mobdev.dogsapp.breeds.images

import cl.mobdev.dogsapp.BasePresenter
import cl.mobdev.dogsapp.BaseView
import cl.mobdev.dogsapp.domain.breeds.images.model.Image

/**
 * Created by guillermo.mazzola on 15/02/2018.
 */

interface BreedImagesListContract {

    interface View : BaseView {

        fun displayItems(items: List<Image>)

    }

    interface Presenter : BasePresenter<View>

}
