package models;

public class StaffMember {
      private int staffId;
      private String name;
      private String email;
      private Equipment[] assignedEquipment;
      
      public StaffMember(int staffId, String name, String email) {
    	this.staffId = staffId;
    	this.name = name;
    	this.email = email;
    	this.assignedEquipment = new Equipment[5]; 
    	
      }
      
      public int getStaffId() {
    	  return staffId;
      }
      
      public void setStaffId(int staffId) {
    	  this.staffId = staffId;
      }

      
      public String getName()  {
    	  return name;
      }
      
      public void setName(String name) {
    	  this.name = name;
      }
      
      public String getEmail() {
    	  return email;
      }
      
      public void setEmail(String email) {
    	  this.email = email;
      }
      
      public Equipment[] getAssignedEquipment() {
    	  return assignedEquipment;
      }
      
      public boolean addAssignedEquipment(Equipment equipment) {
    	  for (int i = 0; i < assignedEquipment.length; i++) {
    		  if (assignedEquipment[i] == null) {
    			  assignedEquipment[i] = equipment;
    			  return true;
    		  }
    	  }
    	  return false;
    	  }
      
      public boolean removeAssignedEquipment(String assetId) {
    	  for (int i = 0; i < assignedEquipment.length; i++) {
    		  if (assignedEquipment[i] != null &&
    		      assignedEquipment[i].getAssetId().equals(assetId)) {
    			  
    			  assignedEquipment[i] = null;
    			  return true;
    			  
    		  }
    	  }
    	  
    	  return false;
      }
      
      public int getAssignedEquipmentCount() {
    	  int count = 0;
    	  for (Equipment eq : assignedEquipment) {
    		  if (eq != null) {
    			  count++;
    		  }
    	  }
    	  return count;
      }
}     
  
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      