package org.ei.drishti.view.controller;

import static java.lang.String.valueOf;
import static java.util.Collections.sort;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.ei.drishti.domain.ServiceProvided.CHILD_ILLNESS_SERVICE_PROVIDED_NAME;
import static org.ei.drishti.domain.ServiceProvided.PNC_SERVICE_PROVIDED_NAME;
import static org.ei.drishti.domain.ServiceProvided.VITAMIN_A_SERVICE_PROVIDED_NAME;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.ei.drishti.AllConstants;
import org.ei.drishti.domain.Alert;
import org.ei.drishti.domain.Child;
import org.ei.drishti.domain.ServiceProvided;
import org.ei.drishti.repository.AllBeneficiaries;
import org.ei.drishti.service.AlertService;
import org.ei.drishti.service.ServiceProvidedService;
import org.ei.drishti.util.Cache;
import org.ei.drishti.util.CacheableData;
import org.ei.drishti.view.contract.AlertDTO;
import org.ei.drishti.view.contract.ChildClient;
import org.ei.drishti.view.contract.ServiceProvidedDTO;

import com.google.gson.Gson;

public class ChildSmartRegisterController {
    private static final String CHILD_CLIENTS_LIST_CACHE_ENTRY_NAME = "ChildClientList";
    private final ServiceProvidedService serviceProvidedService;
    private final AlertService alertService;
    private final AllBeneficiaries allBeneficiaries;
    private final Cache<String> cache;

    public ChildSmartRegisterController(ServiceProvidedService serviceProvidedService, AlertService alertService,
                                        AllBeneficiaries allBeneficiaries, Cache<String> cache) {
        this.serviceProvidedService = serviceProvidedService;
        this.alertService = alertService;
        this.allBeneficiaries = allBeneficiaries;
        this.cache = cache;
    }

    public String get() {
        return cache.get(CHILD_CLIENTS_LIST_CACHE_ENTRY_NAME, new CacheableData<String>() {
            @Override
            public String fetch() {
                List<Child> children = allBeneficiaries.allChildrenWithMotherAndEC();
                List<ChildClient> childrenClient = new ArrayList<ChildClient>();

                for (Child child : children) {
                    String photoPath = isBlank(child.photoPath()) ? (AllConstants.FEMALE_GENDER.equalsIgnoreCase(child.gender()) ? AllConstants.DEFAULT_GIRL_INFANT_IMAGE_PLACEHOLDER_PATH : AllConstants.DEFAULT_BOY_INFANT_IMAGE_PLACEHOLDER_PATH) : child.photoPath();
                    List<AlertDTO> alerts = getAlerts(child.caseId());
                    List<ServiceProvidedDTO> servicesProvided = getServicesProvided(child.caseId());
                    ChildClient childClient =
                            new ChildClient(
                                    child.caseId(),
                                    child.gender(),
                                    child.getDetail(AllConstants.ChildRegistrationFields.WEIGHT),
                                    child.mother().thayiCardNumber())
                                    .withName(child.getDetail(AllConstants.ChildRegistrationFields.NAME))
                                    .withEntityIdToSavePhoto(child.caseId())
                                    .withMotherName(child.ec().wifeName())
                                    .withDOB(child.dateOfBirth())
                                    .withMotherAge(child.ec().age())
                                    .withFatherName(child.ec().husbandName())
                                    .withVillage(child.ec().village())
                                    .withOutOfArea(child.ec().isOutOfArea())
                                    .withEconomicStatus(child.ec().getDetail(AllConstants.ECRegistrationFields.ECONOMIC_STATUS))
                                    .withCaste(child.ec().getDetail(AllConstants.ECRegistrationFields.CASTE))
                                    .withIsHighRisk(child.isHighRisk())
                                    .withPhotoPath(photoPath)
                                    .withECNumber(child.ec().ecNumber())
                                    .withAlerts(alerts)
                                    .withServicesProvided(servicesProvided);

                    childrenClient.add(childClient);
                }
                sortByName(childrenClient);
                return new Gson().toJson(childrenClient);
            }
        });
    }

    private List<ServiceProvidedDTO> getServicesProvided(String entityId) {
        List<ServiceProvided> servicesProvided = serviceProvidedService.findByEntityIdAndServiceNames(entityId,
                ArrayUtils.addAll(AllConstants.Immunizations.ALL, VITAMIN_A_SERVICE_PROVIDED_NAME,
                        CHILD_ILLNESS_SERVICE_PROVIDED_NAME, PNC_SERVICE_PROVIDED_NAME));
        List<ServiceProvidedDTO> serviceProvidedDTOs = new ArrayList<ServiceProvidedDTO>();
        for (ServiceProvided serviceProvided : servicesProvided) {
            serviceProvidedDTOs.add(new ServiceProvidedDTO(serviceProvided.name(), serviceProvided.date(), serviceProvided.data()));
        }
        return serviceProvidedDTOs;
    }

    private List<AlertDTO> getAlerts(String entityId) {
        List<Alert> alerts = alertService.findByEntityIdAndAlertNames(entityId, AllConstants.Immunizations.ALL);
        List<AlertDTO> alertDTOs = new ArrayList<AlertDTO>();
        for (Alert alert : alerts) {
            alertDTOs.add(new AlertDTO(alert.visitCode(), valueOf(alert.status()), alert.startDate()));
        }
        return alertDTOs;
    }

    private void sortByName(List<ChildClient> childrenClient) {
        sort(childrenClient, new Comparator<ChildClient>() {
            @Override
            public int compare(ChildClient oneChild, ChildClient anotherChild) {
                return oneChild.motherName().compareToIgnoreCase(anotherChild.motherName());
            }
        });
    }
}
