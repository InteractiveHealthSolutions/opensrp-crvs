package org.ei.drishti.view.activity;

import static org.ei.drishti.AllConstants.CATEGORY_DESCRIPTION;
import static org.ei.drishti.AllConstants.INDICATOR_DETAIL;

import org.ei.drishti.domain.Report;
import org.ei.drishti.view.controller.ReportIndicatorDetailViewController;

import android.os.Bundle;

public class ReportIndicatorDetailActivity extends SecuredWebActivity {

    @Override
    protected void onInitialization() {
        Bundle extras = getIntent().getExtras();
        Report indicatorDetails = (Report) extras.get(INDICATOR_DETAIL);
        String categoryDescription = extras.getString(CATEGORY_DESCRIPTION);

        webView.addJavascriptInterface(new ReportIndicatorDetailViewController(this, indicatorDetails, categoryDescription), "context");
        webView.loadUrl("file:///android_asset/www/report_indicator_detail.html");
    }
}
