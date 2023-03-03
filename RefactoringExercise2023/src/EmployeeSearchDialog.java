import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A dialog for searching employees by a certain criterion.
 */
public abstract class EmployeeSearchDialog extends JDialog implements ActionListener {
    protected EmployeeDetails employeeDetails;
    protected JButton searchButton, cancelButton;
    protected JTextField searchField;
    protected String searchCriterionLabel;
    protected String dialogTitle;

    /**
     * Creates a new instance of the search dialog.
     * @param employeeDetails the parent window of the dialog
     */
    public EmployeeSearchDialog(EmployeeDetails employeeDetails) {
        super(employeeDetails, true);
        this.employeeDetails = employeeDetails;
        setModal(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JScrollPane scrollPane = new JScrollPane(createSearchPane());
        setContentPane(scrollPane);
        getRootPane().setDefaultButton(searchButton);
        setSize(500, 190);
        setLocation(350, 250);
        setVisible(true);
    }

    /**
     * Creates the search panel.
     * @return the search panel
     */
    public Container createSearchPane(){
        JPanel searchPanel = new JPanel(new GridLayout(3, 1));
        JPanel textPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JLabel searchLabel;

        searchPanel.add(new JLabel("Search by " + getSearchCriterionLabel()));

        textPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        textPanel.add(searchLabel = new JLabel("Enter " + getSearchCriterionLabel() +":"));
        searchLabel.setFont(this.employeeDetails.font1);
        textPanel.add(searchField = new JTextField(20));
        searchField.setFont(this.employeeDetails.font1);
        searchField.setDocument(new JTextFieldLimit(20));

        buttonPanel.add(searchButton = new JButton("Search"));
        searchButton.addActionListener(this);
        searchButton.requestFocus();

        buttonPanel.add(cancelButton = new JButton("Cancel"));
        cancelButton.addActionListener(this);

        searchPanel.add(textPanel);
        searchPanel.add(buttonPanel);

        return searchPanel;
    }

    /**
     * Performs the search action.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            performSearch();
        } else if (e.getSource() == cancelButton) {
            dispose();
        }
    }

    /**
     * Performs the search action.
     */
    protected abstract void performSearch();

    /**
     * Gets the label for the search criterion.
     * @return the label for the search criterion
     */
    public String getSearchCriterionLabel() {
        return searchCriterionLabel;
    }

    /**
     * Sets the label for the search criterion.
     * @param searchCriterionLabel the label for the search criterion
     */
    public void setSearchCriterionLabel(String searchCriterionLabel) {
        this.searchCriterionLabel = searchCriterionLabel;
    }

    /**
     * Gets the title of the dialog.
     * @return the title of the dialog
     */
    @Override
    public String getTitle() {
        return dialogTitle;
    }

    /**
     * Sets the title of the dialog.
     * @param dialogTitle the title of the dialog
     */
    @Override
    public void setTitle(String dialogTitle) {
        this.dialogTitle = dialogTitle;
    }
}
