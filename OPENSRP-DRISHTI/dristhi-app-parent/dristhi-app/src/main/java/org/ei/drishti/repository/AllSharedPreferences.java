package org.ei.drishti.repository;

import static org.ei.drishti.AllConstants.DEFAULT_LOCALE;
import static org.ei.drishti.AllConstants.IS_SYNC_IN_PROGRESS_PREFERENCE_KEY;
import static org.ei.drishti.AllConstants.LANGUAGE_PREFERENCE_KEY;
import android.content.SharedPreferences;

public class AllSharedPreferences {
    public static final String ANM_IDENTIFIER_PREFERENCE_KEY = "anmIdentifier";

    private SharedPreferences preferences;

    public AllSharedPreferences(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void updateANMUserName(String userName) {
        preferences.edit().putString(ANM_IDENTIFIER_PREFERENCE_KEY, userName).commit();
    }

    public String fetchRegisteredANM() {
        return preferences.getString(ANM_IDENTIFIER_PREFERENCE_KEY, "").trim();
    }

    public String fetchLanguagePreference() {
        return preferences.getString(LANGUAGE_PREFERENCE_KEY, DEFAULT_LOCALE).trim();
    }

    public void saveLanguagePreference(String languagePreference) {
        preferences.edit().putString(LANGUAGE_PREFERENCE_KEY, languagePreference).commit();
    }

    public Boolean fetchIsSyncInProgress() {
        return preferences.getBoolean(IS_SYNC_IN_PROGRESS_PREFERENCE_KEY, false);
    }

    public void saveIsSyncInProgress(Boolean isSyncInProgress) {
        preferences.edit().putBoolean(IS_SYNC_IN_PROGRESS_PREFERENCE_KEY, isSyncInProgress).commit();
    }
}
