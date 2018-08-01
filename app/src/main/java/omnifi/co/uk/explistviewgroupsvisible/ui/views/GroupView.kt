package omnifi.co.uk.explistviewgroupsvisible.ui.views

import android.animation.Animator
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewTreeObserver
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.view_group.view.*
import omnifi.co.uk.explistviewgroupsvisible.R
import omnifi.co.uk.explistviewgroupsvisible.model.Constants
import omnifi.co.uk.explistviewgroupsvisible.ui.GroupViewAdapter

class GroupView @JvmOverloads constructor(context: Context,
                                          attributeSet: AttributeSet? = null,
                                          defStyleAttr: Int = 0) :
        RelativeLayout(context, attributeSet, defStyleAttr) {

    var originalPosition = -1.0f
    var expandedPosition = -1.0f
    var state: Constants.ListState = Constants.ListState.UP
        set(value) {
            when (value) {
                Constants.ListState.UP -> {
                    if (field == Constants.ListState.DOWN)
                        this.animate()
                                .y(originalPosition)
                                .setDuration(Constants.ANIMATION_DURATION)
                                .start()
                    if (groupview_list.visibility == View.VISIBLE) {
                        Log.d("Testing", "state.setter: Hiding the list")
                        hideList()
                    }
                }
                Constants.ListState.DOWN -> {
                    this.animate()
                            .y(expandedPosition)
                            .setDuration(Constants.ANIMATION_DURATION)
                            .start()
                }
            }
            field = value
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_group, this, true)

        groupview_list.layoutManager = LinearLayoutManager(context)

        val view = this
        view.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                originalPosition = view.y
                expandedPosition = view.y + Constants.DISTANCE
                view.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    fun setTitle(title: String) {
        groupview_title.text = title
    }

    fun setAdapter(adapter: GroupViewAdapter?) {
        groupview_list.adapter = adapter
    }

    fun showList() {
        groupview_list.visibility = View.VISIBLE
    }

    fun hideList() {
        groupview_list.visibility = View.GONE
    }

    fun isListOpened(): Boolean {
        return groupview_list.visibility == View.VISIBLE
    }

    /* LISTENERS */
}