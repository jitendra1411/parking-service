/*
 * 1/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.parkingserviceweb.services;

import com.project.parkingserviceweb.constant.Messages;
import com.project.parkingserviceweb.constant.ParkingCommands;
import com.project.parkingserviceweb.models.ParkedVehicles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class ParkingService {

    private static ParkingService INSTANCE;
    private static final Logger LOG = LoggerFactory.getLogger(ParkingService.class);

    public ParkingService(){
        INSTANCE = this;
    }

    public ParkingService getInstance(){
        return INSTANCE;
    }
    public void exicuteQuery(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        List<String> commands = new ArrayList<>();
        String st;
        while ((st = br.readLine()) != null) {
            commands.add(st);
        }

        if(commands.size() == 0){
            LOG.info(Messages.ErrorMessage.NO_COMMAND_FOUND);
            return;
        }
        exicuteCommand(commands);
    }

    public void exicuteCommand(List<String> commands){
        Integer slotCount = null;
        List<Integer> availableSlots = new ArrayList<>();
        Map<String, ParkedVehicles> parkedVehiclesByCabReg = new HashMap<>();
        Map<Integer, ParkedVehicles> parkedVehiclesBySlotNo = new HashMap<>();
        Map<String, List<ParkedVehicles>> parkedVehiclesByColor = new HashMap<>();
        for(String commandLine: commands){
            String[] commandElements = commandLine.split(" ");
            String command = commandElements[0];
            if(slotCount == null){
                if(command.equals(ParkingCommands.Commands.CREATE_PARKING_LOT)){
                    slotCount = Integer.parseInt(commandElements[1]);
                    for(int  i = 1; i <= slotCount; i++){
                        availableSlots.add(i);
                    }
                    System.out.println(String.format(Messages.SuccessMessage.PARKING_LOT_CREATED, slotCount));
                }
                else{
                    LOG.info(Messages.ErrorMessage.INVALID_FIRST_COMMAND);
                    return;
                }
            }
            else{
                try{
                    String vehicleReg;
                    String color;
                    ParkedVehicles parkedVehicles;
                    List<ParkedVehicles> parkedVehiclesList;
                    switch (command){
                        case ParkingCommands.Commands.CREATE_PARKING_LOT:
                            int newSlotCount = Integer.parseInt(commandElements[1]);
                            for(int  i = slotCount + 1; i <= newSlotCount; i++){
                                availableSlots.add(i);
                            }
                            slotCount += newSlotCount;
                            System.out.println(String.format(Messages.SuccessMessage.PARKING_LOT_CREATED, newSlotCount));
                            break;
                        case ParkingCommands.Commands.LEAVE:
                            int freeSlotNo = Integer.parseInt(commandElements[1]);
                            parkedVehicles = parkedVehiclesBySlotNo.get(freeSlotNo);
                            availableSlots.add(freeSlotNo);
                            Collections.sort(availableSlots);

                            parkedVehiclesBySlotNo.remove(parkedVehicles.getSlotNo());
                            parkedVehiclesByColor.get(parkedVehicles.getColor()).remove(parkedVehicles);
                            parkedVehiclesByCabReg.remove(parkedVehicles.getVehicleRegNumber());

//                            LOG.info(String.format(Messages.SuccessMessage.FREE_SLOT, freeSlotNo));
                            System.out.println(String.format(Messages.SuccessMessage.FREE_SLOT, freeSlotNo));
                            break;
                        case ParkingCommands.Commands.PARK:
                            if(availableSlots.size() == 0){
//                                LOG.info(Messages.ErrorMessage.PARKING_LOT_FULL);
                                System.out.println(Messages.ErrorMessage.PARKING_LOT_FULL);
                            }
                            else {
                                vehicleReg = commandElements[1];
                                color = commandElements[2];
                                int slotNo = availableSlots.get(0);
                                parkedVehicles = new ParkedVehicles(slotNo, vehicleReg, color);
                                parkedVehiclesByCabReg.put(vehicleReg, parkedVehicles);
                                parkedVehiclesBySlotNo.put(slotNo, parkedVehicles);
                                if(parkedVehiclesByColor.containsKey(color)){
                                    parkedVehiclesByColor.get(color).add(parkedVehicles);
                                }
                                else{
                                    parkedVehiclesList = new ArrayList<>();
                                    parkedVehiclesList.add(parkedVehicles);
                                    parkedVehiclesByColor.put(color, parkedVehiclesList);
                                }

                                availableSlots.remove(0);
//                                LOG.info(String.format(Messages.SuccessMessage.SLOT_ALLOCATED, slotNo));
                                System.out.println(String.format(Messages.SuccessMessage.SLOT_ALLOCATED, slotNo));
                            }
                            break;
                        case ParkingCommands.Commands.REGISTRATIONS_NUMBERS_FOR_CAB_WITH_COLOR:
                            color = commandElements[1];
                            parkedVehiclesList = parkedVehiclesByColor.get(color);
                            List<String> vehicleRegList = new ArrayList<>();
                            parkedVehiclesList.forEach(parkedVehicles1 -> {
                                vehicleRegList.add(parkedVehicles1.getVehicleRegNumber());
                            });
                            System.out.println(String.join(", ", vehicleRegList));
                            break;
                        case ParkingCommands.Commands.SLOT_NUMBERS_FOR_CAB_WITH_COLOR:
                            color = commandElements[1];
                            parkedVehiclesList = parkedVehiclesByColor.get(color);
                            List<String> slots = new ArrayList<>();
                            parkedVehiclesList.forEach(parkedVehicles1 -> {
                                slots.add(String.valueOf(parkedVehicles1.getSlotNo()));
                            });
                            System.out.println(String.join(", ", slots));
                            break;
                        case ParkingCommands.Commands.SLOT_NUMBER_OF_REGISTRATION_NUMBER:
                            vehicleReg = commandElements[1];
                            System.out.println(parkedVehiclesByCabReg.containsKey(vehicleReg)?
                                    parkedVehiclesByCabReg.get(vehicleReg).getSlotNo(): Messages.ErrorMessage.VEHICLE_NOT_FOUND);
                            break;
                        case ParkingCommands.Commands.STATUS:
                            if(parkedVehiclesByCabReg.size() == 0){
                                LOG.info(Messages.Message.CAB_NOT_ALLOCATED);
                            }
                            else{
                                System.out.println("Slot No.        Registration No           Colour");
                                List<Integer> allocatedSlots = new ArrayList<>(parkedVehiclesBySlotNo.keySet());
                                Collections.sort(allocatedSlots);
                                for(Integer slotNo: allocatedSlots){
                                    parkedVehicles = parkedVehiclesBySlotNo.get(slotNo);
                                    System.out.println("    " + parkedVehicles.getSlotNo() + "           "
                                            + parkedVehicles.getVehicleRegNumber() + "             " + parkedVehicles.getColor());
                                }
                            }
                            break;
                        default:
                            LOG.info(String.format(Messages.ErrorMessage.INVALID_COMMAND, commandLine));

                    }
                }
                catch (Exception e){
                    LOG.info(String.format(Messages.ErrorMessage.INVALID_COMMAND, commandLine));
                }

            }
        }
    }
}
