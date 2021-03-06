package org.ei.drishti.view.controller;

import java.util.ArrayList;
import java.util.List;

import org.ei.drishti.Context;

import android.webkit.WebView;

public class UpdateController {
    private WebView webView;
    private boolean pageHasFinishedLoading = false;
    private List<String> urlsToLoad;

    public UpdateController(WebView webView) {
        this.webView = webView;
        urlsToLoad = new ArrayList<String>();
    }

    public void pageHasFinishedLoading() {
        pageHasFinishedLoading = true;
        flushUrlLoads();
    }

    private void flushUrlLoads() {
        if (pageHasFinishedLoading) {
            for (String url : urlsToLoad) {
                if (webView != null)
                    webView.loadUrl(url);
            }
        }
    }

    public void destroy() {
        webView = null;
    }

    public void updateANMDetails() {
        UpdateANMDetailsTask task = new UpdateANMDetailsTask(Context.getInstance().anmController());
        task.fetch(new AfterANMDetailsFetchListener() {
            @Override
            public void afterFetch(String anmDetails) {
                if (webView != null)
                    webView.loadUrl("javascript:pageView.updateANMDetails('" + anmDetails + "')");
            }
        });
    }
}
