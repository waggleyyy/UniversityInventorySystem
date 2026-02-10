package Main;

import java.util.Scanner;

import managers.InventoryManager;
import models.Equipment;
import models.StaffMember;
import exceptions.InventoryException;

public class UniversityInventorySystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        InventoryManager manager = new InventoryManager();

        Equipment[] equipmentList = new Equipment[10];
        StaffMember[] staffList = new StaffMember[10];

        int equipmentCount = 0;
        int staffCount = 0;

        boolean running = true;

        while (running) {
            System.out.println("\n=== UNIVERSITY INVENTORY SYSTEM ===");
            System.out.println("1. Add Equipment");
            System.out.println("2. Register Staff");
            System.out.println("3. Assign Equipment");
            System.out.println("4. Return Equipment");
            System.out.println("5. Exit");
            System.out.print("Option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); 

            try {
                switch (option) {

                    case 1:
                        System.out.print("Asset ID: ");
                        String assetId = scanner.nextLine();

                        System.out.print("Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Brand: ");
                        String brand = scanner.nextLine();

                        System.out.print("Category: ");
                        String category = scanner.nextLine();

                        System.out.print("Warranty months: ");
                        int warranty = scanner.nextInt();
                        scanner.nextLine();

                        Equipment equipment = new Equipment(
                                assetId,        
                                name,
                                true,           
                                assetId,        
                                brand,
                                warranty,
                                category
                        );

                        equipmentList[equipmentCount++] = equipment;
                        System.out.println("Equipment added.");
                        break;

                    case 2:
                        System.out.print("Staff ID: ");
                        int staffId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Name: ");
                        String staffName = scanner.nextLine();

                        System.out.print("Email: ");
                        String email = scanner.nextLine();

                        StaffMember staff = new StaffMember(staffId, staffName, email);
                        staffList[staffCount++] = staff;

                        System.out.println("Staff registered.");
                        break;

                    case 3:
                        System.out.print("Staff ID: ");
                        int sid = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Asset ID: ");
                        String aid = scanner.nextLine();

                        StaffMember selectedStaff = null;
                        Equipment selectedEquipment = null;

                        for (StaffMember s : staffList) {
                            if (s != null && s.getStaffId() == sid) {
                                selectedStaff = s;
                                break;
                            }
                        }

                        for (Equipment e : equipmentList) {
                            if (e != null && e.getAssetId().equals(aid)) {
                                selectedEquipment = e;
                                break;
                            }
                        }

                        if (selectedStaff == null || selectedEquipment == null) {
                            System.out.println("Staff or equipment not found.");
                        } else {
                            manager.assignEquipment(selectedStaff, selectedEquipment);
                            System.out.println("Equipment assigned.");
                        }
                        break;

                    case 4:
                        System.out.print("Staff ID: ");
                        int rsid = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Asset ID: ");
                        String raid = scanner.nextLine();

                        StaffMember returnStaff = null;

                        for (StaffMember s : staffList) {
                            if (s != null && s.getStaffId() == rsid) {
                                returnStaff = s;
                                break;
                            }
                        }

                        if (returnStaff == null) {
                            System.out.println("Staff not found.");
                        } else {
                            manager.returnEquipment(returnStaff, raid);
                            System.out.println("Equipment returned.");
                        }
                        break;

                    case 5:
                        running = false;
                        System.out.println("System closed.");
                        break;

                    default:
                        System.out.println("Invalid option.");
                }

            } catch (InventoryException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
