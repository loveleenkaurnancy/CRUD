package com.example.crud.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.MainActivity;
import com.example.crud.R;
import com.example.crud.database.DatabaseQueries;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditFragment extends Fragment {

    EditText name, email, password;
    Button edit;

    String txt_id, txt_name, txt_email, txt_password;


    public EditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit, container, false);

        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);

        txt_id = getArguments().getString("id");
        txt_name = getArguments().getString("name");
        txt_email = getArguments().getString("email");
        txt_password = getArguments().getString("password");

        Log.e("data2", txt_name + txt_email + txt_password);

        name.setText(txt_name);
        email.setText(txt_email);
        password.setText(txt_password);

        edit = view.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = name.getText().toString();
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();

                if(TextUtils.isEmpty(name1))
                {
                    name.setError("This field is required");
                }
                else if(TextUtils.isEmpty(email1))
                {
                    email.setError("This field is required");
                }
                else
                {
                    DatabaseQueries db = new DatabaseQueries(getContext());
                    long res = db.editData(txt_id, name1, email1, password1);
                    if(res > 0)
                    {
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getContext(), "Successfully Updated", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }

}
