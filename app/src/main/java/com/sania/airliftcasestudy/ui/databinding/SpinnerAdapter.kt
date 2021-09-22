package com.sania.airliftcasestudy.ui.databinding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.sania.airliftcasestudy.R
import com.sania.airliftcasestudy.models.SpinnerModel
import kotlinx.android.synthetic.main.custom_spinner_items.view.*

class SpinnerAdapter(context: Context, spinners: ArrayList<SpinnerModel>) : BaseAdapter() {

    var mContext = context
    var mSpinners = spinners

    override fun getCount(): Int {
        return mSpinners.size
    }

    override fun getItem(p0: Int): Any {
        return mSpinners[p0]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, view: View?, p2: ViewGroup?): View {
        val view = LayoutInflater.from(mContext).inflate(R.layout.custom_spinner_items,null)

        val spinnerModel: SpinnerModel = mSpinners[position]
        view.imageView.setImageResource(spinnerModel.flags)
        view.textView.text = spinnerModel.codes
        return view
    }
}