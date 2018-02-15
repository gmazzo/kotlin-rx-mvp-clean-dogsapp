package cl.mobdev.dogsapp

import android.support.v7.widget.RecyclerView

/**
 * Created by guillermo.mazzola on 15/02/2018.
 */

abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder>(items: List<T>?) : RecyclerView.Adapter<VH>() {

    constructor() : this(null)

    var items: List<T>? = items
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = items?.size ?: 0

    fun getItem(position: Int) = items!![position]

}
