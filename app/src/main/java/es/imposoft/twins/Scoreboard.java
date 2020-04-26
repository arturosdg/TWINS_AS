package es.imposoft.twins;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.PreferenceManager;

import java.util.*;

public class Scoreboard implements Parcelable {
    private List<Integer> highscores;
    private int MAX_SCORES = 3;

    public Scoreboard(){
        highscores = new ArrayList();
    }

    public void addScore(int newScore){
        highscores.add(newScore);
    }

    public List<Integer> getHighscores(){
        Collections.sort(highscores);
        int size = highscores.size();
        if(size>3) {
            highscores = highscores.subList(size - MAX_SCORES, size);
        }
        Collections.sort(highscores, Collections.reverseOrder());
        return highscores;
    }

    public void loadHighscores(SharedPreferences sp) {
        highscores.clear();
        int size = sp.getInt("Status_size", 0);
        SharedPreferences.Editor mEdit1 = sp.edit();

        for(int i=0;i<size;i++)
        {
            highscores.add(sp.getInt("Status_" + i, 0));
        }

        mEdit1.clear();
        mEdit1.commit();
    }

    public void saveHighscores(SharedPreferences sp) {
        SharedPreferences.Editor mEdit1 = sp.edit();
        getHighscores();
        /* sKey is an array */
        mEdit1.putInt("Status_size", highscores.size());

        for(int i=0;i<highscores.size();i++)
        {
            mEdit1.remove("Status_" + i);
            mEdit1.putInt("Status_" + i, highscores.get(i));
        }
        mEdit1.commit();
    }

    protected Scoreboard(Parcel in) {
        if (in.readByte() == 0x01) {
            highscores = new ArrayList<Integer>();
            in.readList(highscores, Integer.class.getClassLoader());
        } else {
            highscores = null;
        }
    }

    public String getStarsFromScore(int score){
        String stars = "";

        int maxStars = 0;

        if(score<8){
            maxStars = 1;
        } else if(score<20){
            maxStars = 2;
        } else if(score<40){
            maxStars = 3;
        } else if(score<60){
            maxStars = 4;
        } else {
            maxStars = 5;
        }

        for(int i=1;i<=maxStars;i++){
            stars += "⭐";
        }
        return stars;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (highscores == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(highscores);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Scoreboard> CREATOR = new Parcelable.Creator<Scoreboard>() {
        @Override
        public Scoreboard createFromParcel(Parcel in) {
            return new Scoreboard(in);
        }

        @Override
        public Scoreboard[] newArray(int size) {
            return new Scoreboard[size];
        }
    };
}