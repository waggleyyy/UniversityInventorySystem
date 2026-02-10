package managers;

import models.Equipment;
import models.StaffMember;

import exceptions.InventoryException;
import exceptions.AssignmentLimitExceededException;

public class InventoryManager {

    public InventoryManager() {
        
    }

    
    public void assignEquipment(StaffMember staff, Equipment equipment)
            throws InventoryException {

        if (!equipment.isAvailable()) {
            throw new InventoryException(
                    "Equipment is not available for assignment."
            );
        }

        if (staff.getAssignedEquipmentCount() >= 5) {
            throw new AssignmentLimitExceededException(
                    "Staff member already has the maximum number of assigned equipment."
            );
        }

        boolean added = staff.addAssignedEquipment(equipment);

        if (!added) {
            throw new AssignmentLimitExceededException(
                    "Unable to assign equipment. Assignment limit reached."
            );
        }

        equipment.setAvailable(false);
    }

    
    public void returnEquipment(StaffMember staff, String assetId)
            throws InventoryException {

        Equipment[] equipments = staff.getAssignedEquipment();
        Equipment returnedEquipment = null;

        for (Equipment eq : equipments) {
            if (eq != null && eq.getAssetId().equals(assetId)) {
                returnedEquipment = eq;
                break;
            }
        }

        if (returnedEquipment == null) {
            throw new InventoryException(
                    "Equipment with assetId " + assetId + " not found for this staff member."
            );
        }

        staff.removeAssignedEquipment(assetId);
        returnedEquipment.setAvailable(true);
    }

    
    public double calculateMaintenanceFee(Equipment equipment, int daysOverdue) {

        double dailyFee;

        switch (equipment.getCategory().toLowerCase()) {
            case "computer":
                dailyFee = 5.0;
                break;
            case "lab":
                dailyFee = 8.0;
                break;
            case "furniture":
                dailyFee = 2.0;
                break;
            default:
                dailyFee = 3.0;
        }

        return dailyFee * daysOverdue;
    }

    
    public Equipment searchEquipment(String name) {
        return null;
    }

    public Equipment searchEquipment(String category, boolean availableOnly) {
        return null;
    }

    public Equipment searchEquipment(int minWarranty, int maxWarranty) {
        return null;
    }

    
    public boolean validateAssignment(StaffMember staff, Equipment equipment) {

        if (staff != null) {
            if (equipment != null) {
                if (equipment.isAvailable()) {
                    if (staff.getAssignedEquipmentCount() < 5) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
