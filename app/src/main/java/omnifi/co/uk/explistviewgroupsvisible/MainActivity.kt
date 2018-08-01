package omnifi.co.uk.explistviewgroupsvisible

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val expandableListDetail = getData()
    private var groupHeight: Int = -1
    private var screenHeight: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val expandableListTitle = expandableListDetail.keys

        container.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                setVariables()
                if ((screenHeight > 0) && (groupHeight > 0))
                    setOnClickListeners()

                container.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun setVariables() {

        screenHeight = container.height
        groupHeight = past.height
        Constants.DISTANCE = (((screenHeight - (groupHeight * 4)) - resources.getDimension(R.dimen.activity_vertical_margin)).toInt())

        Log.d("Testing", "onCreate: screenHeight = $screenHeight; groupHeight = $groupHeight; distance = ${Constants.DISTANCE}")
    }

    private fun setOnClickListeners() {

        val onclickListener = View.OnClickListener {
            colapseAll()

            when (it.id) {
                R.id.today -> {
                    // Expand the Today's walks list
                    tomorrow.state = Constants.ListState.DOWN
                    future.state = Constants.ListState.DOWN
                    past.state = Constants.ListState.DOWN
                }
                R.id.tomorrow -> {
                    // Expand the Tomorrow's walks list
                    future.state = Constants.ListState.DOWN
                    past.state = Constants.ListState.DOWN
                }
                R.id.future -> {
                    // Expand the Future walks list
                    past.state = Constants.ListState.DOWN
                }
                R.id.past -> {
                    // Expand the Past walks list
                }
            }
        }
        today.setOnClickListener(onclickListener)
        tomorrow.setOnClickListener(onclickListener)
        future.setOnClickListener(onclickListener)
        past.setOnClickListener(onclickListener)
    }

    private fun colapseAll() {
        today.state = Constants.ListState.UP
        tomorrow.state = Constants.ListState.UP
        future.state = Constants.ListState.UP
        past.state = Constants.ListState.UP
    }

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

        val expandableListDetail = HashMap<String, List<String>>()
        expandableListDetail["CRICKET TEAMS"] = cricket
        expandableListDetail["FOOTBALL TEAMS"] = football
        expandableListDetail["BASKETBALL TEAMS"] = basketball
        expandableListDetail["BASKETBALL TEAMS2"] = basketball2

        return expandableListDetail
    }
}
