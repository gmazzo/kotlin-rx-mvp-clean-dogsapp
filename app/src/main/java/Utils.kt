import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import java.net.URL

/**
 * Created by guillermo.mazzola on 15/02/2018.
 */

fun URL.asUri() = Uri.parse(toString())!!

fun <T : Fragment> T.withArgs(args: Bundle): T {
    this.arguments = args
    return this
}
