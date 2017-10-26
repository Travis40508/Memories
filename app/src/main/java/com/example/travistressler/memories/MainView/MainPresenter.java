package com.example.travistressler.memories.MainView;

/**
 * Created by travistressler on 10/25/17.
 */

public class MainPresenter {
    private MainView view;

    public void attachView(MainView view) {
        this.view = view;
        if(view != null) {
            view.checkForLocationPermissions();
            view.populateListOfFragments();
        }
    }

    public void backPressed(boolean isGridFragmentVisible, boolean isMapFragmentVisible) {
        if(isGridFragmentVisible) {
            view.removeFragmentFromGrid();
        } else if (isMapFragmentVisible) {
            view.removeFragmentFromMap();
        } else {
            view.superBackPressed();
        }
    }
}
