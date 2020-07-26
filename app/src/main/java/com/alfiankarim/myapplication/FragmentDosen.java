package com.alfiankarim.myapplication;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDosen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDosen extends Fragment implements View.OnClickListener {
    private EditText Email;
    private EditText pass;
    private Button btnLogin;
    private TextView signUp;
    View view;
    // Required empty public constructor
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    SQLiteDataHelper dbhelper ;
    public FragmentDosen() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentDosen.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDosen newInstance(String param1, String param2) {
        FragmentDosen fragment = new FragmentDosen();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_dosen, container, false);
        Email = view.findViewById(R.id.email_dosen);
        pass = view.findViewById(R.id.password_dosen);
        btnLogin = view.findViewById(R.id.btn_login1);
        signUp = view.findViewById(R.id.signup_txt1);
        btnLogin.setOnClickListener(this);
        signUp.setOnClickListener(this);
        SQLiteDataHelper dataHelper= new SQLiteDataHelper(getActivity());
        dataHelper.getWritableDatabase();
        return view;
            }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_login:
                String mEmail = Email.getText().toString();
                String mPass = pass.getText().toString();

                if (TextUtils.isEmpty(mEmail)) {
                    Email.setError("Harus diisi");
                    return;
                }
                if (TextUtils.isEmpty(mPass)) {
                    pass.setError("Harus diisi");
                    return;
                }
                Boolean masuk = dbhelper.checkLogin(mEmail, mPass);
                if (masuk == true) {
                        Toast.makeText(getActivity(), "Berhasil Masuk", Toast.LENGTH_SHORT).show();
                        Intent mainIntent = new Intent(getActivity(), Home_Activity.class);
                        startActivity(mainIntent);
                }else{
                    Toast.makeText(getActivity(), "Masuk Gagal", Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.signup_txt1 :
                startActivity(new Intent(getActivity(), Registration_Dosen_Activity.class));
                break;
        }
    }
}
