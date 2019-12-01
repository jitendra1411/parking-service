/*
 * 1/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.parkingserviceweb.constant;

public class Messages {

    public interface ErrorMessage{
        String NO_COMMAND_FOUND = "No command found in file",
            INVALID_FIRST_COMMAND = "First command should be slot creation",
            INVALID_COMMAND = "Invalid command %s",
            PARKING_LOT_FULL = "Sorry, parking lot is full",
            VEHICLE_NOT_FOUND = "Not found";
    }

    public interface SuccessMessage{
        String FREE_SLOT = "Slot number %s is free",
            SLOT_ALLOCATED = "Allocated slot number: %s",
            PARKING_LOT_CREATED = "Created a parking lot with %s slots";
    }

    public interface Message{
        String CAB_NOT_ALLOCATED = "No one cab allocated";
    }
}
