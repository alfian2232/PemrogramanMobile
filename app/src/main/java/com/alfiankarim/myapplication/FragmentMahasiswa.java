package com.alfiankarim.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMahasiswa#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMahasiswa extends Fragment implements View.OnClickListener {
    View view;
    private EditText email;
    private EditText pass;
    private Button btnLogin;
    private TextView signUp;
    private FirebaseAuth mAuth;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentMahasiswa() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMahasiswa.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMahasiswa newInstance(String param1, String param2) {
        FragmentMahasiswa fragment = new FragmentMahasiswa();
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
        view=inflater.inflate(R.layout.fragment_mahasiswa, container, false);
        mAuth = FirebaseAuth.getInstance();
        email = view.findViewById(R.id.email_login);
        pass = view.findViewById(R.id.password_login);
        btnLogin = view.findViewById(R.id.btn_login);
        signUp = view.findViewById(R.id.signup_txt);
        btnLogin.setOnClickListener(this);
        signUp.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_login:
                String mEmail = email.getText().toString().trim();
                String mPass = pass.getText().toString().trim();

                if (TextUtils.isEmpty(mEmail)) {
                    email.setError("Harus diisi");
                    return;
                }
                if (TextUtils.isEmpty(mPass)) {
                    pass.setError("Harus diisi");
                    return;
                }
                mAuth.signInWithEmailAndPassword(mEmail, mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getActivity(), Home_Activity.class));
                            Toast.makeText(getActivity(), "Successfull", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case R.id.signup_txt :
                startActivity(new Intent(getActivity(), Registration_Activity.class));
                break;
        }

    }
    }