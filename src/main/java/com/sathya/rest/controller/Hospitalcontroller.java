package com.sathya.rest.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.sathya.rest.models.Hospital;
import com.sathya.rest.service.HospitalService;



@RestController
@RequestMapping("/api/v1")
public class Hospitalcontroller {
@Autowired
HospitalService hospitalService;
@PostMapping("/savehospital")
public ResponseEntity<Hospital> saveHospital(@RequestBody Hospital hospital) {
    
    Hospital savehospital = hospitalService.saveHospital(hospital);
    return  ResponseEntity.status(HttpStatus.CREATED)
    		              .header("hospital status","hospital saved successfully")
    		              .body(savehospital);
}
@PostMapping("/saveallhosps")
public ResponseEntity<List<Hospital>> saveallHospitals(@RequestBody List<Hospital> hospitals) {
    
    List<Hospital> savehospitals= hospitalService.saveallHospitals(hospitals);
    return  ResponseEntity.status(HttpStatus.CREATED)
    		              .header("hospital status","hospital saved successfully")
    		              .body(savehospitals);
}
@GetMapping("/getallhosps")
public ResponseEntity<List<Hospital>> getallHospitals() {
    
    List<Hospital> hospitals = hospitalService.getallhospitals();
    return  ResponseEntity.status(HttpStatus.OK)
    		              .header("hospital status","hospital saved successfully")
    		              .body(hospitals);
}
@GetMapping("/getById/{id}")
public ResponseEntity<?> getById(@PathVariable Long id) {
    
    Optional<Hospital> hospitalId = hospitalService. getById(id);
    if(hospitalId.isPresent()) {
    return  ResponseEntity.status(HttpStatus.OK)
    		              .body(hospitalId.get());
    }
    else
    {
    	return  ResponseEntity.status(HttpStatus.NOT_FOUND)
	              .body("hospital not found..."+id);
    }
}
@GetMapping("/getBySpecialization/{specialization}")
public ResponseEntity<?> getByspecialization(@PathVariable String specialization) {
    
    Optional<Hospital> hospitalId1 = hospitalService.getByspecialization(specialization);
    if(hospitalId1.isPresent()) {
    return  ResponseEntity.status(HttpStatus.OK)
    		              .body(hospitalId1.get());
    }
    else
    {
    	return  ResponseEntity.status(HttpStatus.NOT_FOUND)
	              .body("hospital not found..."+specialization);
    }
}
@DeleteMapping("/deleteById/{id}")
public ResponseEntity<?>deleteById(@PathVariable Long id)
{boolean status= hospitalService.deleteById(id); 
 if(status)
 {
	 return ResponseEntity.noContent().build();		 
 }
 else
 {
	 return ResponseEntity.status(HttpStatus.NOT_FOUND)
			 .header("status","data is not found")
			 .body("data not found by id..."+id);
 }
}
@DeleteMapping("/deleteBySpecialization/{specialization}")
public ResponseEntity<?>deleteBySpecialization(@PathVariable String specialization)
{boolean status= hospitalService.deleteBySpecialization(specialization); 
 if(status)
 {
	 return ResponseEntity.noContent().build();	
	 
 }
 else
 {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	        .header("status","data is not found")
			 .body("data not found by specialization..."+specialization);
 }
}
@DeleteMapping("/deleteall")
public ResponseEntity<?>deleteall(){
boolean status= hospitalService.deleteAll(); 
 if(status)
 {
	 return ResponseEntity.noContent().build();	
	 
 }
 else
 {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	        .header("status","data is not found")
			 .body("data is deleted...");
 }
}
@PutMapping("updateById/{id}")
public ResponseEntity<?>updateById (@PathVariable Long id, @RequestBody Hospital newemployee)
{Optional<Hospital> updatedemployee=hospitalService.updateById(id, newemployee);
if(updatedemployee.isPresent())
{
	 return  ResponseEntity.status(HttpStatus.OK)
             .body(updatedemployee);
}
else
{
	return ResponseEntity.status(HttpStatus.NOT_FOUND)
			 .body("data not found by id..."+id);
}
}
@PatchMapping("update/{id}")
public ResponseEntity<?>updatePartialById (@PathVariable Long id, @RequestBody Map<String,Object>updates)
{Optional<Hospital> updatedemployee=hospitalService.updatePartialById(id, updates);
if(updatedemployee.isPresent())
{
	return  ResponseEntity.status(HttpStatus.OK)
    .body(updatedemployee.get());
}
else
{
return  ResponseEntity.status(HttpStatus.NOT_FOUND)
.body("data is not found.."+id);
}
}
@GetMapping("/getnames")
public List<String>getNames()
{
return List.of("nikhitha","prathyusha","krupali","sravani","nikitha");	
}
}


