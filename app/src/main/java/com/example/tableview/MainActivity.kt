package com.example.tableview

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tableview.table.adapter.AbstractTableAdapter
import com.example.tableview.table.filter.Filter
import com.example.tableview.table.pagination.Pagination
import com.example.tableview.table.view.TableView
import com.example.tableview.table.view.TableViewAdapter
import com.example.tableview.table.view.TableViewListener
import com.example.tableview.table.view.TableViewModel
import com.example.tableview.table.view.TableViewModel2

class MainActivity : AppCompatActivity() {
    private lateinit var moodFilter: Spinner
    private lateinit var genderFilter: Spinner
    private lateinit var previousButton: ImageButton
    private lateinit var nextButton: ImageButton
    private lateinit var tablePaginationDetails: TextView
    private lateinit var mTableView: TableView
    private var mTableFilter: Filter? = null
    private var mPagination: Pagination? = null

    private val mPaginationEnabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById<RelativeLayout>(R.id.fragment_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val searchField: EditText = findViewById(R.id.query_string)
        searchField.addTextChangedListener(mSearchTextWatcher)

        moodFilter = findViewById(R.id.mood_spinner)
        moodFilter.onItemSelectedListener = mItemSelectionListener

        genderFilter = findViewById(R.id.gender_spinner)
        genderFilter.onItemSelectedListener = mItemSelectionListener

        val itemsPerPage: Spinner = findViewById(R.id.items_per_page_spinner)
        val tableTestContainer: View = findViewById(R.id.table_test_container)

        previousButton = findViewById(R.id.previous_button)
        nextButton = findViewById(R.id.next_button)
        val pageNumberField: EditText = findViewById(R.id.page_number_text)
        tablePaginationDetails = findViewById(R.id.table_details)

        if (mPaginationEnabled) {
            tableTestContainer.visibility = View.VISIBLE
            itemsPerPage.onItemSelectedListener = onItemsPerPageSelectedListener

            previousButton.setOnClickListener(mClickListener)
            nextButton.setOnClickListener(mClickListener)
            pageNumberField.addTextChangedListener(onPageTextChanged)
        } else {
            tableTestContainer.visibility = View.GONE
        }

        mTableView = findViewById(R.id.tableview)
        initializeTableView()

        if (mPaginationEnabled) {
            mTableFilter = Filter(mTableView)
            mPagination = Pagination(mTableView)
            mPagination?.setOnTableViewPageTurnedListener(onTableViewPageTurnedListener)
        }
    }

    private fun initializeTableView() {
        val tableViewModel = TableViewModel2()
        val tableViewAdapter = TableViewAdapter(tableViewModel)

        mTableView.setAdapter(tableViewAdapter)
        mTableView.tableViewListener = TableViewListener(mTableView)

        tableViewAdapter.setAllItems(
            tableViewModel.getColumnHeaderList(),
            tableViewModel.getRowHeaderList(),
            tableViewModel.getCellList()
        )
    }

    fun filterTable(filter: String) {
        mTableFilter?.set(filter)
    }

    fun filterTableForMood(filter: String) {
        mTableFilter?.set(TableViewModel.MOOD_COLUMN_INDEX, filter)
    }

    fun filterTableForGender(filter: String) {
        mTableFilter?.set(TableViewModel.GENDER_COLUMN_INDEX, filter)
    }

    fun nextTablePage() {
        mPagination?.nextPage()
    }

    fun previousTablePage() {
        mPagination?.previousPage()
    }

    fun goToTablePage(page: Int) {
        mPagination?.goToPage(page)
    }

    fun setTableItemsPerPage(itemsPerPage: Int) {
        mPagination?.setItemsPerPage(itemsPerPage)
    }

    private val onTableViewPageTurnedListener = Pagination.OnTableViewPageTurnedListener { numItems, itemsStart, itemsEnd ->
        val currentPage = mPagination?.currentPage ?: 1
        val pageCount = mPagination?.pageCount ?: 1

        previousButton.visibility = View.VISIBLE
        nextButton.visibility = View.VISIBLE

        if (currentPage == 1 && pageCount == 1) {
            previousButton.visibility = View.INVISIBLE
            nextButton.visibility = View.INVISIBLE
        }

        if (currentPage == 1) {
            previousButton.visibility = View.INVISIBLE
        }

        if (currentPage == pageCount) {
            nextButton.visibility = View.INVISIBLE
        }

        tablePaginationDetails.text = getString(
            R.string.table_pagination_details,
            currentPage.toString(),
            itemsStart.toString(),
            itemsEnd.toString()
        )
    }

    private val mItemSelectionListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            if (position > 0) {
                val filter = position.toString()
                when (parent) {
                    moodFilter -> filterTableForMood(filter)
                    genderFilter -> filterTableForGender(filter)
                }
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>) {}
    }

    private val mSearchTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            filterTable(s.toString())
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    private val onItemsPerPageSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            val selected = parent.getItemAtPosition(position).toString()
            val itemsPerPage = if (selected == "All") 0 else selected.toInt()
            setTableItemsPerPage(itemsPerPage)
        }

        override fun onNothingSelected(parent: AdapterView<*>) {}
    }

    private val mClickListener = View.OnClickListener {
        when (it) {
            previousButton -> previousTablePage()
            nextButton -> nextTablePage()
        }
    }

    private val onPageTextChanged = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val page = if (s.isNullOrEmpty()) 1 else s.toString().toInt()
            goToTablePage(page)
        }

        override fun afterTextChanged(s: Editable?) {}
    }
}