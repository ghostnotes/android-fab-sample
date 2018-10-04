package co.ghostnotes.sample.fab.utils

import android.content.Context

class UIUtil {

    companion object {
        fun convertDpToPx(context: Context, dp: Float): Float {
            return dp * context.resources.displayMetrics.density
        }
    }

}