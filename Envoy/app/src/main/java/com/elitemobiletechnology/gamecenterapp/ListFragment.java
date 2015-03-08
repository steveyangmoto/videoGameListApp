package com.elitemobiletechnology.gamecenterapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.elitemobiletechnology.gamecenterapp.com.elitemobiletechnology.gamecenterapp.models.VideoGameModel;
import com.elitemobiletechnology.gamecenterapp.com.elitemobiletechnology.gamecenterapp.utils.ApplicationUtil;
import com.elitemobiletechnology.gamecenterapp.com.elitemobiletechnology.gamecenterapp.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by SteveYang on 3/6/15.
 */

public class ListFragment extends Fragment {
    private static final String TAG = "ListFragment";
    boolean rateGames;
    ListView list;
    ListAdapter adapter;
    ArrayList<VideoGameModel> gameList;
    Gson gson = new Gson();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.list_fragment, container, false);
        list = (ListView) view.findViewById(R.id.list);  // List defined in XML ( See Below )

        Bundle bundle = getArguments();
        boolean launchRateGamesFragment = bundle == null ? false : bundle.getBoolean(Constants.IS_GAME_RATING);
        gameList = loadGameList();
        adapter = new ListAdapter(this.getActivity(), gameList, launchRateGamesFragment);
        list.setAdapter(adapter);
        return view;
    }

    private ArrayList<VideoGameModel> loadGameList() {
        ArrayList<VideoGameModel> games = new ArrayList<VideoGameModel>();
        String jsonStr = ApplicationUtil.getString(this.getActivity(), Constants.GAME_LIST_STORAGE_KEY);
        if (jsonStr != null) {
            Type listType = new TypeToken<ArrayList<VideoGameModel>>() {
            }.getType();
            games = gson.fromJson(jsonStr, listType);
        } else {
            games.add(new VideoGameModel(R.drawable.call_of_duty, "Call of Duty", "PS3",  false,0f));
            games.add(new VideoGameModel(R.drawable.battlefield_hardline, "Battlefield Hardline", "PS4", false,0f));
            games.add(new VideoGameModel(R.drawable.dragon_ball_xeno_verse, "Dragon Ball Xeno Verse", "Xbox 3", false,0f));
            games.add(new VideoGameModel(R.drawable.grand_theft_auto_v, "Grand Theft Auto", "PS3",  false,0f));
            games.add(new VideoGameModel(R.drawable.metal_gear_solid_v, "Metal Gear Solid V", "PS4",  false,0f));
            games.add(new VideoGameModel(R.drawable.iworld_of_warcraft, "World of Warcraft", "Xbox 360",  false,0f));
            games.add(new VideoGameModel(R.drawable.minecraft, "Minecraft", "PS3",  false,0f));
            games.add(new VideoGameModel(R.drawable.mortal_kombat_x, "Mortal Kombat X", "Wii",  false,0f));
        }
        return games;
    }


    @Override
    public void onPause() {
        Type listType = new TypeToken<ArrayList<VideoGameModel>>() {
        }.getType();
        String json = new Gson().toJson(gameList, listType);
        ApplicationUtil.saveToSharedPrefs(this.getActivity(), Constants.GAME_LIST_STORAGE_KEY, json);
        super.onPause();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.ADD_GAME_REQUEST&&resultCode== Activity.RESULT_OK) {
            Log.d(TAG, "here");
            Bundle bundle = getArguments();
            boolean launchRateGamesFragment = bundle == null ? false : bundle.getBoolean(Constants.IS_GAME_RATING);
            gameList = loadGameList();
            adapter = new ListAdapter(this.getActivity(), gameList, launchRateGamesFragment);
            list.setAdapter(adapter);
        }
    }

}