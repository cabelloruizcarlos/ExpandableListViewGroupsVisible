package omnifi.co.uk.explistviewgroupsvisible

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.ViewTreeObserver
import android.widget.TextView


class GroupView @JvmOverloads constructor(context: Context,
                                          attributeSet: AttributeSet? = null,
                                          defStyleAttr: Int = 0) :
        TextView(context, attributeSet, defStyleAttr) {

    var originalPosition = -1.0f
    var expandedPosition = -1.0f
    var state: Constants.ListState = Constants.ListState.UP
        set(value) {
            field = value
Log.d("Testing", "${this.text}: positions($originalPosition,$expandedPosition)")

            when (value) {
                Constants.ListState.UP -> {
                    this.animate()
                            .y(originalPosition)
                            .setDuration(Constants.ANIMATION_DURATION)
                            .start()
                }
                Constants.ListState.DOWN -> {
                    this.animate()
                            .y(expandedPosition)
                            .setDuration(Constants.ANIMATION_DURATION)
                            .start()
                }
            }
        }

    init {
        val view = this
        view.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                originalPosition = view.y
                expandedPosition = view.y + Constants.DISTANCE
                Log.d("Testing","${view.text}: setting positions($originalPosition,$expandedPosition)")
                view.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }
}