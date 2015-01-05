package org.ei.drishti.provider;

import org.ei.drishti.view.contract.SmartRegisterClient;
import org.ei.drishti.view.contract.SmartRegisterClients;
import org.ei.drishti.view.dialog.FilterOption;
import org.ei.drishti.view.dialog.ServiceModeOption;
import org.ei.drishti.view.dialog.SortOption;

import android.view.View;
import android.view.ViewGroup;

public interface SmartRegisterClientsProvider {

    public View getView(SmartRegisterClient client, View parentView, ViewGroup viewGroup);

    public SmartRegisterClients getClients();

    SmartRegisterClients updateClients(FilterOption villageFilter, ServiceModeOption serviceModeOption,
                                       FilterOption searchFilter, SortOption sortOption);
}
