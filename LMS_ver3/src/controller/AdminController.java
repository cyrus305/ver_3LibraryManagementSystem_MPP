package controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminController {
	
	public void openAddMember(){
		Stage libStage = new Stage();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("../view/AddMember.fxml"));
			libStage.setTitle("Add Member");
			Scene scene = new Scene(root, 1000, 800);
			libStage.setScene(scene);
			libStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
