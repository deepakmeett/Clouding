package com.example.clouding;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Two extends Fragment {
    TextView textView1, textView2;
    Button button5;
    FirebaseFirestore firebaseFirestore;
    FragmentTransaction fragmentTransaction;


    public Fragment_Two() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.text_retrieve_frag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button5 = view.findViewById(R.id.button5);
        textView1 = view.findViewById(R.id.text1);
        textView2 = view.findViewById(R.id.text2);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction = getFragmentManager().beginTransaction();
                Fragment fragment = new Fragment_Eight();
                fragmentTransaction.replace(R.id.f1, fragment);
                fragmentTransaction.commit();

            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseFirestore = FirebaseFirestore.getInstance();
                firebaseFirestore.collection("Library")
                        .document("shelf").get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                String book_one = task.getResult().get("book_one").toString();
                                textView1.setText(book_one);
                                String book_two= task.getResult().get("book_two").toString();
                                textView2.setText(book_two);
                            }
                        });
            }
        });
    }
}