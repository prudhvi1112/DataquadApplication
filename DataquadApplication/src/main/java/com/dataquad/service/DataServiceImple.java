package com.dataquad.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private DataDao dataDao;

	@Transactional
	public DataModel getDataModel(String email) {
		DataModel dataModel = dataDao.findById(email).orElse(null);

		return dataModel;

	}

	@Override
	@Transactional
	public DataDto createData(DataDto dataDto) {

		DataModel dataModel = getDataModel(dataDto.getEmailId());
		if (dataModel == null) {

			DataModel model = dataDao.save(dtoToModel(dataDto));
			return modelToDto(model);
		} else {
			throw new DataAlreadyExistsException("Data Already Exists");
		}
	}

	@Override
	@Transactional
	public DataDto updateData(String email, DataDto dataDto) {
		dataDto.setEmailId(email);

		DataModel model = getDataModel(email);
		if (model == null) {
			throw new DataNotFoundException("Data Not Found");
		} else {
			DataModel dataModel = dataDao.save(dtoToModel(dataDto));

			return modelToDto(dataModel);
		}
	}

	public DataModel dtoToModel(DataDto dataDto) {
		ContactInfo contactInfo = new ContactInfo(dataDto.getMarketingContact(), dataDto.getPersonalContact());
		LocationInfo locationInfo = new LocationInfo(dataDto.getLocation(), dataDto.getRelocation());
		VisaInfo visaInfo = new VisaInfo(dataDto.getMarketingVisa(), dataDto.getActualVisa());
		DateInfo dateInfo = new DateInfo(dataDto.getOriginalDob(), dataDto.getEditedDob());

		DataModel dataModel = new DataModel(dataDto.getEmailId(), dataDto.getName(), dataDto.getGrade(),
				dataDto.getReference(), dataDto.getRecuriter(), dataDto.getTeam(), dataDto.getMode(),
				dataDto.getSillSet(), dataDto.getLinkedinUrl(), dataDto.getBillRate(), dataDto.getPayRoll(),
				dataDto.getMarketingStartDate(), dataDto.getVendorStatus(), dataDto.getExperience(), visaInfo,
				contactInfo, dateInfo, locationInfo);

		return dataModel;

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

		DataDto dataDto = new DataDto(dataModel.getEmailId(), dataModel.getName(), dataModel.getGrade(),
				dataModel.getReference(), dataModel.getRecruiter(), dataModel.getTeam(), dataModel.getMode(),
				dataModel.getSkillSet(), marketingVisa, actualVisa, dataModel.getExperience(), marketingContact,
				personalContact, location, originalDob, editedDob, dataModel.getLinkedinUrl(), relocation,
				dataModel.getBillRate(), dataModel.getPayRoll(), dataModel.getMarketingStartDate(),
				dataModel.getVendorStatus());

		return dataDto;

	}

}
