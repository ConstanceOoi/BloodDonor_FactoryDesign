import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUI implements ActionListener{
	
	private static DonorListInterface donorList;
	JFrame window = new JFrame("Donor");
	Panel panel = new Panel();
	
	//create buttons
	private JButton createButton = new JButton("Create");
	private JButton editButton = new JButton("Edit");
	private JButton deleteButton = new JButton("Delete");
	private JButton prevButton = new JButton("Prev");
	private JButton nextButton = new JButton("Next");
	private JButton clearButton = new JButton("Clear");
	
	//create labels
	private JLabel nameLabel = new JLabel("Name: ");
	private JLabel emailLabel = new JLabel("Email: ");
	private JLabel addLabel = new JLabel("Address: ");
	private JLabel phoneLabel = new JLabel("Phone No.: ");
	private JLabel ageLabel = new JLabel("Age: ");
	private JLabel genderLabel = new JLabel("Gender: ");
	private JLabel bloodLabel = new JLabel("Blood Group: ");
	private JLabel dateLabel = new JLabel("Last Blood Donation Date (YYYY-MM-DD): ");
	private JLabel eligLabel = new JLabel("Eligibility");
	
	//create textfield
	private JTextField nameTF = new JTextField();
	private JTextField emailTF = new JTextField();
	private JTextField addTF = new JTextField();
	private JTextField phoneTF = new JTextField();
	private JTextField ageTF = new JTextField();
	private JTextField genderTF = new JTextField();
	private JTextField bloodTF = new JTextField();
	private JTextField dateTF = new JTextField();
	private JTextField eligTF = new JTextField();
	
	//store all textfield into arraylist
	public ArrayList<JTextField> tfList = new ArrayList<JTextField>();
	
	//counter
	private int i;
	
	
	public GUI(ArrayList<Donor> d) throws ClassNotFoundException, IOException {
		//set layout
		GridLayout grid = new GridLayout(12, 2);
		panel.setLayout(grid);
		
		//set the first donor's details to the corresponding text field
		nameTF.setText(d.get(0).getName());
		emailTF.setText(d.get(0).getEmail());
		addTF.setText(d.get(0).getAddress());
		phoneTF.setText(d.get(0).getPhoneNo());
		ageTF.setText(d.get(0).getAge()+ "");
		genderTF.setText(d.get(0).getGender());
		bloodTF.setText(d.get(0).getBloodGroup());
		dateTF.setText(d.get(0).getLastDonationDay() + "");
		
		//set text for eligibility
		if(d.get(0).getEligibilty() == true) {
			eligTF.setText("Eligilble to donate blood");
		}else {
			eligTF.setText("Not eligible to donate blood");
		}
		
		//add label and textfield to panel
		panel.add(nameLabel);
		panel.add(nameTF);
		
		panel.add(emailLabel);
		panel.add(emailTF);
		
		panel.add(addLabel);
		panel.add(addTF);
		
		panel.add(phoneLabel);
		panel.add(phoneTF);
		
		panel.add(ageLabel);
		panel.add(ageTF);
		
		panel.add(genderLabel);
		panel.add(genderTF);
		
		panel.add(bloodLabel);
		panel.add(bloodTF);
		
		panel.add(dateLabel);
		panel.add(dateTF);
		
		//false  = to prevent the users from changing it
		eligTF.setEditable(false);
		panel.add(eligLabel);
		panel.add(eligTF);
		
		//add textfield to arraylist
		tfList.add(nameTF);
		tfList.add(emailTF);
		tfList.add(addTF);
		tfList.add(phoneTF);
		tfList.add(ageTF);
		tfList.add(genderTF);
		tfList.add(bloodTF);
		tfList.add(dateTF);
		
		//add buttons to panel
		panel.add(createButton);
		panel.add(editButton);
		panel.add(prevButton);
		panel.add(nextButton);
		panel.add(deleteButton);
		panel.add(clearButton);
		
		//action listener for buttons
		createButton.addActionListener(this);
		editButton.addActionListener(this);
		prevButton.addActionListener(this);
		nextButton.addActionListener(this);
		deleteButton.addActionListener(this);
		clearButton.addActionListener(this);
		
		window.getContentPane().add(panel);
		window.setSize(500,300);
		window.setVisible(true);
		
	}
	
	
	public static void main(String[] args) {
		System.out.println("Client is starting");
		try {
			donorList = (DonorListInterface)Naming.lookup("rmi://localhost:6600/donors");
			ArrayList<Donor> donors = donorList.getDonors();
			
			for(Donor d : donors) {
				System.out.println(d.toString());
			}
			
			new GUI(donors);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void serialize(DonorListInterface d) throws ClassNotFoundException, IOException{
		
		//serialize
		try {
			
			FileOutputStream fileOut = new FileOutputStream("donor.ser");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			
			//write donor to file
			objectOut.writeObject(d);
			objectOut.close();
			System.out.println("Write");
					
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<Donor> deserialize() throws ClassNotFoundException, IOException{
		
		DonorListInterface donors = new DonorList();
		//deserialize
		try {
		
			System.out.println("Check for file");
			FileInputStream fileIn = new FileInputStream("donor.ser");
			System.out.println("Reading file");
			ObjectInputStream objIn = new ObjectInputStream(fileIn);
			donors = (DonorListInterface) objIn.readObject();
			objIn.close();
			
			return donors.getDonors();
			
		}catch(FileNotFoundException e) {
			//file does not exists
			//create file
			System.out.println("File does not exists");
			serialize(donors);
		}	
		
		return null;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//CREATE button 
		if(e.getSource().equals(createButton)) {
			//check if all textfield are filled 
			for(JTextField tf : tfList) {
				if(tf.getText().trim().equals("")) {
					tf.setForeground(Color.RED);
					tf.setText("Please fill in before creating");
				}
				
			}
			
			try {
				//
				Donor d = DonorFactory.getDonor(tfList.get(0).getText(), tfList.get(1).getText(), tfList.get(2).getText(), tfList.get(3).getText(), Integer.parseInt(tfList.get(4).getText()), tfList.get(5).getText(), tfList.get(6).getText(), LocalDate.parse(tfList.get(7).getText()));
				
				//create donor
				donorList.createDonor(d);
				
				//set text for eligibility
				if(d.getEligibilty() == true) {
					eligTF.setText("Eligilble to donate blood");
				}else {
					eligTF.setText("Not eligible to donate blood");
				}
				
				//serialize into donors
				serialize(donorList);
			
			} catch (IOException | ClassNotFoundException e2) {
				e2.printStackTrace();
			}
			
			
		}
		
		//EDIT BUTTON
		if(e.getSource().equals(editButton)) {
			
			//check if all text field are filled
			for(JTextField tf : tfList) {
				if(tf.getText().trim().equals("")) {
					tf.setForeground(Color.RED);
					tf.setText("Please fill in before creating");
				}
				
			}
			
			try {
				//get donor details
				Donor d = DonorFactory.getDonor(tfList.get(0).getText(), tfList.get(1).getText(), tfList.get(2).getText(), tfList.get(3).getText(), Integer.parseInt(tfList.get(4).getText()), tfList.get(5).getText(), tfList.get(6).getText(), LocalDate.parse(tfList.get(7).getText()));
				donorList.editDonor(tfList.get(0).getText(), tfList.get(1).getText(), d);
				
				//set text for eligibility
				if(d.getEligibilty() == true) {
					eligTF.setText("Eligilble to donate blood");
				}else {
					eligTF.setText("Not eligible to donate blood");
				}
				
				//serialize into donors
				serialize(donorList);
				
			} catch (IOException | ClassNotFoundException e3) {
				e3.printStackTrace();
			}
		}
		
		//DELETE BUTTON
		if(e.getSource().equals(deleteButton)) {
			
			try {
				//get the donor details
				Donor d = DonorFactory.getDonor(tfList.get(0).getText(), tfList.get(1).getText(), tfList.get(2).getText(), tfList.get(3).getText(), Integer.parseInt(tfList.get(4).getText()), tfList.get(5).getText(), tfList.get(6).getText(), LocalDate.parse(tfList.get(7).getText()));
				
				//remove donor from the list 
				donorList.deleteDonor(d);
				
				//serialize back
				serialize(donorList);
				
			} catch (IOException | ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			
			//clear all textfield
			for(JTextField tf: tfList) {
				tf.setText("");
				eligTF.setText("");
			}
		}
		
		//DISPLAY PREVIOUS OBJECT BUTTON
		if(e.getSource().equals(prevButton)) {
			ArrayList<Donor> donors = new ArrayList<Donor>();
			
			try {
				//deserialize donor list
				donors = deserialize();
				
				//if counter is bigger than 0, 
				//counter - 1 to get the previous object and set text to all text fields
				if(i > 0) {
					i -= 1;
					Donor d = DonorFactory.getDonor(donors.get(i).getName(), donors.get(i).getEmail(), donors.get(i).getAddress(), donors.get(i).getPhoneNo(), donors.get(i).getAge(), donors.get(i).getGender(), donors.get(i).getBloodGroup(), donors.get(i).getLastDonationDay());
					nameTF.setText(d.getName());
					emailTF.setText(d.getEmail());
					addTF.setText(d.getAddress());
					phoneTF.setText(d.getPhoneNo());
					ageTF.setText(d.getAge() + "");
					genderTF.setText(d.getGender());
					bloodTF.setText(d.getBloodGroup());
					dateTF.setText(d.getLastDonationDay() +"");
					if(d.getEligibilty() == true) {
						eligTF.setText("Eligilble to donate blood");
					}else {
						eligTF.setText("Not eligible to donate blood");
					}
					
				}
				
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
			
			
		}
		
		//DISPLAY NEXT OBJECT BUTTON
		if(e.getSource().equals(nextButton)) {
			ArrayList<Donor> donors = new ArrayList<Donor>();
			
			try {
				//deserialize donor list
				donors = deserialize();
				
				//if counter is lesser than the arraylist
				//add counter with 1 and get the donor 
				//set details to the corresponding text fields
				if(i < (donors.size()-1)) {
					i += 1;
					Donor d = DonorFactory.getDonor(donors.get(i).getName(), donors.get(i).getEmail(), donors.get(i).getAddress(), donors.get(i).getPhoneNo(), donors.get(i).getAge(), donors.get(i).getGender(), donors.get(i).getBloodGroup(), donors.get(i).getLastDonationDay());
					nameTF.setText(d.getName());
					emailTF.setText(d.getEmail());
					addTF.setText(d.getAddress());
					phoneTF.setText(d.getPhoneNo());
					ageTF.setText(d.getAge() + "");
					genderTF.setText(d.getGender());
					bloodTF.setText(d.getBloodGroup());
					dateTF.setText(d.getLastDonationDay() +"");
					if(d.getEligibilty() == true) {
						eligTF.setText("Eligilble to donate blood");
					}else {
						eligTF.setText("Not eligible to donate blood");
					}
					
				}
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
		//CLEAR BUTTON
		//clear all text field
		if(e.getSource().equals(clearButton)) {
			for(JTextField tf: tfList) {
				tf.setText("");
				eligTF.setText("");
			}
		}
	}
	
}
