package com.elitemobiletechnology.gamecenterapp;

/**
 * Created by SteveYang on 3/6/15.
 */

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.elitemobiletechnology.gamecenterapp.com.elitemobiletechnology.gamecenterapp.models.VideoGameModel;
import com.elitemobiletechnology.gamecenterapp.com.elitemobiletechnology.gamecenterapp.utils.ApplicationUtil;

public class ListAdapter extends BaseAdapter {
    private static final String TAG = "ListAdapter";
    private Activity activity;
    private ArrayList<VideoGameModel> gameList;
    private LayoutInflater inflater;
    private boolean rateGames;

    public ListAdapter(Activity a, ArrayList<VideoGameModel> gameList, Boolean isRating) {
        activity = a;
        this.gameList = gameList;
        rateGames = isRating;
        inflater = (LayoutInflater) a
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return gameList.size();
    }

    public Object getItem(int position) {
        return gameList.get(position);
    }

    public long getItemId(int position) {
        return gameList.get(position).hashCode();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final VideoGameModel aGame = gameList.get(position);
        View vi = convertView;
        ViewHolder holder = null;
        if (vi == null) {
            vi = inflater.inflate(R.layout.listview_row, parent, false);
            holder = new ViewHolder();
            holder.thumbnail = (ImageView) vi.findViewById(R.id.imageView);
            holder.finished = (CheckBox) vi.findViewById(R.id.checkBox);
            holder.consoleName = (TextView) vi.findViewById(R.id.tvConsole);
            holder.gameTitle = (TextView) vi.findViewById(R.id.tvGameTitle);
            holder.ratingBar = (RatingBar) vi.findViewById(R.id.ratingBar);
            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }
        if (rateGames) {
            holder.ratingBar.setVisibility(View.VISIBLE);
            holder.ratingBar.setRating(aGame.getRating());
            holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    if (fromUser) {
                        aGame.setRating(rating);
                    }
                }
            });
        } else {
            ((View) holder.finished.getParent()).setVisibility(View.VISIBLE);
            holder.finished.setChecked(aGame.isFinished());
            holder.finished.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    aGame.setFinished(((CheckBox) v).isChecked());
                }
            });
        }
        holder.gameTitle.setText(aGame.getGameTitle());
        holder.consoleName.setText(aGame.getConsole());
        if(aGame.getThumbnailResourceId()!=0) {
            holder.thumbnail.setImageResource(aGame.getThumbnailResourceId());
        }else{
            Drawable d = Drawable.createFromPath(ApplicationUtil.getAbsoluteFilePath(aGame.getFileName()));
            Bitmap b = ((BitmapDrawable)d).getBitmap();
            holder.thumbnail.setImageBitmap(b);
        }
        return vi;
    }

    static class ViewHolder {
        TextView gameTitle;
        TextView consoleName;
        CheckBox finished;
        ImageView thumbnail;
        RatingBar ratingBar;
    }
}