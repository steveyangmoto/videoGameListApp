package com.elitemobiletechnology.gamecenterapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.elitemobiletechnology.gamecenterapp.com.elitemobiletechnology.gamecenterapp.models.VideoGameModel;
import android.widget.Toast;
import com.elitemobiletechnology.gamecenterapp.com.elitemobiletechnology.gamecenterapp.utils.ApplicationUtil;
import com.elitemobiletechnology.gamecenterapp.com.elitemobiletechnology.gamecenterapp.utils.Constants;
import com.elitemobiletechnology.gamecenterapp.com.elitemobiletechnology.gamecenterapp.utils.Settings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.UUID;
import android.support.v7.app.ActionBar;
import static com.elitemobiletechnology.gamecenterapp.R.id.btSubmit;

/**
 * ¬
 * Created by SteveYang on 3/6/15.
 */

public class FormActivity extends ActionBarActivity {
    private static final String TAG = "FormFragment";
    private static Gson gson = new Gson();
    private static final int TAKE_PICTURE = 1;
    EditText gameTitle;
    EditText consoleType;
    ImageView gamePicture;
    Bitmap uploadedBitmap;
    ActionBar actionBar;
    Button submit;
    Button upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(R.color.white));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back_button);
        gamePicture = (ImageView) findViewById(R.id.ivVideoGame);
        gameTitle = (EditText)findViewById(R.id.etGameTitle);
        consoleType = (EditText)findViewById(R.id.etGameConsoleType);
        submit = (Button) findViewById(btSubmit);
        upload = (Button) findViewById(R.id.btUpload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, TAKE_PICTURE);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveGame();
            }
        });
    }

    private void saveGame(){
        String uuid = UUID.randomUUID().toString();
        if(gameTitle.getText().toString().isEmpty()){
            gameTitle.setError("Please enter the game title!");
            return;
        }
        if(consoleType.getText().toString().isEmpty()){
            consoleType.setError("Please enter game console type for the game!");
            return;
        }
        if(uploadedBitmap!=null){
            ApplicationUtil.saveFile(uploadedBitmap, uuid);
            VideoGameModel game = new VideoGameModel(uuid,gameTitle.getText().toString(),
                    consoleType.getText().toString(),false,0f);
            String jsonStr = ApplicationUtil.getString(this, Constants.GAME_LIST_STORAGE_KEY);
            Type listType = new TypeToken<ArrayList<VideoGameModel>>() {
            }.getType();
            ArrayList<VideoGameModel> games = gson.fromJson(jsonStr, listType);
            games.add(game);
            String json = new Gson().toJson(games, listType);
            ApplicationUtil.saveToSharedPrefs(this, Constants.GAME_LIST_STORAGE_KEY, json);
            setResult(RESULT_OK, new Intent());
            finish();
        }else{
            Toast.makeText(this,"Please upload a picture for the game!",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAKE_PICTURE&&resultCode== Activity.RESULT_OK) {
            Bitmap bp = (Bitmap) data.getExtras()
                    .get("data");
            bp = Bitmap.createScaledBitmap(bp, Settings.IMAGE_SIDE_LENGTH, Settings.IMAGE_SIDE_LENGTH, false);
            gamePicture.setImageBitmap(bp);
            uploadedBitmap = bp;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return (super.onOptionsItemSelected(menuItem));
    }
}