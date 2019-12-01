/*
 * 1/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.parkingserviceweb.models;

public class ParkedVehicles {

    private int slotNo;
    private String vehicleRegNumber;
    private String color;

    public ParkedVehicles() {
    }

    public ParkedVehicles(int slotNo, String vehicleRegNumber, String color) {
        this.slotNo = slotNo;
        this.vehicleRegNumber = vehicleRegNumber;
        this.color = color;
    }

    public int getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(int slotNo) {
        this.slotNo = slotNo;
    }

    public String getVehicleRegNumber() {
        return vehicleRegNumber;
    }

    public void setVehicleRegNumber(String vehicleRegNumber) {
        this.vehicleRegNumber = vehicleRegNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
