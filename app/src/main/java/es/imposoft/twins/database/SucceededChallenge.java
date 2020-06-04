package es.imposoft.twins.database;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SucceededChallenge {

    private static List<String> succeededChallenges;
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
        succeededChallenges.add(challenge+"");
    }

    public List<String> getSuccedeedChallenges() {
        return succeededChallenges;
    }

    public void loadChallenges(SharedPreferences sp) {
        succeededChallenges.clear();
        if (sp.getStringSet("CHALLENGE" + email, null) != null) {
            Set<String> challenges = sp.getStringSet("CHALLENGE" + email, null);
            sp.getStringSet("CHALLENGE" + email, null);
            SharedPreferences.Editor mEdit1 = sp.edit();
            succeededChallenges.clear();
            succeededChallenges.addAll(challenges);

            mEdit1.commit();
        }
    }

    public void saveChallenges(SharedPreferences sp) {
        SharedPreferences.Editor mEdit1 = sp.edit();
        getSuccedeedChallenges();

        //Set the values
        Set<String> set = new HashSet<>();
        set.addAll(succeededChallenges);

        mEdit1.putStringSet("CHALLENGE" + email, set);
        mEdit1.commit();
    }
}
