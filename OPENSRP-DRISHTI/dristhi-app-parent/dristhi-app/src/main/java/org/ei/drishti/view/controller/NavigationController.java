package org.ei.drishti.view.controller;

import static org.ei.drishti.view.controller.ProfileNavigationController.navigateToANCProfile;
import static org.ei.drishti.view.controller.ProfileNavigationController.navigateToChildProfile;
import static org.ei.drishti.view.controller.ProfileNavigationController.navigateToECProfile;
import static org.ei.drishti.view.controller.ProfileNavigationController.navigateToPNCProfile;

import org.ei.drishti.view.activity.ANCSmartRegisterActivity;
import org.ei.drishti.view.activity.ChildSmartRegisterActivity;
import org.ei.drishti.view.activity.FPSmartRegisterActivity;
import org.ei.drishti.view.activity.NativeECSmartRegisterActivity;
import org.ei.drishti.view.activity.PNCSmartRegisterActivity;
import org.ei.drishti.view.activity.ReportsActivity;
import org.ei.drishti.view.activity.VideosActivity;

import android.app.Activity;
import android.content.Intent;

public class NavigationController {
    private Activity activity;
    private ANMController anmController;

    public NavigationController(Activity activity, ANMController anmController) {
        this.activity = activity;
        this.anmController = anmController;
    }

    public void startReports() {
        activity.startActivity(new Intent(activity, ReportsActivity.class));
    }

    public void startVideos() {
        activity.startActivity(new Intent(activity, VideosActivity.class));
    }

    public void startECSmartRegistry() {
        activity.startActivity(new Intent(activity, NativeECSmartRegisterActivity.class));
    }

    public void startFPSmartRegistry() {
        activity.startActivity(new Intent(activity, FPSmartRegisterActivity.class));
    }

    public void startANCSmartRegistry() {
        activity.startActivity(new Intent(activity, ANCSmartRegisterActivity.class));
    }

    public void startPNCSmartRegistry() {
        activity.startActivity(new Intent(activity, PNCSmartRegisterActivity.class));
    }

    public void startChildSmartRegistry() {
        activity.startActivity(new Intent(activity, ChildSmartRegisterActivity.class));
    }
    
    public void startCRVSSmartRegistry() {
        activity.startActivity(new Intent(activity, ChildSmartRegisterActivity.class));
    }

    public String get() {
        return anmController.get();
    }

    public void goBack() {
        activity.finish();
    }

    public void startEC(String entityId) {
        navigateToECProfile(activity, entityId);
    }

    public void startANC(String entityId) {
        navigateToANCProfile(activity, entityId);
    }

    public void startPNC(String entityId) {
        navigateToPNCProfile(activity, entityId);
    }

    public void startChild(String entityId) {
        navigateToChildProfile(activity, entityId);
    }
}
