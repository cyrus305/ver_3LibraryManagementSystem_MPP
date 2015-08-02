package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminController {
	Stage stage = new Stage();
	Parent root;
	
	@FXML
	Button btnLogOut;
	@FXML
	Button btnAddMember;
	@FXML
	Button btnEditMember;
	@FXML
	Button btnAddBook;
	

	public void openAddMember() {
		
		try {
			root = FXMLLoader.load(getClass().getResource("../view/AddMember.fxml"));
			stage.setTitle("Add Member");
			Scene scene = new Scene(root, 1000, 800);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void EditMember() {
		try {
			root = FXMLLoader.load(getClass().getResource("../view/EditMember.fxml"));
			stage.setTitle("Edit Member");
			Scene scene = new Scene(root, 1000, 800);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void AddBook() {
		try {
			root = FXMLLoader.load(getClass().getResource("../view/AddBook.fxml"));
			stage.setTitle("Add Book");
			Scene scene = new Scene(root, 1000, 800);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void logOut(){
		btnLogOut.getScene().getWindow().hide();
		Stage stage = new Stage();
		Parent root;
		try {
			
			root = FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"));
			stage.setTitle("Add Member");
			Scene scene = new Scene(root, 1000, 800);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}
	

}
