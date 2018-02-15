import android.net.Uri
import java.net.URL

/**
 * Created by guillermo.mazzola on 15/02/2018.
 */

fun URL.asUri() = Uri.parse(toString())
