package com.example.android.moviesapp.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

@SuppressWarnings("ALL")
public class SettingsActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener {

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        bindPrefrenceSummaryToValue(findPreference("sortby"));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object object) {
        String stringValue = object.toString();
        if(preference instanceof ListPreference){
            ListPreference listPreference = (ListPreference) preference;
            int prefindex = listPreference.findIndexOfValue(stringValue);

            if (prefindex >= 0){
                preference.setSummary(listPreference.getEntries()[prefindex]);
            }
        }
        else {
            preference.setSummary(stringValue);
        }
        return true;
    }
    private void bindPrefrenceSummaryToValue(Preference preference) {
        preference.setOnPreferenceChangeListener(this);

        onPreferenceChange(preference, PreferenceManager
                .getDefaultSharedPreferences(preference.getContext())
                .getString(preference.getKey(),""));
    }
}