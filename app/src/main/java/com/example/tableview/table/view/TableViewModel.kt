package com.example.tableview.table.view

import androidx.annotation.DrawableRes
import com.example.tableview.R
import com.example.tableview.table.view.model.Cell
import com.example.tableview.table.view.model.ColumnHeader
import com.example.tableview.table.view.model.RowHeader
import java.util.Random

class TableViewModel {

    companion object {
        // Column indexes
        const val MOOD_COLUMN_INDEX = 3
        const val GENDER_COLUMN_INDEX = 4

        // Constant values for icons
        const val SAD = 1
        const val HAPPY = 2
        const val BOY = 1
        const val GIRL = 2

        // Dummy data set sizes
        const val COLUMN_SIZE = 500
        private const val ROW_SIZE = 500
    }

    // Drawables
    @DrawableRes
    private val mBoyDrawable: Int = R.drawable.ic_male

    @DrawableRes
    private val mGirlDrawable: Int = R.drawable.ic_female

    @DrawableRes
    private val mHappyDrawable: Int = R.drawable.ic_happy

    @DrawableRes
    private val mSadDrawable: Int = R.drawable.ic_sad

    private fun getSimpleRowHeaderList(): List<RowHeader> {
        val list = mutableListOf<RowHeader>()
        for (i in 0 until ROW_SIZE) {
            val header = RowHeader(i.toString(), "row $i")
            list.add(header)
        }
        return list
    }

    private fun getRandomColumnHeaderList(): List<ColumnHeader> {
        val list = mutableListOf<ColumnHeader>()
        for (i in 0 until COLUMN_SIZE) {
            var title = "column $i"
            val nRandom = Random().nextInt()
            if (nRandom % 4 == 0 || nRandom % 3 == 0 || nRandom == i) {
                title = "large column $i"
            }
            val header = ColumnHeader(i.toString(), title)
            list.add(header)
        }
        return list
    }

    private fun getCellListForSortingTest(): List<List<Cell>> {
        val list = mutableListOf<List<Cell>>()
        for (i in 0 until ROW_SIZE) {
            val cellList = mutableListOf<Cell>()
            for (j in 0 until COLUMN_SIZE) {
                val random = Random().nextInt()
                val text: Any = when (j) {
                    0 -> i
                    1 -> random
                    MOOD_COLUMN_INDEX -> if (random % 2 == 0) HAPPY else SAD
                    GENDER_COLUMN_INDEX -> if (random % 2 == 0) BOY else GIRL
                    else -> "cell $j $i"
                }
                val id = "$j-$i"
                val cell = Cell(id, text)
                cellList.add(cell)
            }
            list.add(cellList)
        }
        return list
    }

    @DrawableRes
    fun getDrawable(value: Int, isGender: Boolean): Int {
        return if (isGender) {
            if (value == BOY) mBoyDrawable else mGirlDrawable
        } else {
            if (value == SAD) mSadDrawable else mHappyDrawable
        }
    }

    fun getCellList(): List<List<Cell>> = getCellListForSortingTest()

    fun getRowHeaderList(): List<RowHeader> = getSimpleRowHeaderList()

    fun getColumnHeaderList(): List<ColumnHeader> = getRandomColumnHeaderList()



}
