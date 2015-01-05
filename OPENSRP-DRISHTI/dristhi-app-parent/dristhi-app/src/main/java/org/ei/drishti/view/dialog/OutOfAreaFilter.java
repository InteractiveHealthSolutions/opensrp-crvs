package org.ei.drishti.view.dialog;

import static org.ei.drishti.view.contract.ECClient.OUT_OF_AREA;

import org.ei.drishti.Context;
import org.ei.drishti.R;
import org.ei.drishti.view.contract.SmartRegisterClient;

public class OutOfAreaFilter implements FilterOption {
    @Override
    public String name() {
        return Context.getInstance().getStringResource(R.string.filter_by_out_of_area_label);
    }

    @Override
    public boolean filter(SmartRegisterClient client) {
        return OUT_OF_AREA.equalsIgnoreCase(client.locationStatus());
    }
}
