package com.dataquad.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dataquad.exceptions.DataAlreadyExistsException;
import com.dataquad.exceptions.DataNotFoundException;
import com.dataquad.model.ContactInfo;
import com.dataquad.model.DataDto;
import com.dataquad.model.DataModel;
import com.dataquad.model.DateInfo;
import com.dataquad.model.LocationInfo;
import com.dataquad.model.VisaInfo;
import com.dataquad.repository.DataDao;

@Service
public class DataServiceImple implements DataService {

	private String errorMessage = "Data Not Found";

	private String errorMessageDataAlready = "Data Already Exists";

	@Autowired
	private DataDao dataDao;

	@Transactional
	public DataModel getDataModel(String email) {
		return dataDao.findByEmailIdIgnoreCase(email);

	}

	@Override
	@Transactional
	public DataDto createData(DataDto dataDto) {

		DataModel dataModel = getDataModel(dataDto.getEmailId());
		if (dataModel == null) {

			DataModel model = dataDao.save(dtoToModel(dataDto));
			return modelToDto(model);
		} else {
			throw new DataAlreadyExistsException(errorMessageDataAlready);
		}
	}

	@Override
	@Transactional
	public DataDto updateData(String email, DataDto dataDto) {

		DataModel model = getDataModel(email);

		if (model == null) {
			throw new DataNotFoundException();
		} else {
			dataDto.setEmailId(model.getEmailId());
			DataModel dataModel = dataDao.save(dtoToModel(dataDto));

			return modelToDto(dataModel);
		}
	}

	@Override
	@Transactional
	public DataDto getData(String email) {
		DataModel dataModel = dataDao.findByEmailIdIgnoreCase(email);
		if (dataModel == null) {
			throw new DataNotFoundException(errorMessage);

		} else {
			return modelToDto(dataModel);
		}
	}

	@Override
	@Transactional
	public List<DataDto> getDataList(int page, int size) {

		List<DataModel> content = dataDao.findAll(PageRequest.of(page, size)).getContent();
		List<DataDto> dataList = new ArrayList<>();

		if (content.isEmpty()) {
			throw new DataNotFoundException(errorMessage);

		} else {
			for (DataModel model : content) {
				dataList.add(modelToDto(model));
			}

			return dataList;
		}
	}

	@Override
	@Transactional
	public boolean deleteData(String email) {
		DataModel dataModel = getDataModel(email);
		if (dataModel == null) {
			throw new DataNotFoundException(errorMessage);
		} else {
			dataDao.delete(dataModel);
			return true;
		}
	}

	public DataModel dtoToModel(DataDto dataDto) {
		ContactInfo contactInfo = new ContactInfo(dataDto.getMarketingContact(), dataDto.getPersonalContact());
		LocationInfo locationInfo = new LocationInfo(dataDto.getLocation(), dataDto.getRelocation());
		VisaInfo visaInfo = new VisaInfo(dataDto.getMarketingVisa(), dataDto.getActualVisa());
		DateInfo dateInfo = new DateInfo(dataDto.getOriginalDob(), dataDto.getEditedDob());

		return new DataModel(dataDto.getEmailId(), dataDto.getName(), dataDto.getGrade(), dataDto.getReference(),
				dataDto.getRecuriter(), dataDto.getTeam(), dataDto.getMode(), dataDto.getSillSet(),
				dataDto.getLinkedinUrl(), dataDto.getBillRate(), dataDto.getPayRoll(), dataDto.getMarketingStartDate(),
				dataDto.getVendorStatus(), dataDto.getExperience(), visaInfo, contactInfo, dateInfo, locationInfo);

	}

	public DataDto modelToDto(DataModel dataModel) {
		String marketingContact = dataModel.getContactInfo().getMarketingContact();
		String personalContact = dataModel.getContactInfo().getPersonalContact();
		String location = dataModel.getLocationInfo().getLocation();
		String relocation = dataModel.getLocationInfo().getRelocation();
		String actualVisa = dataModel.getVisaInfo().getActualVisa();
		String marketingVisa = dataModel.getVisaInfo().getMarketingVisa();
		LocalDate editedDob = dataModel.getDateInfo().getEditedDob();
		LocalDate originalDob = dataModel.getDateInfo().getOriginalDob();

		return new DataDto(dataModel.getEmailId(), dataModel.getName(), dataModel.getGrade(), dataModel.getReference(),
				dataModel.getRecruiter(), dataModel.getTeam(), dataModel.getMode(), dataModel.getSkillSet(),
				marketingVisa, actualVisa, dataModel.getExperience(), marketingContact, personalContact, location,
				originalDob, editedDob, dataModel.getLinkedinUrl(), relocation, dataModel.getBillRate(),
				dataModel.getPayRoll(), dataModel.getMarketingStartDate(), dataModel.getVendorStatus());

	}

}
