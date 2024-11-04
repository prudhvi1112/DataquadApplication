package com.dataquad.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DataModel 
{
	    @Id
	    @Email
	    private String emailId;  
	    private String name;
	    private String grade;
	    private String reference;
	    private String recruiter; 
	    private String team;
	    private String mode;
	    private String skillSet;  
	    private String linkedinUrl;
	    private String billRate;
	    private String payRoll;
	    private String marketingStartDate;
	    private String vendorStatus;
	    private String experience;
	    @Embedded
	    private VisaInfo visaInfo; 
	    @Embedded
	    private ContactInfo contactInfo; 
	    @Embedded
	    private DateInfo dateInfo;
	    @Embedded
	    private LocationInfo locationInfo;
	    
}
