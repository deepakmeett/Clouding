package com.example.clouding;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Six extends Fragment {
    ImageView pdf, image01;


    public Fragment_Six() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.pdf_frag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pdf = view.findViewById(R.id.pdf);
        image01 = view.findViewById(R.id.image01);

        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("application/pdf");
                intent.setAction(intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select PDF file"), 4);
            }
        });

        image01.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseStorage.getInstance().getReference()
                        .child("PDF_FILES").child("pdf")
                        .getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        Uri uri = task.getResult();
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(uri, "application/pdf");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        Intent intent1 = Intent.createChooser(intent, "Open File");
                        try {
                            startActivity(intent1);
                        } catch (ActivityNotFoundException e) {

                        }


                    }
                });
            }
        } );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 4 && resultCode == RESULT_OK) ;
        Uri pdf = data.getData();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(pdf, "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Intent intent1 = Intent.createChooser(intent, "Open File");
        try {
            startActivity(intent1);
        } catch (ActivityNotFoundException e) {

        }


        FirebaseStorage.getInstance().getReference()
                .child("PDF_FILES").child("pdf").putFile(pdf)
                .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        Toast.makeText(getContext(), "PDF file sent on Cloud", Toast.LENGTH_LONG).show();
                    }
                });
//        FirebaseStorage.getInstance().getReference().child("PDF_Files").child("pdf").getDownloadUrl()
//                .addOnCompleteListener(new OnCompleteListener<Uri>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Uri> task) {
//                        Uri uri = task.getResult();
//                        Intent intent = new Intent(Intent.ACTION_VIEW);
//                        File file = new File(Environment
//                                .getExternalStorageDirectory().getAbsolutePath() + uri);
//                        intent.setDataAndType(Uri.fromFile(file), "application/pdf");
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//
//                        Intent intent1 = Intent.createChooser(intent, "Open File");
//                        try {
//                            startActivity(intent1);
//                        } catch (ActivityNotFoundException e) {
//
//                        }
//                    }
//                });


    }
}
