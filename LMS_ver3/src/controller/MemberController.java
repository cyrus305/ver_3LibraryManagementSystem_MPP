package controller;

import java.util.HashMap;

import dao.Impl.DataAccessFacade;
import dao.service.DataAccess;
import dao.service.MemberService;
import dao.service.MemberServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Address;
import model.LibraryMember;

public class MemberController {
	@FXML
	TextField memberID, firstName, lastName, telephone, street, city, state, zip;
	@FXML
	Label memberIdError;
	@FXML
	TextField mbrId;
	MemberService service = new MemberServiceImpl();

	public void addMember() throws Exception {
		addNewMember(memberID.getText(), firstName.getText(), lastName.getText(), telephone.getText(),
				new Address(street.getText(), city.getText(), state.getText(), zip.getText()));
	}

	public void search() throws Exception {
		search(mbrId.getText());
	}

	// Use Factory Method to call the servie of dao layer
	// public void addMember(LibraryMember member) throws Exception {
	// // Adding Member
	// service.saveMember(member);
	// }

	/**
	 * This method checks if memberId already exists -- if so, it cannot be
	 * added as a new member, and an exception is thrown. If new, creates a new
	 * LibraryMember based on input data and uses DataAccess to store it.
	 * 
	 * @throws Exception
	 * 
	 */
	public void addNewMember(String memberId, String firstName, String lastName, String telNumber, Address addr)
			throws Exception {
		DataAccess dataAccess = new DataAccessFacade();
		HashMap<String, LibraryMember> memberMap = dataAccess.readMemberMap();
		if (!memberMap.containsKey(memberId)) {
			memberIdError.setText("");
			service.saveMember(new LibraryMember(memberId, firstName, lastName, telNumber, addr));
		} else {
			memberIdError.setText("Member with Id# " + memberID.getText() + " already exists!");
		}

	}

	/**
	 * Reads data store for a library member with specified id. Ids begin at
	 * 1001... Returns a LibraryMember if found, null otherwise
	 * 
	 * @throws Exception
	 * 
	 */
	public LibraryMember search(String memberId) throws Exception {
		MemberService ms = new MemberServiceImpl();
		System.out.println("searchMember");
		return ms.searchMember(memberId);
	}

}