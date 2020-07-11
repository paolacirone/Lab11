package it.polito.tdp.rivers;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<String> boxRiver;

	@FXML
	private TextField txtStartDate;

	@FXML
	private TextField txtEndDate;

	@FXML
	private TextField txtNumMeasurements;

	@FXML
	private TextField txtFMed;

	@FXML
	private TextField txtK;

	@FXML
	private Button btnSimula;

	@FXML
	private TextArea txtResult;

	private Model model;

	@FXML
	void doCompletamento(ActionEvent event) {

		String r = this.boxRiver.getValue();

		if (r != null) {

			this.txtNumMeasurements.setText("" + model.getTot(r));

			this.txtFMed.setText(String.format("%.02f",model.getMedia(r)));
			this.txtStartDate.setText("" + this.model.getDate(r).get(0));
			this.txtEndDate.setText("" + this.model.getDate(r).get(model.getDate(r).size() - 1));

		}
	}

	@FXML
	void doSimula(ActionEvent event) {

	}

	@FXML
	void initialize() {
		assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

	}

	public void setModel(Model model) {
		this.model = model;
		this.boxRiver.getItems().addAll(this.model.loadAllRiver());

	}
}