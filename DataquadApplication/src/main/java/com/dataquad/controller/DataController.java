package com.dataquad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
		return new ResponseEntity<>(data, HttpStatus.CREATED);
	}

	@PutMapping("/{emailId}")
	public ResponseEntity<DataDto> updateData(@PathVariable("emailId") String emailId, @RequestBody DataDto dataDto) {
		DataDto updateData = dataServiceImple.updateData(emailId, dataDto);
		return new ResponseEntity<>(updateData, HttpStatus.OK);
	}

	@GetMapping("/{emailId}")
	public ResponseEntity<DataDto> getData(@PathVariable("emailId") String emailId) {
		DataDto dataDto = dataServiceImple.getData(emailId);
		return new ResponseEntity<>(dataDto, HttpStatus.FOUND);

	}

	@GetMapping("/{page}/{size}")
	public ResponseEntity<List<DataDto>> getDataList(@PathVariable("page") int page, @PathVariable("size") int size) {
		List<DataDto> dataList = dataServiceImple.getDataList(page, size);
		return new ResponseEntity<>(dataList, HttpStatus.FOUND);

	}

	@DeleteMapping("/{emailId}")
	public ResponseEntity<Void> deleteData(@PathVariable("emailId") String emailId) {
		dataServiceImple.deleteData(emailId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}
