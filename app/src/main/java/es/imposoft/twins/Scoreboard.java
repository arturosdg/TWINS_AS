package es.imposoft.twins;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import com.google.gson.Gson;

import java.util.*;

public class Scoreboard {
    private List<Integer> highscores;
    private int MAX_SCORES = 3;
    private int lastScore;
    private int id = 0;

    public Scoreboard(){
        lastScore = 0;
        highscores = new ArrayList();
    }

    public Scoreboard(int id){
        this.id = id;
        lastScore = 0;
        highscores = new ArrayList();
    }

    public void addScore(int newScore){
        lastScore = newScore;
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
        if(sp.getString("SCORE"+id,null) != null){
            Gson gson = new Gson();
            Scoreboard scoreboard = gson.fromJson(sp.getString("SCORE"+id,null),Scoreboard.class);

            SharedPreferences.Editor mEdit1 = sp.edit();
            highscores = scoreboard.getHighscores();

            mEdit1.clear();
            mEdit1.commit();
        }
    }

    public void saveHighscores(SharedPreferences sp) {
        SharedPreferences.Editor mEdit1 = sp.edit();
        getHighscores();

        Gson gson = new Gson();
        String gscoreboard = gson.toJson(this);
        mEdit1.putString("SCORE"+id, gscoreboard);
        mEdit1.commit();
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

    public int getLastScore() {
        return lastScore;
    }

    public String getSmileys() {
        return "\uD83D\uDE14\uD83D\uDE14\uD83D\uDE14\uD83D\uDE14\uD83D\uDE14";
    }
}