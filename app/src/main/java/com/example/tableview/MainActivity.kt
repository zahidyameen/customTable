package com.example.tableview

import android.os.Bundle
import android.widget.RelativeLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tableview.table.view.TableView
import com.example.tableview.table.view.TableViewAdapter
import com.example.tableview.table.view.TableViewModel2

class MainActivity : AppCompatActivity() {
    private lateinit var mTableView: TableView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById<RelativeLayout>(R.id.fragment_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mTableView = findViewById(R.id.tableview)
        initializeTableView()
    }

    private fun initializeTableView() {
        val tableViewModel = TableViewModel2()
        val tableViewAdapter = TableViewAdapter()
        mTableView.setAdapter(tableViewAdapter)
        tableViewAdapter.setAllItems(
            tableViewModel.getColumnHeaderList(),
            tableViewModel.getRowHeaderList(),
            tableViewModel.getCellList()
        )
    }
}