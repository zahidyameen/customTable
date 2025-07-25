/*
 * MIT License
 *
 * Copyright (c) 2021 Evren Coşkun
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.example.tableview.table.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tableview.table.adapter.AbstractTableAdapter;
import com.example.tableview.table.adapter.recyclerview.CellRecyclerView;
import com.example.tableview.table.handler.ScrollHandler;
import com.example.tableview.table.handler.SelectionHandler;
import com.example.tableview.table.handler.VisibilityHandler;
import com.example.tableview.table.layoutmanager.CellLayoutManager;
import com.example.tableview.table.layoutmanager.ColumnHeaderLayoutManager;
import com.example.tableview.table.listener.ITableViewListener;
import com.example.tableview.table.listener.scroll.HorizontalRecyclerViewListener;
import com.example.tableview.table.listener.scroll.VerticalRecyclerViewListener;



/**
 * Created by evrencoskun on 19/06/2017.
 */

public interface ITableView {

    void addView(View child, ViewGroup.LayoutParams params);

    boolean hasFixedWidth();

    boolean isIgnoreSelectionColors();

    boolean isShowHorizontalSeparators();

    boolean isShowVerticalSeparators();

    boolean isAllowClickInsideCell();


    @NonNull
    Context getContext();

    @NonNull
    CellRecyclerView getCellRecyclerView();

    @NonNull
    CellRecyclerView getColumnHeaderRecyclerView();

    @NonNull
    CellRecyclerView getRowHeaderRecyclerView();

    @NonNull
    ColumnHeaderLayoutManager getColumnHeaderLayoutManager();

    @NonNull
    CellLayoutManager getCellLayoutManager();

    @NonNull
    LinearLayoutManager getRowHeaderLayoutManager();

    @NonNull
    HorizontalRecyclerViewListener getHorizontalRecyclerViewListener();

    @NonNull
    VerticalRecyclerViewListener getVerticalRecyclerViewListener();

    @Nullable
    ITableViewListener getTableViewListener();

    @NonNull
    SelectionHandler getSelectionHandler();



    @NonNull
    VisibilityHandler getVisibilityHandler();

    @NonNull
    DividerItemDecoration getHorizontalItemDecoration();

    @NonNull
    DividerItemDecoration getVerticalItemDecoration();



    void scrollToColumnPosition(int column);

    void scrollToColumnPosition(int column, int offset);

    void scrollToRowPosition(int row);

    void scrollToRowPosition(int row, int offset);

    void showRow(int row);

    void hideRow(int row);

    boolean isRowVisible(int row);

    void showAllHiddenRows();

    void clearHiddenRowList();

    void showColumn(int column);

    void hideColumn(int column);

    boolean isColumnVisible(int column);

    void showAllHiddenColumns();

    void clearHiddenColumnList();

    int getShadowColor();

    int getSelectedColor();

    int getUnSelectedColor();

    int getSeparatorColor();



    void remeasureColumnWidth(int column);

    int getRowHeaderWidth();

    void setRowHeaderWidth(int rowHeaderWidth);

    boolean getShowCornerView();

    enum CornerViewLocation {
        TOP_LEFT(0),
        TOP_RIGHT(1),
        BOTTOM_LEFT(2),
        BOTTOM_RIGHT(3);
        int id;

        CornerViewLocation(int id) {
            this.id = id;
        }

        static CornerViewLocation fromId(int id) {
            for (CornerViewLocation c : values()) {
                if (c.id == id) return c;
            }
            // If enum not found return default of Top Left
            return TOP_LEFT;
        }
    }

    CornerViewLocation getCornerViewLocation();

    void setCornerViewLocation(CornerViewLocation cornerViewLocation);

    int getGravity();

    boolean getReverseLayout();

    void setReverseLayout(boolean reverseLayout);

    @Nullable
    AbstractTableAdapter getAdapter();
    /**
     * Retrieves the ScrollHandler of the TableView.
     *
     * @return The ScrollHandler of the TableView.
     */
    @NonNull
    ScrollHandler getScrollHandler();
}
