package com.tools.factories;

import com.tools.entities.Location;

import net.bytebuddy.utility.RandomString;

public class LocationFactory {
    public static Location getLocationInstance() {
        Location location = new Location();
        location.setName("Location " + RandomString.make(10));
        return location;
    }
}
