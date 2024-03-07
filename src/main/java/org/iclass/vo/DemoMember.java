package org.iclass.vo;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DemoMember {
	private String userid;
	private String name;
	@Setter
	private String password;
	private String email;
	private int birth;
	private String gender;
	private String phone;
	private String favorites;
	private String address1;
	private String address2;
	private String address3;
	private String address4;
	private String postalCode;
	private String adultYn;
	private Timestamp joinDate;
}
