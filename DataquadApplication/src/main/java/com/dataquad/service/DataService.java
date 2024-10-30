package com.dataquad.service;

import java.util.List;

import com.dataquad.model.DataDto;

public interface DataService
{
	
	public DataDto createData(DataDto dataDto);
	public DataDto updateData(String email, DataDto dataDto);
	public DataDto getData(String email);
	public boolean deleteData(String email);
	public List<DataDto> getDataList(int page,int size);

}
