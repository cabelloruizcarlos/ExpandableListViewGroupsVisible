package omnifi.co.uk.explistviewgroupsvisible.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import kotlinx.android.synthetic.main.activity_main.*
import omnifi.co.uk.explistviewgroupsvisible.R
import omnifi.co.uk.explistviewgroupsvisible.model.Constants
import omnifi.co.uk.explistviewgroupsvisible.ui.views.GroupView


class MainActivity : AppCompatActivity() {

    private val expandableListDetail = getData()
    private var groupHeight: Int = -1
    private var screenHeight: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                setVariables()

                if ((screenHeight > 0) && (groupHeight > 0)) {
                    today.setOnClickListener(onclickListener)
                    tomorrow.setOnClickListener(onclickListener)
                    future.setOnClickListener(onclickListener)
                    past.setOnClickListener(onclickListener)
                }

                container.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })

        setLists()
    }

    private fun setVariables() {

        screenHeight = container.height
        groupHeight = past.height
        Constants.DISTANCE = (((screenHeight - (groupHeight * 4)) - resources.getDimension(R.dimen.activity_vertical_margin)).toInt())
    }

    private fun setLists() {

        val expandableListTitle = expandableListDetail.keys

        today.setTitle(expandableListTitle.elementAt(0))
        tomorrow.setTitle(expandableListTitle.elementAt(1))
        future.setTitle(expandableListTitle.elementAt(2))
        past.setTitle(expandableListTitle.elementAt(3))

        var adapter = expandableListDetail[expandableListTitle.elementAt(0)]?.let { GroupViewAdapter(it) }
        today.setAdapter(adapter)

        adapter = expandableListDetail[expandableListTitle.elementAt(1)]?.let { GroupViewAdapter(it) }
        tomorrow.setAdapter(adapter)

        adapter = expandableListDetail[expandableListTitle.elementAt(2)]?.let { GroupViewAdapter(it) }
        future.setAdapter(adapter)

        adapter = expandableListDetail[expandableListTitle.elementAt(3)]?.let { GroupViewAdapter(it) }
        past.setAdapter(adapter)
    }

    /* LISTENERS */

    private val onclickListener = View.OnClickListener {

        val groupView: GroupView = it as GroupView
        val wasItOpened = groupView.isListOpened()

        // If there is any list open, I close it first...
        today.state = Constants.ListState.UP
        tomorrow.state = Constants.ListState.UP
        future.state = Constants.ListState.UP
        past.state = Constants.ListState.UP

        // ...then I expanded the one clicked, only if it wasn't already opened
        if (!wasItOpened)
            when (groupView.id) {
                R.id.today -> {
                    // Expand the Today's walks list
                    Log.d("Testing", "onclickListener: showing the Today list")
                    today.showList()
                    tomorrow.state = Constants.ListState.DOWN
                    future.state = Constants.ListState.DOWN
                    past.state = Constants.ListState.DOWN
                }
                R.id.tomorrow -> {
                    // Expand the Tomorrow's walks list
                    Log.d("Testing", "onclickListener: showing the Tomorrow list")
                    tomorrow.showList()
                    future.state = Constants.ListState.DOWN
                    past.state = Constants.ListState.DOWN
                }
                R.id.future -> {
                    // Expand the Future walks list
                    Log.d("Testing", "onclickListener: showing the Future list")
                    future.showList()
                    past.state = Constants.ListState.DOWN
                }
                R.id.past -> {
                    // Expand the Past walks list
                    Log.d("Testing", "onclickListener: showing the Past list")
                    past.showList()
                }
            }
    }

    /* BAKED DATA */

    private fun getData(): HashMap<String, List<String>> {

        val cricket = ArrayList<String>()
        cricket.add("India")
        cricket.add("Pakistan")
        cricket.add("Australia")
        cricket.add("England")
        cricket.add("South Africa")
        cricket.add("India")
        cricket.add("Pakistan")
        cricket.add("Australia")
        cricket.add("England")
        cricket.add("South Africa")
        cricket.add("India")
        cricket.add("Pakistan")
        cricket.add("Australia")
        cricket.add("England")
        cricket.add("South Africa")

        val football = ArrayList<String>()
        football.add("Brazil")
        football.add("Spain")
        football.add("Germany")
        football.add("Netherlands")
        football.add("Italy")
        football.add("Brazil")
        football.add("Spain")
        football.add("Germany")
        football.add("Netherlands")
        football.add("Italy")
        football.add("Brazil")
        football.add("Spain")
        football.add("Germany")
        football.add("Netherlands")
        football.add("Italy")

        val basketball = ArrayList<String>()
        basketball.add("United States")
        basketball.add("Spain")
        basketball.add("Argentina")
        basketball.add("France")
        basketball.add("Russia")
        basketball.add("United States")
        basketball.add("Spain")
        basketball.add("Argentina")
        basketball.add("France")
        basketball.add("Russia")
        basketball.add("United States")
        basketball.add("Spain")
        basketball.add("Argentina")
        basketball.add("France")
        basketball.add("Russia")

        val basketball2 = ArrayList<String>()
        basketball2.add("United States")
        basketball2.add("Spain")
        basketball2.add("Argentina")
        basketball2.add("France")
        basketball2.add("Russia")
        basketball2.add("United States")
        basketball2.add("Spain")
        basketball2.add("Argentina")
        basketball2.add("France")
        basketball2.add("Russia")
        basketball2.add("United States")
        basketball2.add("Spain")
        basketball2.add("Argentina")
        basketball2.add("France")
        basketball2.add("Russia")

        val expandableListDetail = HashMap<String, List<String>>()
        expandableListDetail["CRICKET TEAMS"] = cricket
        expandableListDetail["FOOTBALL TEAMS"] = football
        expandableListDetail["BASKETBALL TEAMS"] = basketball
        expandableListDetail["BASKETBALL TEAMS2"] = basketball2

        return expandableListDetail
    }
}