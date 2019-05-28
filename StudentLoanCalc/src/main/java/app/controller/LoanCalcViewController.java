package app.controller;

import app.StudentCalc;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;

import org.apache.poi.ss.formula.functions.FinanceLib;

public class LoanCalcViewController implements Initializable   {

	private StudentCalc SC = null;
	
	@FXML
	private TextField LoanAmount;
	
	@FXML
	private Label lblTotalPayemnts;
	
	@FXML
	private DatePicker PaymentStartDate;
	
	@FXML
	private TextField totalPayments;
	
	@FXML
	private TextField interestRate;
	
	@FXML
	private TextField term;
	
	@FXML
	private TextField additionalPayment;
	
	@FXML
	private TextField totalInterest;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void setMainApp(StudentCalc sc) {
		this.SC = sc;
	}
	
	/**
	 * btnCalcLoan - Fire this event when the button clicks
	 * 
	 * @version 1.0
	 * @param event
	 */
	@FXML
	private void btnCalcLoan(ActionEvent event) {
		
		
		double dLoanAmount = Double.parseDouble(LoanAmount.getText());
		double dInterestRate = Double.parseDouble(interestRate.getText())/12;
		double dTerm = Double.parseDouble(term.getText())*12;
		double dAdditionalPayment = Double.parseDouble(additionalPayment.getText());
		double PMT = Math.abs(FinanceLib.pmt(dInterestRate, dTerm, dLoanAmount, 0, false));

		
		if (dAdditionalPayment == 0) {
			double amount = dTerm * 12;
			double positive = PMT* amount-dLoanAmount;
			totalPayments.setText(Double.toString(amount));
			totalInterest.setText(Double.toString(positive));
		}
		
		else {
			double interest = (PMT-dAdditionalPayment)*dTerm;
			double pay = interest/(PMT);
			interest = interest - dLoanAmount;
			totalPayments.setText(Double.toString(pay));
			totalInterest.setText(Double.toString(interest));
		}
	}
}
