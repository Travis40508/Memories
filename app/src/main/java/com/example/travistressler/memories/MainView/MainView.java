package com.example.travistressler.memories.MainView;

/**
 * Created by travistressler on 10/25/17.
 */

public interface MainView {
    void checkForLocationPermissions();

    void populateListOfFragments();

    void removeFragmentFromGrid();

    void removeFragmentFromMap();

    void superBackPressed();
}
