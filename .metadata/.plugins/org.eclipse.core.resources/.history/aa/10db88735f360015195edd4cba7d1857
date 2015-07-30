package controller;

import java.io.IOException;

import dao.service.BookService;
import dao.service.MemberService;
import dao.service.MemberServiceImpl;
import helper.LibraryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Book;
import model.LibraryMember;

public class CheckoutController {

	@FXML
	TextField memberId, booksISBN;
	@FXML
	Label lblMessage;

	MemberService ms;
	BookService bs;

	public CheckoutController() {
		// TODO Auto-generated constructor stu
		ms = new MemberServiceImpl();
		bs = new BookService();
		memberId = new TextField();
		booksISBN = new TextField();

	}

	public void openCheckout() {
		Stage libStage = new Stage();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("../view/CheckOut.fxml"));
			libStage.setTitle("Checkout");
			Scene scene = new Scene(root, 1000, 800);
			libStage.setScene(scene);
			libStage.show();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void search(ActionEvent e) throws Exception {

		check();

	}

	public void onCheckOut(ActionEvent e) throws Exception {
		check();
		
	}

	private void check() {

		try {
			if (memberId.getText().equals("") || memberId.getText() == null) {
				throw new LibraryException("Member id field cannot be empty!");
			}
			LibraryMember lm = ms.searchMember(memberId.getText());

			if (lm != null) {
				if (booksISBN.getText().equals("") || booksISBN.getText() == null) {
					throw new LibraryException("ISBN field cannot be empty!");
				}

				bs.searchBook(booksISBN.getText());
			}

		} catch (LibraryException exp) {
			lblMessage.setText(exp.getMessage());
		}
	}

}
