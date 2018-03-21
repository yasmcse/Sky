package com.example.yasirnazir.sky.utils.display;

/**
 * Created by yasirnazir on 3/20/18.
 */

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Decoration that draws dividers around the items in a RecyclerView when using GridLayoutManager.
 */
public class GridDividerDecoration extends RecyclerView.ItemDecoration {
    private final int skipFirstN;
    private Drawable mDrawable;

    public GridDividerDecoration(Drawable drawable) {
        mDrawable = drawable;
        this.skipFirstN = 0;
    }

    public GridDividerDecoration(Drawable drawable, int skipFirstN) {
        mDrawable = drawable;
        this.skipFirstN = skipFirstN;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawVertical(c, parent);
        drawHorizontal(c, parent);
    }

    /**
     * Draw dividers at each expected grid interval
     */
    public void drawVertical(Canvas c, RecyclerView parent) {
        if (parent.getChildCount() == 0) return;

        final int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            if (i < skipFirstN)
                continue;

            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params =
                    (RecyclerView.LayoutParams) child.getLayoutParams();

            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin;
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }

    /**
     * Draw dividers to the right of each child view
     */
    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params =
                    (RecyclerView.LayoutParams) child.getLayoutParams();

            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDrawable.getIntrinsicWidth();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }
}
