/*
 * 
 * This is the dialog for Employee search by ID
 * 
 * */

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class SearchByIdDialog extends EmployeeSearchDialog {
	// constructor for SearchByIdDialog 
	public SearchByIdDialog(EmployeeDetails parent) {
		super(parent);
		setTitle("Search by Id");
		setSearchCriterionLabel("Id");
	}// end SearchByIdDialog
	@Override
	protected void performSearch() {
		try {
			Double.parseDouble(searchField.getText());
			this.employeeDetails.searchByIdField.setText(searchField.getText());
			// search Employee by ID
			this.employeeDetails.searchEmployeeById();
			dispose();// dispose dialog
		}// end try
		catch (NumberFormatException num) {
			// display message and set colour to text field if entry is wrong
			searchField.setBackground(new Color(255, 150, 150));
			JOptionPane.showMessageDialog(null, "Wrong ID format!");
		}
	}
}// end class searchByIdDialog
