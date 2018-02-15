package cl.mobdev.dogsapp.breeds.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cl.mobdev.dogsapp.BaseAdapter
import cl.mobdev.dogsapp.R
import cl.mobdev.dogsapp.domain.breeds.list.model.Breed

/**
 * Created by guillermo.mazzola on 15/02/2018.
 */
class BreedsListAdapter(items: List<Breed>) : BaseAdapter<Breed, BreedsListAdapter.Holder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = Holder(LayoutInflater.from(parent?.context)
            .inflate(R.layout.fragment_breeds_list_item, parent, false))

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        with(holder!!) {
            item = items[position]
            name.text = item.name
        }
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)!!
        lateinit var item: Breed
    }

}
