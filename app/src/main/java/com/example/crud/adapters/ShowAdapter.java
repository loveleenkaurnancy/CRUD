package com.example.crud.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crud.MainActivity;
import com.example.crud.R;
import com.example.crud.customclass.ShowContent.ShowItem;
import com.example.crud.database.DatabaseQueries;
import com.example.crud.fragments.EditFragment;

import java.util.List;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ViewHolder> {

    private final List<ShowItem> mValues;
    private final Context context;
    private final FragmentManager fm;

    public ShowAdapter(List<ShowItem> items, Context listener, FragmentManager fragmentManager) {
        mValues = items;
        context = listener;
        fm = fragmentManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_show, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.name.setText(mValues.get(position).name);
        holder.email.setText(mValues.get(position).email);
        holder.password.setText(mValues.get(position).password);

        final String id = mValues.get(position).id;
        final String name = mValues.get(position).name;
        final String email = mValues.get(position).email;
        final String password = mValues.get(position).password;

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try
                {

                    Fragment fragment=null;
                    Class fragmentClass;

                    Bundle bundle= new Bundle();
                    bundle.putString("id",id);
                    bundle.putString("name",name);
                    bundle.putString("email",email);
                    bundle.putString("password",password);

                    fragmentClass = EditFragment.class;

                    fragment = (Fragment) fragmentClass.newInstance();
                    fragment.setArguments(bundle);

                    fm.beginTransaction().replace(R.id.content, fragment).commit();
                }
                catch (Exception e)
                {
                    Log.e("Error", e.getMessage());
                }

            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseQueries db = new DatabaseQueries(view.getRootView().getContext());
                long res = db.deleteData(id);
                if(res > 0)
                {
                    Intent intent = new Intent(view.getRootView().getContext(), MainActivity.class);
                    view.getRootView().getContext().startActivity(intent);
                    Toast.makeText(view.getRootView().getContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(view.getRootView().getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView name;
        public final TextView email;
        public final TextView password;
        public final Button edit;
        public final Button delete;
        public ShowItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            name = (TextView) view.findViewById(R.id.name);
            email = (TextView) view.findViewById(R.id.email);
            password = (TextView) view.findViewById(R.id.password);
            edit = (Button) view.findViewById(R.id.edit);
            delete = (Button) view.findViewById(R.id.delete);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + name.getText() + "'";
        }
    }
}
