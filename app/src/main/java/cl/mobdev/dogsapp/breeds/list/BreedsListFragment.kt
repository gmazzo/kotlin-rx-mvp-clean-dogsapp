package cl.mobdev.dogsapp.breeds.list

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cl.mobdev.dogsapp.R
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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_breeds_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
        refresh.setOnRefreshListener { mPresenter.reloadItems() }
    }

    override fun onStart() {
        super.onStart()

        refresh.isRefreshing = true
        mPresenter.bindView(this)
    }

    override fun onStop() {
        super.onStop()

        mPresenter.unbindView()
    }

    override fun displayItems(items: List<Breed>) {
        recycler.adapter = BreedsListAdapter(items)
        refresh.isRefreshing = false
    }

    companion object {

        fun create() = BreedsListFragment()

    }

}
