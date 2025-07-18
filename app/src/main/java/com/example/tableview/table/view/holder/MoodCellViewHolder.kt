package com.example.tableview.table.view.holder

import android.view.View
import android.widget.ImageView
import com.example.tableview.R
import com.example.tableview.table.adapter.recyclerview.holder.AbstractViewHolder
import com.example.tableview.table.view.TableViewModel

open class MoodCellViewHolder(itemView: View) : AbstractViewHolder(itemView) {

    val cellImage: ImageView = itemView.findViewById(R.id.cell_image)

    open fun setData(data: Any?) {
        val mood = data as? Int ?: return
        val moodDrawable = if (mood == TableViewModel.HAPPY) {
            R.drawable.ic_happy
        } else {
            R.drawable.ic_sad
        }

        cellImage.setImageResource(moodDrawable)
    }
}
