package com.example.tableview.table.view.model

import com.example.tableview.table.filter.IFilterableModel
import com.example.tableview.table.sort.ISortableModel


open class Cell(
    private val id: String,
    val data: Any?
) : ISortableModel, IFilterableModel {

    private val filterKeyword: String = data?.toString() ?: ""

    /**
     * Necessary for sorting process
     */
    override fun getId(): String = id

    /**
     * Necessary for sorting process
     */
    override fun getContent(): Any? = data


    override fun getFilterableKeyword(): String = filterKeyword
}
