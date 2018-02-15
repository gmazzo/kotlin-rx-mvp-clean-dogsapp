package cl.mobdev.dogsapp.breeds.list

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cl.mobdev.dogsapp.R
import cl.mobdev.dogsapp.breeds.images.BreedImagesListFragment
import cl.mobdev.dogsapp.domain.breeds.list.model.Breed
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_breeds_list.*
import javax.inject.Inject

/**
 * Created by guillermo.mazzola on 15/02/2018.
 */

class BreedsListFragment : DaggerFragment(), BreedsListContract.View {

    @Inject
    internal lateinit var mPresenter: BreedsListContract.Presenter

    @Inject
    internal lateinit var mAdapter: BreedsListAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_breeds_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler.setHasFixedSize(true)
        recycler.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        recycler.adapter = mAdapter

        refresh.setOnRefreshListener { mPresenter.reloadItems() }
    }

    override fun onStart() {
        super.onStart()

        mPresenter.bindView(this)
        mAdapter.listener = this::onItemSelected

        refresh.isRefreshing = true
    }

    override fun onStop() {
        super.onStop()

        mPresenter.unbindView()
        mAdapter.listener = null
    }

    override fun displayItems(items: List<Breed>) {
        mAdapter.items = items
        refresh.isRefreshing = false
    }

    private fun onItemSelected(item: Breed) {
        fragmentManager.beginTransaction()
                .replace(id, BreedImagesListFragment.create(item.name))
                .addToBackStack(null)
                .commit()
    }

    companion object {

        fun create() = BreedsListFragment()

    }

}
