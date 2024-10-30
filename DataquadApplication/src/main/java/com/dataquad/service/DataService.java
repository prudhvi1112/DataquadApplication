package com.dataquad.service;

import com.dataquad.model.DataDto;

public interface DataService
{
	
	public DataDto createData(DataDto dataDto);
	public DataDto updateData(String email, DataDto dataDto);

}
