package com.dataquad.model;

import java.time.LocalDate;




import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DataDto
{

	private String emailId;	
	private String name;
	private String grade;
	private String reference;
	private String recuriter;
	private String team;
	private String mode;
	private String sillSet;
	private String marketingVisa;
	private String actualVisa;
	private String experience;
	private String marketingContact;
	private String personalContact;
	private String location;
	private LocalDate originalDob;
	private LocalDate editedDob;
	private String linkedinUrl;
	private String relocation;
	private String billRate;
	private String payRoll;
	private String marketingStartDate;
	private String vendorStatus;
	
	


}
