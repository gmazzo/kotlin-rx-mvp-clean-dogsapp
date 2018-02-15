package cl.mobdev.dogsapp.breeds.list

import cl.mobdev.dogsapp.domain.breeds.list.ListBreedsUseCase
import cl.mobdev.dogsapp.domain.breeds.list.model.Breed
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by guillermo.mazzola on 15/02/2018.
 */

class BreedsListPresenter @Inject constructor(private val useCase: Provider<ListBreedsUseCase>) : BreedsListContract.Presenter {
    private var mRequest = createRequest()
    private var mView: BreedsListContract.View? = null

    override fun bindView(view: BreedsListContract.View) {
        mView = view

        performLoad()
    }

    override fun unbindView() {
        mView = null
    }

    override fun reloadItems() {
        mRequest = createRequest()

        performLoad()
    }

    private fun createRequest(): Single<List<Breed>> {
        return useCase.get()
                .execute(Unit)
                .toList()
                .cache()
    }

    private fun performLoad() {
        mRequest.observeOn(AndroidSchedulers.mainThread())
                .subscribe({ mView?.displayItems(it) }, { mView?.displayError(it) })
    }

}
