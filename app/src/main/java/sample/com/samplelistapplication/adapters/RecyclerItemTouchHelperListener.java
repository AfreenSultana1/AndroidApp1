package sample.com.samplelistapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public interface RecyclerItemTouchHelperListener {
    void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);

}
