import java.io.Serializable;
import java.rmi.Remote;
import java.time.LocalDate;

public abstract class Donor implements Serializable, Remote{
	private static final long serialVersionUID = 6529685098267757690L;
	
	public abstract String getName();
	public abstract String getEmail();
	public abstract String getAddress();
	public abstract String getPhoneNo();
	public abstract int getAge();
	public abstract String getGender();
	public abstract String getBloodGroup();
	public abstract LocalDate getLastDonationDay();
	public abstract Boolean getEligibilty();
	
	public abstract void setName(String name);
	public abstract void setEmail(String email);
	public abstract void setAddress(String address);
	public abstract void setPhoneNo(String phone);
	public abstract void setAge(int age);
	public abstract void setGender(String gender);
	public abstract void setBloodGroup(String blood);
	public abstract void setLastDonationDate(LocalDate date);
	
	public abstract String toString();
	
}
