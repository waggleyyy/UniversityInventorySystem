package managers;

import models.Equipment;
import models.StaffMember;

public class InventoryReports {

    private Equipment[] inventory;
    private StaffMember[] staffMembers;

    public InventoryReports(Equipment[] inventory, StaffMember[] staffMembers) {
        this.inventory = inventory;
        this.staffMembers = staffMembers;
    }

    
    public void generateInventoryReport() {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null) {
                System.out.println(inventory[i]);
            }
        }
    }

    
    public void findExpiredWarranties() {
        int i = 0;
        while (i < inventory.length) {
            if (inventory[i] != null && inventory[i].getWarrantyMonths() == 0) {
                System.out.println(inventory[i]);
            }
            i++;
        }
    }

    
    public void displayAssignmentsByDepartment() {
        for (StaffMember staff : staffMembers) {
            if (staff != null) {
                System.out.println("Staff: " + staff.getName());
                for (Equipment eq : staff.getAssignedEquipment()) {
                    if (eq != null) {
                        System.out.println(" - " + eq.getCategory());
                    }
                }
            }
        }
    }

    
    public void calculateUtilisationRate() {
        int total = 0;
        int inUse = 0;

        for (Equipment eq : inventory) {
            if (eq != null) {
                total++;
                if (!eq.isAvailable()) {
                    inUse++;
                }
            }
        }

        if (total > 0) {
            double rate = (double) inUse / total * 100;
            System.out.println("Utilisation rate: " + rate + "%");
        }
    }

    
    public void generateMaintenanceSchedule() {
        int i = 0;
        if (inventory.length == 0) return;

        do {
            if (inventory[i] != null) {
                System.out.println("Maintenance check for: " + inventory[i].getName());
            }
            i++;
        } while (i < inventory.length);
    }
}
