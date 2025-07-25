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

package com.example.tableview.table.adapter.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tableview.table.view.ITableView;
import com.example.tableview.table.adapter.recyclerview.holder.AbstractViewHolder;
import com.example.tableview.table.handler.ScrollHandler;
import com.example.tableview.table.handler.SelectionHandler;
import com.example.tableview.table.layoutmanager.CellLayoutManager;
import com.example.tableview.table.layoutmanager.ColumnLayoutManager;
import com.example.tableview.table.listener.itemclick.CellRecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evrencoskun on 10/06/2017.
 */

public class CellRecyclerViewAdapter<C> extends AbstractRecyclerViewAdapter<C> {
    @NonNull
    private final ITableView mTableView;

    @NonNull
    private final RecyclerView.RecycledViewPool mRecycledViewPool;

    // This is for testing purpose
    private int mRecyclerViewId = 0;

    public CellRecyclerViewAdapter(@NonNull Context context, @Nullable List<C> itemList, @NonNull ITableView tableView) {
        super(context, itemList);
        this.mTableView = tableView;

        // Create view pool to share Views between multiple RecyclerViews.
        mRecycledViewPool = new RecyclerView.RecycledViewPool();
        //TODO set the right value.
        //mRecycledViewPool.setMaxRecycledViews(0, 110);
    }

    @NonNull
    @Override
    public AbstractViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Create a RecyclerView as a Row of the CellRecyclerView
        CellRecyclerView recyclerView = new CellRecyclerView(mContext);

        // Use the same view pool
        recyclerView.setRecycledViewPool(mRecycledViewPool);

        if (mTableView.isShowHorizontalSeparators()) {
            // Add divider
            recyclerView.addItemDecoration(mTableView.getHorizontalItemDecoration());
        }

        // To get better performance for fixed size TableView
        recyclerView.setHasFixedSize(mTableView.hasFixedWidth());

        // set touch mHorizontalListener to scroll synchronously
        recyclerView.addOnItemTouchListener(mTableView.getHorizontalRecyclerViewListener());

        // Add Item click listener for cell views
        if (mTableView.isAllowClickInsideCell()) {
            recyclerView.addOnItemTouchListener(new CellRecyclerViewItemClickListener(recyclerView,
                    mTableView));
        }

        // Set the Column layout manager that helps the fit width of the cell and column header
        // and it also helps to locate the scroll position of the horizontal recyclerView
        // which is row recyclerView
        ColumnLayoutManager mColumnLayoutManager = new ColumnLayoutManager(mContext, mTableView);
        if (mTableView.getReverseLayout()) mColumnLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(mColumnLayoutManager);

        // Create CellRow adapter
        recyclerView.setAdapter(new CellRowRecyclerViewAdapter<>(mContext, mTableView));

        // This is for testing purpose to find out which recyclerView is displayed.
        recyclerView.setId(mRecyclerViewId);

        mRecyclerViewId++;

        return new CellRowViewHolder(recyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull AbstractViewHolder holder, int yPosition) {
        CellRowViewHolder viewHolder = (CellRowViewHolder) holder;
        CellRowRecyclerViewAdapter viewAdapter = (CellRowRecyclerViewAdapter) viewHolder
                .recyclerView.getAdapter();

        // Get the list
        List<C> rowList = (List<C>) mItemList.get(yPosition);

        // Set Row position
        viewAdapter.setYPosition(yPosition);

        // Set the list to the adapter
        viewAdapter.setItems(rowList);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull AbstractViewHolder holder) {
        super.onViewAttachedToWindow(holder);

        CellRowViewHolder viewHolder = (CellRowViewHolder) holder;

        ScrollHandler scrollHandler = mTableView.getScrollHandler();

        // The below code helps to display a new attached recyclerView on exact scrolled position.
        ((ColumnLayoutManager) viewHolder.recyclerView.getLayoutManager())
                .scrollToPositionWithOffset(scrollHandler.getColumnPosition(), scrollHandler
                        .getColumnPositionOffset());

        SelectionHandler selectionHandler = mTableView.getSelectionHandler();

        if (selectionHandler.isAnyColumnSelected()) {

            AbstractViewHolder cellViewHolder = (AbstractViewHolder) viewHolder.recyclerView
                    .findViewHolderForAdapterPosition(selectionHandler.getSelectedColumnPosition());

            if (cellViewHolder != null) {
                // Control to ignore selection color
                if (!mTableView.isIgnoreSelectionColors()) {
                    cellViewHolder.setBackgroundColor(mTableView.getSelectedColor());
                }
                cellViewHolder.setSelected(AbstractViewHolder.SelectionState.SELECTED);

            }
        } else if (selectionHandler.isRowSelected(holder.getAdapterPosition())) {
            selectionHandler.changeSelectionOfRecyclerView(viewHolder.recyclerView,
                    AbstractViewHolder.SelectionState.SELECTED, mTableView.getSelectedColor());
        }

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull AbstractViewHolder holder) {
        super.onViewDetachedFromWindow(holder);

        // Clear selection status of the view holder
        mTableView.getSelectionHandler().changeSelectionOfRecyclerView(((CellRowViewHolder)
                holder).recyclerView, AbstractViewHolder.SelectionState.UNSELECTED, mTableView.getUnSelectedColor());
    }

    @Override
    public void onViewRecycled(@NonNull AbstractViewHolder holder) {
        super.onViewRecycled(holder);

        CellRowViewHolder viewHolder = (CellRowViewHolder) holder;
        // ScrolledX should be cleared at that time. Because we need to prepare each
        // recyclerView
        // at onViewAttachedToWindow process.
        viewHolder.recyclerView.clearScrolledX();
    }

    static class CellRowViewHolder extends AbstractViewHolder {
        final CellRecyclerView recyclerView;

        CellRowViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = (CellRecyclerView) itemView;
        }
    }

    public void notifyCellDataSetChanged() {
        CellRecyclerView[] visibleRecyclerViews = mTableView.getCellLayoutManager()
                .getVisibleCellRowRecyclerViews();

        if (visibleRecyclerViews.length > 0) {
            for (CellRecyclerView cellRowRecyclerView : visibleRecyclerViews) {
                if (cellRowRecyclerView != null) {
                    RecyclerView.Adapter adapter = cellRowRecyclerView.getAdapter();
                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        } else {
            notifyDataSetChanged();
        }
    }

    /**
     * This method helps to get cell item model that is located on given column position.
     *
     * @param columnPosition
     */
    @NonNull
    public List<C> getColumnItems(int columnPosition) {
        List<C> cellItems = new ArrayList<>();

        for (int i = 0; i < mItemList.size(); i++) {
            List<C> rowList = (List<C>) mItemList.get(i);

            if (rowList.size() > columnPosition) {
                cellItems.add(rowList.get(columnPosition));
            }
        }

        return cellItems;
    }


    public void removeColumnItems(int column) {

        // Firstly, remove columns from visible recyclerViews.
        // To be able provide removing animation, we need to notify just for given column position.
        CellRecyclerView[] visibleRecyclerViews = mTableView.getCellLayoutManager()
                .getVisibleCellRowRecyclerViews();

        for (CellRecyclerView cellRowRecyclerView : visibleRecyclerViews) {
            if (cellRowRecyclerView != null) {
                AbstractRecyclerViewAdapter adapter = (AbstractRecyclerViewAdapter) cellRowRecyclerView.getAdapter();
                if (adapter != null) {
                    adapter.deleteItem(column);
                }
            }
        }

        // Lets change the model list silently
        // Create a new list which the column is already removed.
        List<List<C>> cellItems = new ArrayList<>();
        for (int i = 0; i < mItemList.size(); i++) {
            List<C> rowList = new ArrayList<>((List<C>) mItemList.get(i));

            if (rowList.size() > column) {
                rowList.remove(column);
            }

            cellItems.add(rowList);
        }

        // Change data without notifying. Because we already did for visible recyclerViews.
        setItems((List<C>) cellItems, false);
    }

    public void addColumnItems(int column, @NonNull List<C> cellColumnItems) {
        // It should be same size with exist model list.
        if (cellColumnItems.size() != mItemList.size() || cellColumnItems.contains(null)) {
            return;
        }

        // Firstly, add columns from visible recyclerViews.
        // To be able provide removing animation, we need to notify just for given column position.
        CellLayoutManager layoutManager = mTableView.getCellLayoutManager();
        for (int i = layoutManager.findFirstVisibleItemPosition(); i < layoutManager
                .findLastVisibleItemPosition() + 1; i++) {
            // Get the cell row recyclerView that is located on i position
            RecyclerView cellRowRecyclerView = (RecyclerView) layoutManager.findViewByPosition(i);

            // Add the item using its adapter.
            ((AbstractRecyclerViewAdapter) cellRowRecyclerView.getAdapter()).addItem(column,
                    cellColumnItems.get(i));
        }


        // Lets change the model list silently
        List<List<C>> cellItems = new ArrayList<>();
        for (int i = 0; i < mItemList.size(); i++) {
            List<C> rowList = new ArrayList<>((List<C>) mItemList.get(i));

            if (rowList.size() > column) {
                rowList.add(column, cellColumnItems.get(i));
            }

            cellItems.add(rowList);
        }

        // Change data without notifying. Because we already did for visible recyclerViews.
        setItems((List<C>) cellItems, false);
    }
}
