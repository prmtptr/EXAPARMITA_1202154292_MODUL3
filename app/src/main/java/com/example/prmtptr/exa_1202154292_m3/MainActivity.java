package com.example.prmtptr.exa_1202154292_m3;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecycleView;
    private ArrayList<water> mWaterData;
    private waterAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycleView = findViewById(R.id.recyclerview);

        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);
        mRecycleView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));
        mWaterData = new ArrayList<>();
        mAdapter = new waterAdapter(this, mWaterData);
        mRecycleView.setAdapter(mAdapter);

        initializeData();
        // If there is more than one column, disable swipe to dismiss
        int swipeDirs;
        if(gridColumnCount > 1){
            swipeDirs = 0;
        } else {
            swipeDirs = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        }


        //Helper class for creating swipe to dismiss and drag and drop functionality
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback
                (ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN
                        | ItemTouchHelper.UP, swipeDirs) {

            /**
             * Method that defines the drag and drop functionality
             * @param recyclerView The RecyclerView that contains the list items
             * @param viewHolder The SportsViewHolder that is being moved
             * @param target The SportsViewHolder that you are switching the original one with.
             * @return returns true if the item was moved, false otherwise
             */
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {

                //Get the from and to position
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();

                //Swap the items and notify the adapter
                Collections.swap(mWaterData, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            /**
             * Method that defines the swipe to dismiss functionality
             * @param viewHolder The viewholder being swiped
             * @param direction The direction it is swiped in
             */
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                //Remove the item from the dataset
                mWaterData.remove(viewHolder.getAdapterPosition());

                //Notify the adapter
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        //Attach the helper to the RecyclerView
        helper.attachToRecyclerView(mRecycleView);

    }

    private void initializeData() {

        TypedArray watersImageRes = getResources().obtainTypedArray(R.array.water_images);

        //Get the resources from the XML file
        String[] watersList = getResources().getStringArray(R.array.water_images);

        //Clear the existing data (to avoid duplication)
        mWaterData.clear();

        //Create the ArrayList of Sports objects with the titles and information about each sport
        for(int i=0;i<watersList.length;i++){
            String[] judul = {"aqua", "ades", "amidis", "cleo", "club", "equil",
                    "evian", "leminerale", "pristine", "vit", "nestle"};
            mWaterData.add(new water(judul[i], "Air minum merk "+judul[i], "air minum pennting untuk kesehata kita da sangaat di butuhkan oleh tubuh kita." +
                            " air minum juga memberi energi pada tubuh kita yang 80% terdiri dari air" +
                    " pentingnya meminum air putih karena kita sangat membutuhkan bantuan ir putih dalam tubuh kita " +
                    "minumlah air putih 12 gelas satu hari untuk memenuhi kebutuhan ",
                    watersImageRes.getResourceId(i,0)));
        }
        watersImageRes.recycle();
        mAdapter.notifyDataSetChanged();
    }
}

