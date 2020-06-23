package es.imposoft.twins.persistance.database;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scoreboard {
    private static int totalScore;
    private List<Integer> highscores;
    private int MAX_SCORES = 3;
    private int lastScore;
    private int id;
    private String email;

    public Scoreboard(){
        id = 0;
        lastScore = 0;
        highscores = new ArrayList();
    }

    public Scoreboard(int id, String email){
        this.id = id;
        this.email = email;
        lastScore = 0;
        highscores = new ArrayList();
        totalScore = 0;
    }

    public void addScore(int newScore){
        lastScore = newScore;
        highscores.add(newScore);
        totalScore += newScore;
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

    public void loadHighscores(SharedPreferences sharedPreferences) {
        highscores.clear();
        if(sharedPreferences.getString("SCORE" + id + email,null) != null){
            Gson gson = new Gson();
            Scoreboard scoreboard = gson.fromJson(sharedPreferences.getString("SCORE" + id + email,null),Scoreboard.class);

            SharedPreferences.Editor editedSharedPreferences = sharedPreferences.edit();
            highscores = scoreboard.getHighscores();

            editedSharedPreferences.commit();
        }
    }

    public void saveHighscores(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editedSharedPreferences = sharedPreferences.edit();
        getHighscores();

        Gson gson = new Gson();
        String gscoreboard = gson.toJson(this);
        editedSharedPreferences.putString("SCORE" + id + email, gscoreboard);
        editedSharedPreferences.commit();
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

    public static int getTotalScore() {
        return totalScore;
    }
}