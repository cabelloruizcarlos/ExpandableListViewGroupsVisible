package omnifi.co.uk.explistviewgroupsvisible.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import omnifi.co.uk.explistviewgroupsvisible.R

class GroupViewAdapter(private val pList: List<String>) : RecyclerView.Adapter<GroupViewAdapter.GroupViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {

        val view: ViewGroup = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_groupview, parent, false) as ViewGroup
        return GroupViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.textView.text = pList[position]
    }

    override fun getItemCount(): Int {
        return pList.size
    }

    /* VIEW HOLDER */

    class GroupViewHolder(val view: ViewGroup) : RecyclerView.ViewHolder(view) {

        val textView: TextView = view.getChildAt(0) as TextView
    }
}