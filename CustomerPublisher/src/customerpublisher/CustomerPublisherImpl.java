package customerpublisher;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import lk.sliit.sa.osgi.persistence.CustomerFactoryImpl;
import lk.sliit.sa.osgi.persistence.PersistenceServiceImpl;
import lk.sliit.sa.osgi.persistence.RoomFactoryImpl;
import lk.sliit.sa.osgi.persistence.service.Customer;
import lk.sliit.sa.osgi.persistence.service.Factory;
import lk.sliit.sa.osgi.persistence.service.Room;

public class CustomerPublisherImpl implements CustomerPublish{

	@Override
	public boolean addCustomer(int id, String fname, String lname, String nic, String username, String password,String contact) {
		boolean Success = false;
		Customer cusotmer = new Customer();
		cusotmer.setId(id);
		cusotmer.setFname(fname);
		cusotmer.setLname(lname);
		cusotmer.setNic(nic);
		cusotmer.setUsername(username);
		cusotmer.setPassword(password);
		cusotmer.setContact(contact);
		try {
			
			System.out.println("Adding Cusotmer");
			PersistenceServiceImpl impl = new PersistenceServiceImpl();
			CustomerFactoryImpl fc = (CustomerFactoryImpl) impl.getFactory(Factory.CUSTOMERS);
			fc.add(cusotmer);
			Success = true;
			
		} catch (Exception e) {
			System.out.println("Error Occured");
			e.printStackTrace();
		}
		return Success;
	}

	@Override
	public boolean updateCustomer(int id, String fname, String lname, String nic, String username, String password,
			String contact) {
		boolean Success = false;
		Customer cusotmer = new Customer();
		cusotmer.setId(id);
		cusotmer.setFname(fname);
		cusotmer.setLname(lname);
		cusotmer.setNic(nic);
		cusotmer.setUsername(username);
		cusotmer.setPassword(password);
		cusotmer.setContact(contact);
		try {
			
			System.out.println(" Cusotmer ");
			PersistenceServiceImpl impl = new PersistenceServiceImpl();
			CustomerFactoryImpl fc = (CustomerFactoryImpl) impl.getFactory(Factory.CUSTOMERS);
			fc.update(cusotmer); 
			
			/*
			 * Customer cus = new Customer(); cus.addRoom(room);
			 */
			Success = true;
			
		} catch (Exception e) {
			System.out.println("Error Occured");
			e.printStackTrace();
		}
		return Success;
	}

	@Override
	public boolean deleteCustomer(int id) {
		boolean success = false;
		try {
			System.out.println("Updating Cusotmer Details");
			PersistenceServiceImpl imp = new PersistenceServiceImpl();
			CustomerFactoryImpl customer = (CustomerFactoryImpl) imp.getFactory(Factory.CUSTOMERS);
			customer.deleteById(id);
			success = true;
			
		} catch (Exception e) {
			System.out.println("Error Occured");
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public void searchCustomerById(int id) {
		try {
			PersistenceServiceImpl imp = new PersistenceServiceImpl();
			CustomerFactoryImpl customer = (CustomerFactoryImpl) imp.getFactory(Factory.CUSTOMERS);
			Optional<Customer> customerOptional = customer.findById(id);
			
			if(customerOptional.isPresent()){
				Customer cus = customerOptional.get();
				System.out.println("**** Details of Cusotmer Id "+ id +" ****");
			    System.out.println("Cusotmer ID:     "+cus.getId());
				System.out.println("Customer First Name:   "+cus.getFname());
			    System.out.println("Cusotmer Last Name : "+cus.getLname());
			    System.out.println("Customer Nic:     "+cus.getNic());
				System.out.println("Cusotmer Username:   "+cus.getUsername());
			    System.out.println("Cusotmer Password: "+cus.getPassword());
			    System.out.println("Customer Contact: "+cus.getContact());
			    System.out.println("******************************************");
			}
			else {
			    System.out.println("No Customer Details were found to the entered Customer ID");
			}
		} catch (Exception e) {
			System.out.println("Error Occured");
			e.printStackTrace();
		}	
		
		
	}

	@Override
	public void searchCustomerByAny(String field, String value) {
		try {
			PersistenceServiceImpl imp = new PersistenceServiceImpl();
			CustomerFactoryImpl customer = (CustomerFactoryImpl) imp.getFactory(Factory.CUSTOMERS);
			Optional<List<Customer>> customerOptional = customer.findAll();
			
			if(customerOptional.isPresent()) {
				System.out.println("******************Customer Details***************");
				List<Customer> cus = customerOptional.get();
				Iterator<Customer> cusIterator = cus.iterator();
				while(cusIterator.hasNext()) {
					Customer cust = cusIterator.next();
				    System.out.println("Cusotmer ID:     "+cust.getId());
					System.out.println("Customer First Name:   "+cust.getFname());
				    System.out.println("Cusotmer Last Name : "+cust.getLname());
				    System.out.println("Customer Nic:     "+cust.getNic());
					System.out.println("Cusotmer Username:   "+cust.getUsername());
				    System.out.println("Cusotmer Password: "+cust.getPassword());
				    System.out.println("Customer Contact: "+cust.getContact());
				    System.out.println("******************************************");
				}
			}
			else {
			    System.out.println("No data found");
			}
			
		} catch (Exception e) {
			System.out.println("Error Occured");
			e.printStackTrace();
		}
		
	}

	@Override
	public void checkAvailability(String field, Object value) {
		
		try {
		PersistenceServiceImpl imp = new PersistenceServiceImpl();
		RoomFactoryImpl cusroom= (RoomFactoryImpl) imp.getFactory(Factory.ROOMS);
		Optional<List<Room>> customer_room = cusroom.findBy("isbooked" , true);
		
		if(customer_room .isPresent()) {
			System.out.println("******************Room Details***************");
			List<Room> room = customer_room .get();
			Iterator<Room> roomIterator = room.iterator();
			while(roomIterator.hasNext()) {
				Room rm = roomIterator.next();
				  System.out.println("Room ID:     "+rm.getId());
				    System.out.println("Room Name:   "+rm.getTitle());
				    System.out.println("Room Type:  "+ rm.getType());
				    System.out.println("Room Status: "+rm.getStatus());
				    System.out.println("Room Avaliability : "+ rm.isBooked());
				    System.out.println("***************************************");
			}
		}
		else {
		    System.out.println("NO data for searched parameters");
		}
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
		

	@Override
	public void DisplayRoomDetails() {
		

		try {
		PersistenceServiceImpl imp = new PersistenceServiceImpl();
		RoomFactoryImpl cusroom= (RoomFactoryImpl) imp.getFactory(Factory.ROOMS);
		Room rmRoom = new Room();
		Optional<List<Room>> customer_room = cusroom.findAll();
		
		if(customer_room .isPresent()) {
			System.out.println("******************Room Details***************");
			List<Room> room = customer_room .get();
			Iterator<Room> roomIterator = room.iterator();
			while(roomIterator.hasNext()) {
				Room rm = roomIterator.next();
				  System.out.println("Room ID:     "+rm.getId());
				    System.out.println("Room Name:   "+rm.getTitle());
				    System.out.println("Room Status: "+rm.getStatus());
				    System.out.println("Room Type:  "+ rm.getType());
				    System.out.println("Room Avaliability : "+ rm.isBooked());
				    System.out.println("Room CheckIn:  "+ rm.getCheckIn());
				    System.out.println("Room CheckOut:  "+ rm.getCheckout());
				    System.out.println("***************************************");
			}
		}
		else {
		    System.out.println("NO data for searched parameters");
		}
		
	
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	}

	@Override
	public boolean addingRoom(int cid, int rid) {
		boolean success = false;
		Room rm = null; 
		
		try {
			PersistenceServiceImpl imp = new PersistenceServiceImpl();
			CustomerFactoryImpl customer;
			customer = (CustomerFactoryImpl) imp.getFactory(Factory.CUSTOMERS);
			Optional<Customer> customerOptional = customer.findById(cid); 
			
			PersistenceServiceImpl imp1= new PersistenceServiceImpl();
			RoomFactoryImpl room= (RoomFactoryImpl) imp1.getFactory(Factory.ROOMS);
			Optional<Room> roomDet= room.findById(rid);
			
			
			if(customerOptional.isPresent()){
				Customer cus = customerOptional.get();
				System.out.println("**** Details of Cusotmer Id "+ cid +" ****");
			    System.out.println("Cusotmer ID:     "+cus.getId());
				System.out.println("Customer First Name:   "+cus.getFname());
			    System.out.println("Cusotmer Last Name : "+cus.getLname());
			    System.out.println("Customer Nic:     "+cus.getNic());
				System.out.println("Cusotmer Username:   "+cus.getUsername());
			    System.out.println("Cusotmer Password: "+cus.getPassword());
			    System.out.println("Customer Contact: "+cus.getContact());
			    System.out.println("******************************************");
			}
			else {
			    System.out.println("No Customer Details were found to the entered Customer ID");
			
			}
			
			if(roomDet.isPresent()){
				rm = roomDet.get();
				System.out.println("**** Details of Room Id "+ rid+" ****");
			    System.out.println("Room ID:     "+rm.getId());
			    System.out.println("Room Name:   "+rm.getTitle());
			    System.out.println("Room Status: "+rm.getStatus());
			    System.out.println("*****************");
			}
			else {
			    System.out.println("No Room Details were found to the entered Room ID");
			}
			    
			Customer customer2 = new Customer();
			customer2.addRoom(rm);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		 
		return success;
	}

//	@Override
/*	public boolean roomBooked(int id, String title, String status, Boolean isbooked) {
			checkAvailability("isbooked", false);
			boolean success =false;
			Room room = new Room();
			room.setId(id);
			room.setStatus(status);
			room.setTitle(title);
			room.isBooked();
			try {
				System.out.println("Booking a Room ");
				PersistenceServiceImpl imp = new PersistenceServiceImpl();
				RoomFactoryImpl factory= (RoomFactoryImpl) imp.getFactory(Factory.ROOMS);
				factory.update(room);
				success=true;
			} catch (ClassNotFoundException e) {
				success=false;
				e.printStackTrace();
				System.out.println("Something went wrong");
			} catch (SQLException e) {
				System.out.println("Something went wrong");
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("Something went wrong");
				e.printStackTrace();
			}		
		return success;	
			
	}*/

	

}
