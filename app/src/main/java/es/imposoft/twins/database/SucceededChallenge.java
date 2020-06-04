package es.imposoft.twins.database;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SucceededChallenge {

    private static List<Integer> succeededChallenges;
    private int id = 0;
    private String email;

    public SucceededChallenge() {
        succeededChallenges = new ArrayList();
    }

    public SucceededChallenge(String email) {
        //this.id = id;
        this.email = email;
        succeededChallenges = new ArrayList();
    }

    public int getChallenges() {
        return succeededChallenges.size();
    }

    public void addSuccedeedChallenges(int level) {
        succeededChallenges.add(level);
    }

    public List<Integer> getSuccedeedChallenges() {
        return succeededChallenges;
    }

    public void loadChallenges(SharedPreferences sp) {
        succeededChallenges.clear();
        if (sp.getString("CHALLENGE" + id + email, null) != null) {
            Gson gson = new Gson();
            SucceededChallenge levels = gson.fromJson(sp.getString("CHALLENGE" + id + email, null), SucceededChallenge.class);

            SharedPreferences.Editor mEdit1 = sp.edit();
            succeededChallenges = levels.getSuccedeedChallenges();

            mEdit1.commit();
        }
    }

    public void saveChallenges(SharedPreferences sp) {
        SharedPreferences.Editor mEdit1 = sp.edit();
        getSuccedeedChallenges();


        Gson gson = new Gson();
        String glevels = gson.toJson(this);
        mEdit1.putString("CHALLENGE" + id + email, glevels);
        mEdit1.commit();
    }
}
