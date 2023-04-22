package net.javaguides.springboot.web.dto;

public class UserRegistrationDto {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String maBHYT;
	private String cmnd;
	private String address;
	private String phoneNumber;
	private String coquanBHYT;
	private String birthDay;

	public UserRegistrationDto() {

	}

	public UserRegistrationDto(String firstName, String lastName, String email, String password, String maBHYT,
			String birthDay, String cmnd, String address, String phoneNumber, String coquanBHYT) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.maBHYT = maBHYT;
		this.birthDay = birthDay;
		this.cmnd = cmnd;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.coquanBHYT = coquanBHYT;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMaBHYT() {
		return maBHYT;
	}

	public void setMaBHYT(String maBHYT) {
		this.maBHYT = maBHYT;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCoquanBHYT() {
		return coquanBHYT;
	}

	public void setCoquanBHYT(String coquanBHYT) {
		this.coquanBHYT = coquanBHYT;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getBirthDay() {
		return birthDay;
	}

}
