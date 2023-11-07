import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface DonorListInterface extends Remote {
	public void createDonor(Donor d) throws RemoteException;
	public void editDonor(String name, String email, Donor d) throws RemoteException;
	public void deleteDonor(Donor d) throws RemoteException;
	public ArrayList<Donor> getDonors() throws RemoteException;
	public Donor getDonor(String name, String email) throws RemoteException; 
}
