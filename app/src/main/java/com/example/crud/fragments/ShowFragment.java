package com.example.crud.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.crud.R;
import com.example.crud.adapters.ShowAdapter;
import com.example.crud.customclass.ShowContent;
import com.example.crud.customclass.ShowContent.ShowItem;
import com.example.crud.database.DatabaseColumns;
import com.example.crud.database.DatabaseQueries;

public class ShowFragment extends Fragment {

    // TODO: Customize parameters
    private int mColumnCount = 1;

    private Context mListener;
    FragmentManager fm;

    public ShowFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_list, container, false);

        fm = getActivity().getSupportFragmentManager();
        recyclerView = view.findViewById(R.id.list);

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), mColumnCount));
            }

        DatabaseQueries db = new DatabaseQueries(getContext());
        Cursor cursor = db.getData();

        ShowContent.ITEMS.clear();
        if(cursor.moveToFirst())
        {
            do {
                String id =cursor.getString(cursor.getColumnIndex(DatabaseColumns.ID));
                String name =cursor.getString(cursor.getColumnIndex(DatabaseColumns.NAME));
                String email =cursor.getString(cursor.getColumnIndex(DatabaseColumns.EMAIL));
                String password =cursor.getString(cursor.getColumnIndex(DatabaseColumns.PASSWORD));

                Log.e("data", name + email + password);

                ShowItem showItem =new ShowItem(id, name, email, password);
                ShowContent.ITEMS.add(showItem);

            }while (cursor.moveToNext());
        }

        ShowAdapter showAdapter = new ShowAdapter(ShowContent.ITEMS, getContext(), fm);
        recyclerView.setAdapter(showAdapter);

        return view;
    }

}
