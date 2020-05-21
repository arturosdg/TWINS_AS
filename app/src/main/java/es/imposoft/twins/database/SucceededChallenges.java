package es.imposoft.twins.database;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SucceededChallenges {

    private List<Integer> succedeedLevels;
    private int id = 0;

    public SucceededChallenges() {
        succedeedLevels = new ArrayList();
    }

    public SucceededChallenges(int id) {
        this.id = id;
        succedeedLevels = new ArrayList();
    }

    public void addSuccedeedChallenges(int level) {
        succedeedLevels.add(level);
    }

    public List<Integer> getSuccedeedChallenges() {
        return succedeedLevels;
    }

    public void loadChallenges(SharedPreferences sp) {
        succedeedLevels.clear();
        if (sp.getString("CHALLENGE" + id, null) != null) {
            Gson gson = new Gson();
            SucceededChallenges levels = gson.fromJson(sp.getString("CHALLENGE" + id, null), SucceededChallenges.class);

            SharedPreferences.Editor mEdit1 = sp.edit();
            succedeedLevels = levels.getSuccedeedChallenges();

            mEdit1.commit();
        }
    }

    public void saveChallenges(SharedPreferences sp) {
        SharedPreferences.Editor mEdit1 = sp.edit();
        getSuccedeedChallenges();

        Gson gson = new Gson();
        String glevels = gson.toJson(this);
        mEdit1.putString("CHALLENGE" + id, glevels);
        mEdit1.commit();
    }
}
