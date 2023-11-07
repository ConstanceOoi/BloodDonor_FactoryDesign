import java.rmi.RemoteException;
import java.time.LocalDate;

public class DonorFactory {
	
	public static Donor getDonor(String name, String email, String address, String phone, int age, String gender, String blood, LocalDate lastDonationDate) {
		//calculate the date after 56 days from the last blood donation
		LocalDate date = lastDonationDate.plusDays(56);
		
		//check if the date is after the current date
		//if it is then it is an eligible donor
		//else it is an ineligible donor
		if(LocalDate.now().isAfter(date)) {
			try {
				return new EligibleDonor(name, email, address, phone, age, gender, blood, lastDonationDate);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}else {
			try {
				return new IneligibleDonor(name, email, address, phone, age, gender, blood, lastDonationDate);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return null ;
	}
}
