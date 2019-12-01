/*
 * 1/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.parkingserviceweb.constant;

public class ParkingCommands {
    public interface Commands{
        String CREATE_PARKING_LOT = "create_parking_lot",
            PARK = "park",
            LEAVE = "leave",
            STATUS = "status",
            REGISTRATIONS_NUMBERS_FOR_CAB_WITH_COLOR = "registration_numbers_for_cars_with_colour",
            SLOT_NUMBERS_FOR_CAB_WITH_COLOR = "slot_numbers_for_cars_with_colour",
            SLOT_NUMBER_OF_REGISTRATION_NUMBER = "slot_number_for_registration_number";
    }
}
