package org.ei.drishti.view.dialog;

import static org.ei.drishti.view.contract.SmartRegisterClient.EC_NUMBER_COMPARATOR;

import java.util.Collections;

import org.ei.drishti.Context;
import org.ei.drishti.R;
import org.ei.drishti.view.contract.SmartRegisterClients;

public class ECNumberSort implements SortOption {
    @Override
    public String name() {
        return Context.getInstance().getStringResource(R.string.sort_by_ec_number_label);
    }

    @Override
    public SmartRegisterClients sort(SmartRegisterClients allClients) {
        Collections.sort(allClients, EC_NUMBER_COMPARATOR);
        return allClients;
    }
}
