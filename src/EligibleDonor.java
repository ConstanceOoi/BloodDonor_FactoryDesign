import java.rmi.RemoteException;
import java.time.LocalDate;

public class EligibleDonor extends Donor {
	private String name;
	private String email;
	private String address;
	private String phoneNo;
	private int age;
	private String gender;
	private String bloodGroup;
	private LocalDate lastDonationDay;
	
	public EligibleDonor(String name, String email, String address, String phoneNo, int age, String gender, String bloodGroup, LocalDate date) throws RemoteException {
		this.name = name;
		this.email = email;
		this.address = address;
		this.phoneNo = phoneNo;
		this.age = age;
		this.gender = gender;
		this.bloodGroup = bloodGroup;
		this.lastDonationDay = date;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}
	
	@Override
	public String getAddress() {
		// TODO Auto-generated method stub
		return address;
	}
	
	@Override
	public String getPhoneNo() {
		// TODO Auto-generated method stub
		return phoneNo;
	}
	
	@Override
	public int getAge() {
		// TODO Auto-generated method stub
		return age;
	}
	
	@Override
	public String getGender() {
		// TODO Auto-generated method stub
		return gender;
	}
	
	@Override
	public String getBloodGroup() {
		// TODO Auto-generated method stub
		return bloodGroup;
	}
	
	@Override
	public LocalDate getLastDonationDay() {
		// TODO Auto-generated method stub
		return lastDonationDay;
	}
	
	@Override
	public Boolean getEligibilty() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public String toString() {
		return "Eligible Donor \n" + "Name: " + name + "\nEmail: " + email + "\nAddress: " + address + "\nPhone No.: " + phoneNo + "\nAge: " + age + "\nGender: " + gender + "\nBlood Group: " + bloodGroup + "\nLast Donation Date: " + lastDonationDay;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
		
	}

	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub
		this.email = email;
	}

	@Override
	public void setAddress(String address) {
		// TODO Auto-generated method stub
		this.address = address;
	}

	@Override
	public void setPhoneNo(String phone) {
		// TODO Auto-generated method stub
		this.phoneNo = phone;
	}

	@Override
	public void setAge(int age) {
		// TODO Auto-generated method stub
		this.age = age;
	}

	@Override
	public void setGender(String gender) {
		// TODO Auto-generated method stub
		this.gender = gender;
	}

	@Override
	public void setBloodGroup(String blood) {
		// TODO Auto-generated method stub
		this.bloodGroup = blood;
	}

	@Override
	public void setLastDonationDate(LocalDate date) {
		// TODO Auto-generated method stub
		this.lastDonationDay = date;
	}

	

	
	
}
