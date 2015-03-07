package com.elitemobiletechnology.gamecenterapp;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.elitemobiletechnology.gamecenterapp.com.elitemobiletechnology.gamecenterapp.models.GameListModel;

import java.util.ArrayList;

/**
 * Created by SteveYang on 3/6/15.
 */

public class ListFragment extends Fragment {
    boolean rateGames;
    ListView list;
    ListAdapter adapter;
    ArrayList<GameListModel> gamelist = new ArrayList<GameListModel>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.list_fragment, container, false);
        list = (ListView) view.findViewById(R.id.list);  // List defined in XML ( See Below )

        gamelist.add(new GameListModel(R.drawable.call_of_duty, "Call of Duty", "PS3", false));
        gamelist.add(new GameListModel(R.drawable.battlefield_hardline, "Battlefield Hardline", "PS4", false));
        gamelist.add(new GameListModel(R.drawable.dragon_ball_xeno_verse, "Dragon Ball Xeno Verse", "Xbox 3", false));
        gamelist.add(new GameListModel(R.drawable.grand_theft_auto_v, "Grand Theft Auto", "PS3", false));
        gamelist.add(new GameListModel(R.drawable.metal_gear_solid_v, "Metal Gear Solid V", "PS4", false));
        gamelist.add(new GameListModel(R.drawable.iworld_of_warcraft, "World of Warcraft", "Xbox 360", false));
        gamelist.add(new GameListModel(R.drawable.minecraft, "Minecraft", "PS3", false));
        gamelist.add(new GameListModel(R.drawable.mortal_kombat_x, "Mortal Kombat X", "Wii", false));
        Bundle bundle = getArguments();
        boolean lanchRateGamesFragment = bundle==null?false:bundle.getBoolean(Constants.IS_GAME_RATING);
        adapter = new ListAdapter(this.getActivity(), gamelist, lanchRateGamesFragment);
        list.setAdapter(adapter);
        return view;
    }
}