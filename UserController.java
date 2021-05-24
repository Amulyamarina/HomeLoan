package com.example.homeloan.layer5;



import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.homeloan.layer2.Document;
import com.example.homeloan.layer2.Income;
import com.example.homeloan.layer2.UserRegistration;
import com.example.homeloan.layer4.UserRegistrationService;
import com.example.homeloan.layer4.exceptions.UserAlreadyExistException;
import com.example.homeloan.layer4.exceptions.UserNotFoundException;

@RestController
public class UserController {
	
	@Autowired
	UserRegistrationService userRegServ;
	
	@GetMapping(path="/getUser/{myuno}")
	@ResponseBody
	public ResponseEntity<UserRegistration> getUserRegistration(@PathVariable("myuno") Integer uno) throws UserNotFoundException {
		System.out.println("UserRegistration Controller....Understanding client and talking to service layer...");
		UserRegistration user=null;
		
			user = userRegServ.findUserService(uno);
			if(user==null)
			{ 
				return ResponseEntity.notFound().build();
			
			}
			else {
				return ResponseEntity.ok(user);
			}
		
	}
	
	@GetMapping(path="/getUsers")
	@ResponseBody
	public Set <UserRegistration>  getAllUsers() {
		System.out.println("Department Controller....Understanding client and talking to service layer...");
		Set<UserRegistration> userSet = userRegServ.findUsersService();
		return userSet;
		
	}
	
	
	
	@PostMapping(path="/addUser")
	public String addUserRegistration(@RequestBody UserRegistration user) {
		System.out.println("User Controller....Understanding client and talking to service layer...");
		UserRegistration userReg=new UserRegistration();
	      userReg.setFname(user.getFname());
	      userReg.setMailid(user.getMailid());
	      userReg.setLname(user.getLname());
	      userReg.setMname(user.getMname());
	      userReg.setMailid(user.getMailid());
	      userReg.setGender(user.getGender());
	      userReg.setAddress(user.getAddress());
	      userReg.setAdharNo(user.getAdharNo());
	      userReg.setPassword(user.getPassword());
	      userReg.setConfirmPassword(user.getConfirmPassword());
	      userReg.setPanNo(user.getPanNo());
	      userReg.setPhoneno(user.getPhoneno());
	      
	      
		 String stmsg = null;
		try {
			stmsg = userRegServ.addUserService(userReg);
		} 
	 catch (UserAlreadyExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
		catch(Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		System.out.println("controller printing"+stmsg);
		  return stmsg;
		
	}
		
	
	@PutMapping(path="/modifyUser")
	public String modifyDepartment(@RequestBody UserRegistration user)throws UserNotFoundException {
		System.out.println("User Controller....Understanding client and talking to service layer...");
		 String stmsg = null;
		try {
			stmsg = userRegServ.modifyUserService(user);
		} 
		catch (UserNotFoundException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("controller is saying: "+stmsg);
		  return stmsg;
		
	}
	@DeleteMapping(path="/deleteUser")
	public String removeDepartment(@RequestBody UserRegistration user)throws UserNotFoundException {
		System.out.println("User Controller....Understanding client and talking to service layer...");
		 String stmsg = null;
		try {
			stmsg = userRegServ.removeUserService(user.getUserId());
		} 
		catch (UserNotFoundException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("controller is saying: "+stmsg);
		  return stmsg;
		
	}
	@GetMapping(path="/getDocumentByUserId")
	@ResponseBody
	public Set<Document> getAllDocumentByUserId() {
		System.out.println("User Controller....Understanding client and talking to service layer...");
		UserRegistration user=null;
		try {
			user =userRegServ.findUserService(101);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
		Set<Document> docSet =user.getDocument();
		return docSet;
		
	}
	
	@GetMapping(path="/IncomeByUserId")
	@ResponseBody
	public Set<Income> getAllLoanByUserId() {
		System.out.println("User Controller....Understanding client and talking to service layer...");
		UserRegistration user=null;
		try {
			user =userRegServ.findUserService(101);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
		Set<Income> loanSet =user.getIncome();

		return loanSet;
		
	}
	
	

}
