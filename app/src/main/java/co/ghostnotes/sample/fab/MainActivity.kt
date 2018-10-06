package co.ghostnotes.sample.fab

import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import co.ghostnotes.sample.R
import co.ghostnotes.sample.fab.utils.UIUtil

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var isFabOpened = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            Log.d(TAG, "### fab is clicked.")

            if (isFabOpened) {
                animateFabClose()
                animateSubFabFirstClose()
            } else {
                animateFabOpen()
                animateSubFabFirstOpen()
            }

            showSnackbar(fab,
                    if (isFabOpened)  R.string.snackbar_message_fab_clicked_close
                    else R.string.snackbar_message_fab_clicked_open)

            // Change the state of the FAB.
            isFabOpened = !isFabOpened
        }
    }

    private fun showSnackbar(view: View, @StringRes resId: Int) {
        showSnackbar(view, getString(resId))
    }

    private fun showSnackbar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

    private fun animateSubFabFirstOpen() {
        animateSubFab(SUB_FAB_TRANSLATION_Y_FROM_DP, SUB_FAB_TRANSLATION_Y_TO_DP)
    }

    private fun animateSubFabFirstClose() {
        animateSubFab(SUB_FAB_TRANSLATION_Y_TO_DP, SUB_FAB_TRANSLATION_Y_FROM_DP)
    }

    private fun animateSubFab(fromDp: Float, toDp: Float) {
        ObjectAnimator.ofFloat(
                sub_fab_first,
                ANIMATION_PROPERTY_NAME_TRANSLATION_Y,
                UIUtil.convertDpToPx(this, fromDp),
                UIUtil.convertDpToPx(this, toDp)
        ).apply {
            duration = SUB_FAB_TRANSLATE_DURATION
            start()
        }
    }

    private fun animateFabOpen() {
        animateFab(FAB_ROTATION_DEFAULT, FAB_ROTATION_VALUE)
    }

    private fun animateFabClose() {
        animateFab(FAB_ROTATION_VALUE, FAB_ROTATION_DEFAULT)
    }

    private fun animateFab(from: Float, to: Float) {
        ObjectAnimator.ofFloat(fab, ANIMATION_PROPERTY_NAME_ROTATION, from, to).apply {
            duration = FAB_ROTATION_DURATION
            start()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName

        private const val FAB_ROTATION_DEFAULT = 0F
        private const val FAB_ROTATION_VALUE = -135F
        private const val FAB_ROTATION_DURATION = 100L

        private const val SUB_FAB_TRANSLATION_Y_FROM_DP = 0F
        private const val SUB_FAB_TRANSLATION_Y_TO_DP = -64F

        private const val SUB_FAB_TRANSLATE_DURATION = 100L

        private const val ANIMATION_PROPERTY_NAME_ROTATION = "rotation"
        private const val ANIMATION_PROPERTY_NAME_TRANSLATION_Y = "translationY"
    }

}
