package com.sathya.rest.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sathya.rest.models.Hospital;
import com.sathya.rest.repository.HospitalRepository;

@Service
public class HospitalService {
@Autowired
HospitalRepository hospitalRespository;

public Hospital saveHospital(Hospital hospital) {
	Hospital savehospital= hospitalRespository.save( hospital);
	 return savehospital;
}

public List<Hospital> saveallHospitals(List<Hospital> hospitals) {
	List<Hospital> savehospitals= hospitalRespository.saveAll( hospitals);
	 return savehospitals;	
}

public List<Hospital> getallhospitals() {
	List<Hospital> hospitals=hospitalRespository.findAll();
	return hospitals;
}

public Optional<Hospital> getById(Long id) {
		Optional<Hospital> hospitalId=hospitalRespository.findById(id);
		return hospitalId;
	}

public Optional<Hospital> getByspecialization(String specialization) {
	Optional<Hospital> hospitalId1=hospitalRespository.findByspecialization(specialization);
	return hospitalId1;
}

public boolean deleteById(Long id) {
	boolean status=hospitalRespository.existsById(id);
	if(status)
	{
		hospitalRespository.existsById(id);
		return true;
	}
	else
	{
		return false;
	}
}

public boolean deleteBySpecialization(String specialization) {
	boolean status=hospitalRespository.existsBySpecialization(specialization);
	if(status)
	{   hospitalRespository.deleteBySpecialization(specialization);
		return true;
	}
	else
	{
		return false;
	}
}

public boolean deleteAll() {
	hospitalRespository.deleteAll();
	return true;
}
public Optional<Hospital>updateById(Long id ,Hospital newhosp)
{
	Optional<Hospital> optionalhosp=hospitalRespository.findById(id);
	if(optionalhosp.isPresent())
	{
		Hospital existinghosp =optionalhosp.get();
		existinghosp.setName(newhosp.getName());
		existinghosp.setAddress(newhosp.getAddress());
		existinghosp.setMobile(newhosp.getMobile());
		existinghosp.setSpecialization(newhosp.getSpecialization());
		existinghosp.setRating(newhosp.getRating());
		Hospital updatedhosp=hospitalRespository.save(existinghosp);
		return Optional.of(updatedhosp);
	}
	else
	{
		return Optional.empty(); 
	}
}

public Optional<Hospital> updatePartialById(Long id, Map<String, Object> updates) {
	Optional<Hospital> optionalhosp=hospitalRespository.findById(id);
	if(optionalhosp.isPresent())
	{
		Hospital existinghosp= optionalhosp.get();
		updates.forEach((key,value)  -> {
			switch(key) {
			case "name":
				existinghosp.setName((String) value);
				break;
			case "address":
				existinghosp.setAddress((String) value);
				break;
			case "mobile":
				existinghosp.setMobile((Long) value);
				break;
			case "specialization":
				existinghosp.setSpecialization((String) value);
				break;
			case "rating":
				existinghosp.setRating((Double) value);
				break;
		}
	});
		Hospital updatedhosp=hospitalRespository.save(existinghosp);
		return Optional.of(updatedhosp);
}
	else
	{
		return Optional.empty();
	}
	
}

}


