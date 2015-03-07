package com.elitemobiletechnology.gamecenterapp;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.elitemobiletechnology.gamecenterapp.com.elitemobiletechnology.gamecenterapp.models.GameListModel;
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
    private static final String KEY = "myGameList";
    boolean rateGames;
    ListView list;
    ListAdapter adapter;
    ArrayList<GameListModel> gameList;
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

    private ArrayList<GameListModel> loadGameList() {
        ArrayList<GameListModel> games = new ArrayList<GameListModel>();
        String jsonStr = ApplicationUtil.getString(this.getActivity(), KEY);
        if (jsonStr != null) {
            Type listType = new TypeToken<ArrayList<GameListModel>>() {
            }.getType();
            games = gson.fromJson(jsonStr, listType);
        } else {
            games.add(new GameListModel(R.drawable.call_of_duty, "Call of Duty", "PS3", 0, false));
            games.add(new GameListModel(R.drawable.battlefield_hardline, "Battlefield Hardline", "PS4", 0, false));
            games.add(new GameListModel(R.drawable.dragon_ball_xeno_verse, "Dragon Ball Xeno Verse", "Xbox 3", 0, false));
            games.add(new GameListModel(R.drawable.grand_theft_auto_v, "Grand Theft Auto", "PS3", 0, false));
            games.add(new GameListModel(R.drawable.metal_gear_solid_v, "Metal Gear Solid V", "PS4", 0, false));
            games.add(new GameListModel(R.drawable.iworld_of_warcraft, "World of Warcraft", "Xbox 360", 0, false));
            games.add(new GameListModel(R.drawable.minecraft, "Minecraft", "PS3", 0, false));
            games.add(new GameListModel(R.drawable.mortal_kombat_x, "Mortal Kombat X", "Wii", 0, false));
        }
        return games;
    }


    @Override
    public void onPause() {
        Type listType = new TypeToken<ArrayList<GameListModel>>() {
        }.getType();
        String json = new Gson().toJson(gameList, listType);
        ApplicationUtil.saveToSharedPrefs(this.getActivity(), KEY, json);
        super.onPause();
    }


}