import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException{

	    System.out.println("Server is starting");
		try {
			//deserialize
			FileInputStream fileIn = new FileInputStream("donor.ser");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			DonorListInterface donors = (DonorListInterface) objectIn.readObject();
			objectIn.close();
			System.out.println(donors.getDonors());
			
			//rmi
			LocateRegistry.createRegistry(6600);
			Naming.rebind("rmi://localhost:6600/donors", donors);
		}catch(Exception e) {
			try {
				//serialize
				
				//create data
				Donor d1 = DonorFactory.getDonor("Constance", "constance@gmail.com", "Athlone", "0899462020", 20, "Female", "O+", LocalDate.of(2020, 3, 21));
				Donor d2 = DonorFactory.getDonor("Ting", "ting@gmail.com", "Athlone", "0899452020", 19, "Female", "A+", LocalDate.of(2022, 11, 20));
				DonorListInterface donors = new DonorList();
				donors.createDonor(d1);
				System.out.println("Create donor 1");
				donors.createDonor(d2);
				System.out.println("Create donor 2");
				
				FileOutputStream fileOut = new FileOutputStream("donor.ser");
				ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
				objectOut.writeObject(donors);
				objectOut.close();
				
				//rmi
				LocateRegistry.createRegistry(6600);
				Naming.rebind("rmi://localhost:6600/donors", donors);

			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}

	}

}
