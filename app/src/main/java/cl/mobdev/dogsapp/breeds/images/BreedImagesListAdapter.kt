package cl.mobdev.dogsapp.breeds.images

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import asUri
import cl.mobdev.dogsapp.BaseAdapter
import cl.mobdev.dogsapp.R
import cl.mobdev.dogsapp.domain.breeds.images.model.Image
import com.squareup.picasso.Picasso
import javax.inject.Inject

/**
 * Created by guillermo.mazzola on 15/02/2018.
 */
class BreedImagesListAdapter @Inject constructor(private val picasso: Picasso) : BaseAdapter<Image, BreedImagesListAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = Holder(LayoutInflater.from(parent?.context)
            .inflate(R.layout.fragment_breed_images_list_item, parent, false))

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        with(holder!!) {
            picasso.load(getItem(position).url.asUri())
                    .resizeDimen(R.dimen.image_width, R.dimen.image_height)
                    .centerInside()
                    .into(image)
        }
    }

    override fun onViewRecycled(holder: Holder?) {
        super.onViewRecycled(holder)

        picasso.cancelRequest(holder?.image)
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)!!
    }

}
