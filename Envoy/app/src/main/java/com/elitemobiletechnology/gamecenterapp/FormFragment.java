package com.elitemobiletechnology.gamecenterapp;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.elitemobiletechnology.gamecenterapp.com.elitemobiletechnology.gamecenterapp.models.GameListModel;

import java.io.File;
import java.util.ArrayList;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.elitemobiletechnology.gamecenterapp.com.elitemobiletechnology.gamecenterapp.models.GameListModel;

import java.util.ArrayList;

import static com.elitemobiletechnology.gamecenterapp.R.id.btSubmit;

/**¬
 * Created by SteveYang on 3/6/15.
 */

public class FormFragment extends Fragment {
    private static final int TAKE_PICTURE = 1;
    private Uri imageUri;
    Button submit;
    Button upload;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.form_fragment, container, false);
        submit = (Button)view.findViewById(btSubmit);
        upload = (Button)view.findViewById(R.id.btUpload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photo));
                imageUri = Uri.fromFile(photo);
                startActivityForResult(intent, TAKE_PICTURE);
            }
        });
        return view;
    }
}