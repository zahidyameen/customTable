package com.example.tableview.table.view

import com.example.tableview.table.view.model.Cell
import com.example.tableview.table.view.model.ColumnHeader
import com.example.tableview.table.view.model.RowHeader

class TableViewModel2 {
    fun getCellList(): List<List<Cell>> = getCellListForSortingTest()
    fun getRowHeaderList(): List<RowHeader> = getSimpleRowHeaderList()
    fun getColumnHeaderList(): List<ColumnHeader> = arrayListOf<ColumnHeader>(
        ColumnHeader(0.toString(), "column 1"),
        ColumnHeader(1.toString(), "column 2"),
        ColumnHeader(2.toString(), "column 3"),
        ColumnHeader(3.toString(), "column 4"),
        ColumnHeader(4.toString(), "column 5"),
        ColumnHeader(5.toString(), "column 6"),
        ColumnHeader(6.toString(), "column 8"),
        ColumnHeader(7.toString(), "column 8"),
        ColumnHeader(8.toString(), "column 9"),
    )


    private fun getCellListForSortingTest(): List<List<Cell>> {
        val list = mutableListOf<List<Cell>>()
        for (i in 0 until 1000) {
            val cellList = mutableListOf<Cell>()
            for (j in 0 until 9) {

                val id = "row $j -> column $i"
                val cell = Cell(id, id)
                cellList.add(cell)
            }
            list.add(cellList)
        }
        return list
    }


    private fun getSimpleRowHeaderList(): List<RowHeader> {
        val list = mutableListOf<RowHeader>()
        for (i in 0 until 1000) {
            val header = RowHeader(i.toString(), "row $i")
            list.add(header)
        }
        return list
    }
}