package com.example.tableview.table.view.holder

import android.view.View
import com.example.tableview.R
import com.example.tableview.table.view.TableViewModel

class GenderCellViewHolder(itemView: View) : MoodCellViewHolder(itemView) {

    override fun setData(data: Any?) {
        val gender = data as? Int ?: return
        val genderDrawable = if (gender == TableViewModel.BOY) {
            R.drawable.ic_male
        } else {
            R.drawable.ic_female
        }

        cellImage.setImageResource(genderDrawable)
    }
}
