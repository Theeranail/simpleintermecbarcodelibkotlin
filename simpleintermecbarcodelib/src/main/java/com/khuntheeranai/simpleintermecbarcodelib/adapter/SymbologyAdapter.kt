package com.khuntheeranai.simpleintermecbarcodelib.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.khuntheeranai.simpleintermecbarcodelib.R
import com.khuntheeranai.simpleintermecbarcodelib.models.SymbologyItems
import java.util.ArrayList

class SymbologyAdapter : ArrayAdapter<SymbologyItems> {
    private lateinit var symbologyList: ArrayList<SymbologyItems>;

    private var myContext: Context? = null

    constructor(
        context: Context,
        extViewResourceId: Int,
        symbologyList: ArrayList<SymbologyItems>
    ) : super(context, extViewResourceId, symbologyList) {
        this.symbologyList = ArrayList()
        this.symbologyList.addAll(symbologyList)
        myContext = context.applicationContext
    }

    private class ViewHolder {
        var name: TextView? = null
        var status: CheckBox? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return super.getView(position, convertView, parent)
        var holder: ViewHolder? = null
        if (convertView == null) {
            var vi = myContext!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = vi.inflate(R.layout.symbology_info, null)
            holder = ViewHolder()
            holder.name = convertView.findViewById<View>(R.id.name) as TextView
            holder.status =
                convertView.findViewById<View>(R.id.checkBoxStatus) as CheckBox
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        val symItem = symbologyList[position]
        holder.name!!.text = symItem.name
        holder.status!!.isChecked = symItem.enabled
        holder.name!!.tag = symItem

        return convertView!!
    }
}