package com.example.clouding;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Seven extends Fragment {

    RecyclerView recyclerView;
    FirebaseFirestore firebaseFirestore;

    public Fragment_Seven() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_fragment_seven, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        recyclerView = view.findViewById( R.id.recycler );
        
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection( "Library" )
                .addSnapshotListener( new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent
                            (@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots,
                             @javax.annotation.Nullable FirebaseFirestoreException e) {
                        Adapter_Clouding adapter_clouding = new Adapter_Clouding();
                        for (DocumentChange documentChange : queryDocumentSnapshots.getDocumentChanges()) {
                            Model_Clouding model_clouding = documentChange
                                    .getDocument().toObject( Model_Clouding.class );
                            adapter_clouding.book_add( model_clouding );
                        }
                        
                        recyclerView.setLayoutManager( new GridLayoutManager( getActivity(), 3 ) );
                        recyclerView.setHasFixedSize( true );
                        recyclerView.setAdapter( adapter_clouding );
                        Toast.makeText( getActivity(), "Recycler view has completed", Toast.LENGTH_LONG ).show();
                    }
                } );
    }
}