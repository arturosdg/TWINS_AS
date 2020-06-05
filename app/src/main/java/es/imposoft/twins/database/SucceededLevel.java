package es.imposoft.twins.database;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SucceededLevel {

    private List<Integer> succedeedLevels;
    private int id;
    private String email;

    public SucceededLevel() {
        id = 0;
        succedeedLevels = new ArrayList();
    }

    public SucceededLevel(int id, String email) {
        this.id = id;
        this.email = email;
        succedeedLevels = new ArrayList();
    }

    public void addSuccedeedLevel(int level) {
        succedeedLevels.add(level);
    }

    public List<Integer> getSuccedeedLevels() {
        return succedeedLevels;
    }

    public void loadSuccedeedLevels(SharedPreferences sharedPreferences) {
        succedeedLevels.clear();
        if (sharedPreferences.getString("LEVEL" + id + email, null) != null) {
            Gson gson = new Gson();
            SucceededLevel levels = gson.fromJson(sharedPreferences.getString("LEVEL" + id + email, null), SucceededLevel.class);

            SharedPreferences.Editor editedSharedPreferences = sharedPreferences.edit();
            succedeedLevels = levels.getSuccedeedLevels(); 

            editedSharedPreferences.commit();
        }
    }

    public void saveSucceededLevels(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editedSharedPreferences = sharedPreferences.edit();
        getSuccedeedLevels();

        Gson gson = new Gson();
        String glevels = gson.toJson(this);
        editedSharedPreferences.putString("LEVEL" + id + email, glevels);
        editedSharedPreferences.commit();
    }
}