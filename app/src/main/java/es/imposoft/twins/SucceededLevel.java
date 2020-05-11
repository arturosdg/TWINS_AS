package es.imposoft.twins;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SucceededLevel {

    private List<Integer> succedeedLevels;
    private int id = 0;

    public SucceededLevel() {
        succedeedLevels = new ArrayList();
    }

    public SucceededLevel(int id) {
        this.id = id;
        succedeedLevels = new ArrayList();
    }

    public void addSuccedeedLevel(int level) {
        succedeedLevels.add(level);
    }

    public List<Integer> getSuccedeedLevels() {
        return succedeedLevels;
    }

    public void loadSuccedeedLevels(SharedPreferences sp) {
        succedeedLevels.clear();
        if (sp.getString("LEVEL" + id, null) != null) {
            Gson gson = new Gson();
            SucceededLevel levels = gson.fromJson(sp.getString("LEVEL" + id, null), SucceededLevel.class);

            SharedPreferences.Editor mEdit1 = sp.edit();
            succedeedLevels = levels.getSuccedeedLevels();

            mEdit1.clear();
            mEdit1.commit();
        }
    }

    public void saveSucceededLevels(SharedPreferences sp) {
        SharedPreferences.Editor mEdit1 = sp.edit();
        getSuccedeedLevels();

        Gson gson = new Gson();
        String glevels = gson.toJson(this);
        mEdit1.putString("LEVEL" + id, glevels);
        mEdit1.commit();
    }
}