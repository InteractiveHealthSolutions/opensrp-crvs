package org.ei.drishti.view.dialog;

import static org.ei.drishti.view.contract.SmartRegisterClient.SC_COMPARATOR;

import java.util.Collections;

import org.ei.drishti.Context;
import org.ei.drishti.crvs.pk.R;
import org.ei.drishti.view.contract.SmartRegisterClients;

public class SCSort implements SortOption {
    @Override
    public String name() {
        return Context.getInstance().getStringResource(R.string.sort_by_sc_label);
    }

    @Override
    public SmartRegisterClients sort(SmartRegisterClients allClients) {
        Collections.sort(allClients, SC_COMPARATOR);
        return allClients;
    }
}
