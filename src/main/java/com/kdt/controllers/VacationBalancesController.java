package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdt.dto.VacationBalancesDTO;
import com.kdt.dto.VacationRequsetDTO;
import com.kdt.services.VacationBalancesService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/vacation")
public class VacationBalancesController {
	
	@Autowired
	VacationBalancesService vservice;
	
	@Autowired
	HttpSession session;
	
	@GetMapping
	public ResponseEntity<List> allVacationList(){
		List<VacationBalancesDTO> dto = vservice.allVacationList();
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/myVacation/{memberID}")
	public ResponseEntity<VacationBalancesDTO> myVacation(@PathVariable String memberID){
		System.out.println("테스트용");
		VacationBalancesDTO dto= vservice.myVacation(memberID);
		 return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/myVacation/{memberID}/{total_vactionDate}")
	public ResponseEntity<VacationBalancesDTO> myVacationUpdate(@PathVariable String memberID,@PathVariable int total_vactionDate ){
		VacationBalancesDTO dto= vservice.myVacationUpdate(memberID, total_vactionDate);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping("/add")
	public ResponseEntity<VacationBalancesDTO> addVacation(@RequestBody VacationRequsetDTO vRequest){
		VacationBalancesDTO dto=vservice.addVacation(vRequest);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping("/subtract")
	public ResponseEntity<VacationBalancesDTO> subtractVacation(@RequestBody VacationRequsetDTO vRequest){
		VacationBalancesDTO dto=vservice.subtractVacation(vRequest);
		return ResponseEntity.ok(dto);
	}
	
}