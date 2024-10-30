package com.dataquad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataquad.model.DataDto;
import com.dataquad.service.DataServiceImple;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/data")
public class DataController {

	@Autowired
	private DataServiceImple dataServiceImple;

	@PostMapping("/")
	public ResponseEntity<DataDto> insertData(@Valid @RequestBody DataDto dataDto) {
		DataDto data = dataServiceImple.createData(dataDto);

		return new ResponseEntity<DataDto>(data, HttpStatus.CREATED);
	}

	@PutMapping("/{emailId}")
	public ResponseEntity<DataDto> updateData(@PathVariable("emailId") String emailId, @RequestBody DataDto dataDto) {
		DataDto updateData = dataServiceImple.updateData(emailId, dataDto);
		return new ResponseEntity<DataDto>(updateData, HttpStatus.OK);
	}

}
