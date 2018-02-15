package cl.mobdev.dogsapp.breeds.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cl.mobdev.dogsapp.R
import cl.mobdev.dogsapp.domain.breeds.images.model.Image
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_breed_images_list.*
import javax.inject.Inject

/**
 * Created by guillermo.mazzola on 15/02/2018.
 */

class BreedImagesListFragment : DaggerFragment(), BreedImagesListContract.View {

    @Inject
    internal lateinit var mPresenter: BreedImagesListContract.Presenter

    @Inject
    internal lateinit var mAdapter: BreedImagesListAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_breed_images_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler.setHasFixedSize(true)
        recycler.layoutManager = FlexboxLayoutManager(context, FlexDirection.ROW)
        recycler.adapter = mAdapter
    }

    override fun onStart() {
        super.onStart()

        mPresenter.bindView(this)
    }

    override fun onStop() {
        super.onStop()

        mPresenter.unbindView()
    }

    override fun displayItems(items: List<Image>) {
        mAdapter.items = items
        switcher.displayedChild = 1
    }

    companion object {

        fun create(breedName: String): BreedImagesListFragment {
            val args = Bundle(1)
            args.putString(ARG_BREED_NAME, breedName)

            val fragment = BreedImagesListFragment()
            fragment.arguments = args
            return fragment
        }

    }

}
