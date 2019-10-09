package com.example.clouding;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Five extends Fragment {
    VideoView videoView1, videoView2;
    ImageView imageView, imageView2, imageView4, imageView5;

    public Fragment_Five() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment__five, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView2 = view.findViewById(R.id.image6);
         imageView = view.findViewById(R.id.image3);
        videoView1 = view.findViewById(R.id.video1);
        videoView2 = view.findViewById(R.id.video2);
        imageView4 = view.findViewById(R.id.image4);
        imageView5 = view.findViewById(R.id.image5);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "select Video file"), 3);
            }
        });

        imageView4 .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView5.setVisibility(View.INVISIBLE);
                FirebaseStorage.getInstance().getReference()
                        .child("Video_Files").child("video.mp4").getDownloadUrl()
                        .addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                Uri uri = task.getResult();
                                MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(), uri);
                                mediaPlayer.start();
                                if (task.isSuccessful()){
                                    Toast.makeText(getActivity(),"Video will be played soon",Toast.LENGTH_LONG).show();
                                }
                                videoView2.setVideoURI(uri);
                                imageView4.setVisibility(View.INVISIBLE);
                                MediaController mediaController = new MediaController(getActivity());
                                videoView2.setMediaController(mediaController);
                                mediaController.setAnchorView(videoView2);
                            }
                        });
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3 && resultCode == RESULT_OK) {
            Uri video = data.getData();
            videoView1.setVideoURI(video);

            MediaController mediaController = new MediaController(getActivity());
            videoView1.setMediaController(mediaController);
            mediaController.setAnchorView(videoView1);

            imageView.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
            FirebaseStorage.getInstance().getReference()
                    .child("Video_Files").child("video.mp4").putFile(video)
                    .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity(), "Video file sent on Cloud",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });

        }

    }
}