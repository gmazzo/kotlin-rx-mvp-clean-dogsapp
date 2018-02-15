package cl.mobdev.dogsapp.breeds.images

import cl.mobdev.dogsapp.domain.breeds.images.ListBreedImagesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Provider

/**
 * Created by guillermo.mazzola on 15/02/2018.
 */

class BreedImagesListPresenter @Inject constructor(
        useCase: Provider<ListBreedImagesUseCase>,
        @Named(ARG_BREED_NAME) breedName: String) : BreedImagesListContract.Presenter {
    private val mRequest = useCase.get()
            .execute(breedName)
            .toList()
            .cache()
    private var mView: BreedImagesListContract.View? = null

    override fun bindView(view: BreedImagesListContract.View) {
        mView = view

        performLoad()
    }

    override fun unbindView() {
        mView = null
    }

    private fun performLoad() {
        mRequest.observeOn(AndroidSchedulers.mainThread())
                .subscribe({ mView?.displayItems(it) }, { mView?.displayError(it) })
    }

}
