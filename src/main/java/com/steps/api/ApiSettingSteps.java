package com.steps.api;

import com.tools.constants.ApiUrlConstants;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.Alert;
import com.tools.entities.Location;
import com.tools.entities.LocationsCollection;
import com.tools.factories.AlertFactory;
import com.tools.factories.LocationFactory;
import com.tools.utils.InstanceUtils;
import com.tools.utils.SerenitySessionUtils;

import net.thucydides.core.annotations.Step;

import org.junit.Assert;

import java.util.List;

public class ApiSettingSteps extends AbstractApiSteps {
    private static final long serialVersionUID = 1L;

    @Step
    public void enableBookingRequestsNotifications() {
        Alert alertRequest = AlertFactory.getIndividualBookingRequestAlertInstance();
        updateResource(ApiUrlConstants.USER_SETTINGS, alertRequest);
    }

    @Step
    public void createLocation() {
        Location locationRequest = LocationFactory.getLocationInstance();
        Location locationResponse = createResource(ApiUrlConstants.LOCATIONS, locationRequest, Location.class);
        InstanceUtils.mergeObjects(locationRequest, locationResponse);
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.LOCATION, locationRequest);
        SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.LOCATIONS, locationRequest);
    }

    @Step
    public void deleteLocation() {
        Location locationRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.LOCATION);
        deleteResource(ApiUrlConstants.LOCATIONS, locationRequest.getId());
    }

    @Step
    public void checkThatLocationExists() {
        boolean isFound = false;
        Location locationRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.LOCATION);
        LocationsCollection[] locations = getResource(ApiUrlConstants.LOCATIONS + "?perPage=9999", LocationsCollection[].class);
        for (int i = 0; i < locations.length; i++) {
            if (locations[i].getId().intValue() == locationRequest.getId()) {
                if (locations[i].getName().contentEquals(locationRequest.getName())) {
                    isFound = true;
                    break;
                }
            }
        }
        Assert.assertTrue(String.format("Location %s was not found!", locationRequest.getName()), isFound);
    }

    @Step
    public void deleteAllLocations() {
        LocationsCollection[] locations = getResource(ApiUrlConstants.LOCATIONS + "?perPage=9999", LocationsCollection[].class);
        for (int i = 0; i < locations.length; i++) {
            if (locations[i].getAction() == null) {
                deleteResourceWithoutAssertion(ApiUrlConstants.LOCATIONS, locations[i].getId());
            }
        }
    }

    @Step
    public void deleteAllLocationsFromSession() {
        List<Location> locationsList = SerenitySessionUtils.getFromSession(SerenityKeyConstants.LOCATIONS);
        for (Location location : locationsList) {
            if (location.getAction() == null) {
                deleteResourceWithoutAssertion(ApiUrlConstants.LOCATIONS, location.getId());
            }
        }
    }
}
