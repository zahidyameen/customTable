package com.example.tableview.table.view

import com.example.tableview.table.view.model.Cell
import com.example.tableview.table.view.model.ColumnHeader
import com.example.tableview.table.view.model.RowHeader

class TableViewModel2 {
    fun getCellList(): List<List<Cell>> = getCellListForSortingTest()
    fun getRowHeaderList(): List<RowHeader> = getSimpleRowHeaderList()
    fun getColumnHeaderList(): List<ColumnHeader> = arrayListOf<ColumnHeader>(
        ColumnHeader(0.toString(), "column 1",0),
        ColumnHeader(1.toString(), "column 2",0),
        ColumnHeader(2.toString(), "column 3",0),
        ColumnHeader(3.toString(), "column 4",0),
        ColumnHeader(4.toString(), "column 5",0),
    )
    private fun getCellListForSortingTest(): List<List<Cell>> {
        val list = mutableListOf<List<Cell>>()
        listItems.forEachIndexed { i, item ->
            val cellList = mutableListOf<Cell>()
            cellList.add(Cell("row $i -> column 0", item.col1,0))
            cellList.add(Cell("row $i -> column 1", item.col2,0))
            cellList.add(Cell("row $i -> column 2", item.col3,0))
            cellList.add(Cell("row $i -> column 3", item.col4,1))
            cellList.add(Cell("row $i -> column 4", item.col5,2))
            list.add(cellList)
        }
        return list
    }

    private fun getSimpleRowHeaderList(): List<RowHeader> {
        val list = mutableListOf<RowHeader>()

        listItems.forEachIndexed { i, item ->
            val header = RowHeader(i.toString(), "row $i",0)
            list.add(header)
        }
        return list
    }
    data class DataModel(
        val col1: String?,
        val col2: String?,
        val col3: String?,
        val col4: String?,
        val col5: String?,
    )
    val listItems: List<DataModel> = arrayListOf(
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5"),
        DataModel("col1", "col2", "col3", "col4", "col5")
    )
}