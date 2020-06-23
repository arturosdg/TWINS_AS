package es.imposoft.twins.persistance.database;

import android.content.SharedPreferences;

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

    public void loadChallenges(SharedPreferences sharedPreferences) {
        succeededChallenges.clear();
        if (sharedPreferences.getStringSet("CHALLENGE" + email, null) != null) {
            Set<String> challenges = sharedPreferences.getStringSet("CHALLENGE" + email, null);
            sharedPreferences.getStringSet("CHALLENGE" + email, null);
            SharedPreferences.Editor editedSharedPreferences = sharedPreferences.edit();
            succeededChallenges.clear();
            succeededChallenges.addAll(challenges);

            editedSharedPreferences.commit();
        }
    }

    public void saveChallenges(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editedSharedPreferences = sharedPreferences.edit();
        getSuccedeedChallenges();

        //Set the values
        Set<String> set = new HashSet<>();
        set.addAll(succeededChallenges);

        editedSharedPreferences.putStringSet("CHALLENGE" + email, set);
        editedSharedPreferences.commit();
    }
}
