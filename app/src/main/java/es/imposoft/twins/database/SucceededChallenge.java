package es.imposoft.twins.database;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SucceededChallenge {

    private static List<Integer> succeededChallenges;
    private String email;

    public SucceededChallenge() {
        succeededChallenges = new ArrayList();
    }

    public SucceededChallenge(String email) {
        this.email = email;
        succeededChallenges = new ArrayList();
    }

    public int getChallenges() {
        return succeededChallenges.size();
    }

    public void addSuccedeedChallenges(int challenge) {
        succeededChallenges.add(challenge);
    }

    public List<Integer> getSuccedeedChallenges() {
        return succeededChallenges;
    }

    public void loadChallenges(SharedPreferences sp) {
        succeededChallenges.clear();
        if (sp.getString("CHALLENGE" + email, null) != null) {
            Gson gson = new Gson();
            SucceededChallenge challenges = gson.fromJson(sp.getString("CHALLENGE" + email, null), SucceededChallenge.class);

            SharedPreferences.Editor mEdit1 = sp.edit();
            succeededChallenges = challenges.getSuccedeedChallenges();

            mEdit1.commit();
        }
    }

    public void saveChallenges(SharedPreferences sp) {
        SharedPreferences.Editor mEdit1 = sp.edit();
        getSuccedeedChallenges();


        Gson gson = new Gson();
        String gchallenge = gson.toJson(this);
        mEdit1.putString("CHALLENGE" + email, gchallenge);
        mEdit1.commit();
    }
}
