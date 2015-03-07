package com.elitemobiletechnology.gamecenterapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.elitemobiletechnology.gamecenterapp.com.elitemobiletechnology.gamecenterapp.models.GameListModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.elitemobiletechnology.gamecenterapp.com.elitemobiletechnology.gamecenterapp.models.GameListModel;

import java.util.ArrayList;

import static com.elitemobiletechnology.gamecenterapp.R.id.btSubmit;
import static com.elitemobiletechnology.gamecenterapp.R.id.ivVideoGame;

/**
 * ¬
 * Created by SteveYang on 3/6/15.
 */

public class FormFragment extends Fragment {
    private static final String TAG = "FormFragment";
    private static final int TAKE_PICTURE = 1;
    private Uri imageUri;
    ImageView gamePicture;
    Button submit;
    Button upload;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.form_fragment, container, false);
        gamePicture = (ImageView) view.findViewById(R.id.ivVideoGame);
        submit = (Button) view.findViewById(btSubmit);
        upload = (Button) view.findViewById(R.id.btUpload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, TAKE_PICTURE);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this.getActivity(), "hey",Toast.LENGTH_LONG).show();
        if (requestCode == TAKE_PICTURE&&resultCode== Activity.RESULT_OK) {
            Bitmap bp = (Bitmap) data.getExtras()
                    .get("data");
            Log.d(TAG,"width:"+bp.getWidth()+"height:"+bp.getHeight());
            gamePicture.setImageBitmap(bp);
        }
    }
}