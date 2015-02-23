package org.ei.drishti.view.activity;

import org.ei.drishti.view.controller.CRVSSmartRegisterController;

public class CRVSSmartRegisterActivity extends SmartRegisterActivity {
    @Override
    protected void onSmartRegisterInitialization() {
        webView.addJavascriptInterface(new CRVSSmartRegisterController(context.serviceProvidedService(), context.alertService(),
                context.formDataRepository(), context.listCache()), "context");
        webView.loadUrl("file:///android_asset/www/smart_registry/crvs_register.html");
    }
}
