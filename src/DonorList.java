import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;

public class DonorList extends UnicastRemoteObject implements DonorListInterface {

	
	private ArrayList<Donor> donorList;
	
	public DonorList() throws RemoteException {
		donorList = new ArrayList();
		
	}

	@Override
	public void createDonor(Donor d) throws RemoteException {
		// TODO Auto-generated method stub
		donorList.add(d);
		
	}

	@Override
	public void editDonor(String name, String email, Donor d) throws RemoteException {
		// TODO Auto-generated method stub
		Donor getdonor = getDonor(name, email);
		donorList.remove(getdonor);
		Donor editDonor = DonorFactory.getDonor(d.getName(), d.getEmail(), d.getAddress(), d.getPhoneNo(), d.getAge(), d.getGender(), d.getBloodGroup(), d.getLastDonationDay());
		donorList.add(editDonor);
		
	}

	@Override
	public void deleteDonor(Donor d) throws RemoteException {
		// TODO Auto-generated method stub
		Donor getdonor = getDonor(d.getName(), d.getEmail());
		donorList.remove(getdonor);
		
	}

	@Override
	public ArrayList<Donor> getDonors() throws RemoteException {
		// TODO Auto-generated method stub
		return donorList;
	}

	@Override
	public Donor getDonor(String name, String email) throws RemoteException {
		// TODO Auto-generated method stub
		for(Donor d : donorList) {
			if(d.getName().equals(name) && d.getEmail().equals(email)) {
				return d;
			}
		}
		
		return null;
		
	}
		
	
	
}
