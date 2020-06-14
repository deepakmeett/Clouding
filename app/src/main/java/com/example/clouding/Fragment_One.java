package com.example.clouding;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_One extends Fragment {

    EditText editText1, editText2;
    Button button1, button2, button3;
    FirebaseFirestore firebaseFirestore;
    String s1, s2;

    public Fragment_One() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.send_dlt_upd_text, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        editText1 = view.findViewById( R.id.edit1 );
        editText2 = view.findViewById( R.id.edit2 );
        button1 = view.findViewById( R.id.button1 );
        button2 = view.findViewById( R.id.button2 );
        button3 = view.findViewById( R.id.button3 );
        button1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseFirestore = FirebaseFirestore.getInstance();
                firebaseFirestore.collection( "Library" )
                        .document( "shelf" ).delete()
                        .addOnCompleteListener( new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText( getContext(), "Document Deleted Successfully"
                                            , Toast.LENGTH_SHORT ).show();
                                }
                            }
                        } );
            }
        } );
        button2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1 = editText1.getText().toString();
                s2 = editText2.getText().toString();
                HashMap<String, Object> map = new HashMap<>();
                map.put( "book_one", s1 );
                map.put( "book_two", s2 );
                firebaseFirestore = FirebaseFirestore.getInstance();
                firebaseFirestore.collection( "Library" )
                        .document( "shelf" ).set( map )
                        .addOnCompleteListener( new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText( getContext(), "Data sent on Cloud", Toast.LENGTH_SHORT ).show();
                            }
                        } );
                button3.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        s1 = editText1.getText().toString();
                        s2 = editText2.getText().toString();
                        firebaseFirestore = FirebaseFirestore.getInstance();
                        firebaseFirestore.collection( "Library" )
                                .document( "shelf" ).update( "book_one", s1 )
                                .addOnCompleteListener( new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText( getContext(),
                                                            s1 + " is updated", Toast.LENGTH_SHORT ).show();
                                        }
                                    }
                                } );
                        firebaseFirestore.collection( "Library" )
                                .document( "shelf" ).update( "book_two", s2 )
                                .addOnCompleteListener( new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText( getContext(),
                                                        s2 + " is updated", Toast.LENGTH_SHORT ).show();
                                    }
                                } );
                    }
                } );
            }

        } );
    }
}