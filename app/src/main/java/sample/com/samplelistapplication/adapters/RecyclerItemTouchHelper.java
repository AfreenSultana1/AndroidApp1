package sample.com.samplelistapplication.adapters;

import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {
    private RecyclerItemTouchHelperListener listener;

    public RecyclerItemTouchHelper(int dragDirs, int swipeDirs, RecyclerItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    /*getDefaultUIUtil() will be used by ItemTouchHelper to detect whenever there is UI change on the view.
    We use this function to keep the background view in a static position and move the foreground view.*/
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);

        if (viewHolder != null) {
            final View foreGroundView = ((SampleRecyclerAdapter.SampleViewHolder) viewHolder).foreGroundView;
            getDefaultUIUtil().onSelected(foreGroundView);
        }
    }
    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        final View foreGorundView = ((SampleRecyclerAdapter.SampleViewHolder) viewHolder).foreGroundView;
        getDefaultUIUtil().onDrawOver(c, recyclerView, foreGorundView, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        final View foreGroundView = ((SampleRecyclerAdapter.SampleViewHolder) viewHolder).foreGroundView;
        getDefaultUIUtil().clearView(foreGroundView);
    }
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        final View foreGroundView = ((SampleRecyclerAdapter.SampleViewHolder) viewHolder).foreGroundView;
        getDefaultUIUtil().onDraw(c, recyclerView, foreGroundView, dX, dY, actionState, isCurrentlyActive);
    }
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());
    }
    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

}
