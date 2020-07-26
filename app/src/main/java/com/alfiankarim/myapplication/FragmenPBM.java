package com.alfiankarim.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmenPBM#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmenPBM extends Fragment implements View.OnClickListener {
View view;
private EditText email_pbm,pass_pbm;
private Button btnlogin_pbm;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmenPBM() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmenPBM.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmenPBM newInstance(String param1, String param2) {
        FragmenPBM fragment = new FragmenPBM();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_fragmen_p_b_m, container, false);
        email_pbm=view.findViewById(R.id.email_pbm);
        pass_pbm=view.findViewById(R.id.password_pbm);
        btnlogin_pbm=view.findViewById(R.id.btn_login2);
        btnlogin_pbm.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login2:
                String mEmail_pbm= email_pbm.getText().toString();
                String mPass_pbm= pass_pbm.getText().toString();

                if(mEmail_pbm.equals("pbm") && mPass_pbm.equals("pbm123")){
                    Toast.makeText(getActivity(),"Login Succes", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getActivity(),Halaman_PBM.class));
                }else{
                    Toast.makeText(getActivity(),"Password dan Username salah", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}