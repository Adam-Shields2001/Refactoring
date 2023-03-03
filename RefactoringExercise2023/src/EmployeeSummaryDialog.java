/*
 * 
 * This is the summary dialog for displaying all Employee details
 * 
 * */

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

public class EmployeeSummaryDialog extends JDialog implements ActionListener {
	// vector with all Employees details
	Vector<Object> allEmployees;
	JButton back;
	
	public EmployeeSummaryDialog(Vector<Object> allEmployees) {
		setTitle("Employee Summary");
		setModal(true);
		this.allEmployees = allEmployees;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane(summaryPane());
		setContentPane(scrollPane);

		setSize(850, 500);
		setLocation(350, 250);
		setVisible(true);

	}
	
	private JScrollPane createScrollPane(JTable table) {
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setEnabled(false);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    return scrollPane;
	}
	
	//initialise container
	public Container summaryPane() {
	    JPanel summaryDialog = new JPanel(new MigLayout());
	    JPanel buttonPanel = createButtonPanel();
	    JTable employeeTable = createEmployeeTable();
	    JScrollPane scrollPane = createScrollPane(employeeTable);

	    summaryDialog.add(buttonPanel, "growx, pushx, wrap");
	    summaryDialog.add(scrollPane, "growx, pushx, wrap");
	    scrollPane.setBorder(BorderFactory.createTitledBorder("Employee Details"));

	    return summaryDialog;
	}

	private JPanel createButtonPanel() {
	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	    JButton back = new JButton("Back");
	    back.addActionListener(this);
	    back.setToolTipText("Return to main screen");
	    buttonPanel.add(back);
	    return buttonPanel;
	}

	private JTable createEmployeeTable() {
	    Vector<String> header = createTableHeaders();
	    int[] colWidth = createColumnWidths();
	    DefaultTableCellRenderer centerRenderer = createCenterRenderer();
	    DefaultTableCellRenderer leftRenderer = createLeftRenderer();
	    DefaultTableModel tableModel = createTableModel(header);
	    JTable employeeTable = new JTable(tableModel);
	    setColumnWidths(employeeTable, header, colWidth);
	    return employeeTable;
	}

	private Vector<String> createTableHeaders() {
		// header names
	    String[] headerName = {"ID", "PPS Number", "Surname", "First Name", "Gender", "Department", "Salary", "Full Time"};
	    return new Vector<>(Arrays.asList(headerName));
	}

	private int[] createColumnWidths() {
		// column widths
	    return new int[]{15, 100, 120, 120, 50, 120, 80, 80};
	}

	private DefaultTableCellRenderer createCenterRenderer() {
		// column center alignment
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
	    return centerRenderer;
	}

	private DefaultTableCellRenderer createLeftRenderer() {
		// column left alignment 
	    DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
	    leftRenderer.setHorizontalAlignment(JLabel.LEFT);
	    return leftRenderer;
	}

	private DefaultTableModel createTableModel(Vector<String> header) {
		// construct table and choose table model for each column
	    return new DefaultTableModel() {
	        @Override
	        public Class<?> getColumnClass(int columnIndex) {
	            switch (columnIndex) {
	                case 0:
	                    return Integer.class;
	                case 4:
	                    return Character.class;
	                case 6:
	                    return Double.class;
	                case 7:
	                    return Boolean.class;
	                default:
	                    return String.class;
	            }// end switch
			}// end getColumnClass
	    };
	}


	private void setColumnWidths(JTable employeeTable, Vector<String> header, int[] colWidth) {
		// add headers
		for (int i = 0; i < header.size(); i++) {
	        employeeTable.getColumn(header.get(i)).setMinWidth(colWidth[i]);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back){
			dispose();
		}

	}
	// format for salary column
	static class DecimalFormatRenderer extends DefaultTableCellRenderer {
		 private static final DecimalFormat format = new DecimalFormat(
		 "\u20ac ###,###,##0.00" );

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {

			Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			 JLabel label = (JLabel) c;
			 label.setHorizontalAlignment(JLabel.RIGHT);
			 // format salary column
			value = format.format((Number) value);

			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}// end getTableCellRendererComponent
	}// DefaultTableCellRenderer
}// end class EmployeeSummaryDialog
