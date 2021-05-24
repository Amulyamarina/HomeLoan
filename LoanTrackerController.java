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
import com.example.homeloan.layer2.LoanTracker;
import com.example.homeloan.layer3.DocumentRepo;
import com.example.homeloan.layer4.LoanTrackerService;
import com.example.homeloan.layer4.exceptions.LoanTrackerAlreadyExsitException;
import com.example.homeloan.layer4.exceptions.LoanTrackerNotFoundException;

@RestController
public class LoanTrackerController {

	@Autowired
	LoanTrackerService loanTrackerServ;
	
	@Autowired
	DocumentRepo docRepo;
	
	@GetMapping(path="/getLoanTracker/{mylno}")
	@ResponseBody
	public ResponseEntity<LoanTracker> getLoanTracker(@PathVariable("mylno") Integer lno) throws LoanTrackerNotFoundException {
		System.out.println("LoanTracker Controller....Understanding client and talking to service layer...");
		LoanTracker loanTracker=null;
		
		loanTracker = loanTrackerServ.findLoanTrackerService(lno);
			if(loanTracker==null)
			{ 
				return ResponseEntity.notFound().build();
			
			}
			else {
				return ResponseEntity.ok(loanTracker);
			}
		
	}
	
	@GetMapping(path="/getLoanTrackers")
	@ResponseBody
	public Set<LoanTracker> getAllLoanTrackers() {
		System.out.println("LoanTracker Controller....Understanding client and talking to service layer...");
		Set<LoanTracker> loanTrackerSet = loanTrackerServ.findLoanTrackersService();
		
		return loanTrackerSet;
		
	}

	@PostMapping(path="/addLoanTracker")
	public String addLoanTracker(@RequestBody LoanTracker loanTracker) {
		System.out.println(" LoanTracker Controller....Understanding client and talking to service layer...");
		LoanTracker loan=new LoanTracker();
		loan.setAccNo(loanTracker.getAccNo());
		loan.setLoanApprovalDate(loanTracker.getLoanApprovalDate());
		 Document document=docRepo.findDocument(702);
		 loan.setDocument(document);
		String stmsg = null;
		try {
			stmsg = loanTrackerServ.addLoanTrackerService(loanTracker);
		} 
	 catch (LoanTrackerAlreadyExsitException e) {
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
	@PutMapping(path="/modifyLoanTracker")
	public String modifyLoanTracker(@RequestBody LoanTracker loanTracker)throws LoanTrackerNotFoundException {
		System.out.println("LoanTracker Controller....Understanding client and talking to service layer...");
		 String stmsg = null;
		try {
			stmsg = loanTrackerServ.modifyLoanTrackerService(loanTracker);
		} 
		catch (LoanTrackerNotFoundException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("controller is saying: "+stmsg);
		  return stmsg;
		
	}
	@DeleteMapping(path="/deleteLoanTracker")
	public String removeLoanTracker(@RequestBody LoanTracker loanTracker)throws LoanTrackerNotFoundException {
		System.out.println("LoanTracker Controller....Understanding client and talking to service layer...");
		 String stmsg = null;
		try {
			stmsg = loanTrackerServ.removeLoanTrackerService(loanTracker.getFinalId());
		} 
		catch (LoanTrackerNotFoundException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("controller is saying: "+stmsg);
		  return stmsg;
		
	}
	


}

