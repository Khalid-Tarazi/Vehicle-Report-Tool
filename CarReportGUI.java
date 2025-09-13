import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarReportGUI extends JFrame {
    private final JTextField nameTextField;
    private final JTextField yearTextField;
    private final JTextField vinTextField;
    private final JComboBox<String> assessmentComboBox;
    private final JRadioButton yesRadioButton;
    private final JRadioButton noRadioButton;

    public CarReportGUI() {
        setTitle("🚗 Vehicle Report Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null);

        // Use BoxLayout for better spacing
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // padding
        add(mainPanel);

        // === Vehicle Info Section ===
        JPanel vehiclePanel = new JPanel(new GridLayout(3, 2, 10, 10));
        vehiclePanel.setBorder(BorderFactory.createTitledBorder(" Vehicle Information "));

        vehiclePanel.add(new JLabel("Name:"));
        nameTextField = new JTextField();
        vehiclePanel.add(nameTextField);

        vehiclePanel.add(new JLabel("Year:"));
        yearTextField = new JTextField();
        yearTextField.setToolTipText("Enter only digits, e.g., 2022");
        vehiclePanel.add(yearTextField);

        vehiclePanel.add(new JLabel("VIN:"));
        vinTextField = new JTextField("17 character");
        vehiclePanel.add(vinTextField);

        mainPanel.add(vehiclePanel);

        // === Assessment Section ===
        JPanel assessmentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        assessmentPanel.setBorder(BorderFactory.createTitledBorder(" Assessment "));
        assessmentPanel.add(new JLabel("Rating (1–5): "));
        String[] assessments = {"1", "2", "3", "4", "5"};
        assessmentComboBox = new JComboBox<>(assessments);
        assessmentPanel.add(assessmentComboBox);

        mainPanel.add(assessmentPanel);

        // === Issues Section ===
        JPanel issuesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        issuesPanel.setBorder(BorderFactory.createTitledBorder(" Issues (Damage, Flood, Fire, etc.) "));

        ButtonGroup issuesButtonGroup = new ButtonGroup();
        yesRadioButton = new JRadioButton("Yes");
        noRadioButton = new JRadioButton("No", true); // default No selected
        issuesButtonGroup.add(yesRadioButton);
        issuesButtonGroup.add(noRadioButton);

        issuesPanel.add(yesRadioButton);
        issuesPanel.add(noRadioButton);

        mainPanel.add(issuesPanel);

        // === Buttons Section ===
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton addVehicleButton = new JButton("➕ Add Vehicle");
        JButton showReportButton = new JButton("📄 Show Report");

        // Button sizes
        addVehicleButton.setPreferredSize(new Dimension(130, 35));
        showReportButton.setPreferredSize(new Dimension(130, 35));

        // Action Listeners
        addVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addVehicle();
            }
        });

        showReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateReport();
                clearForm();
            }
        });

        buttonPanel.add(addVehicleButton);
        buttonPanel.add(showReportButton);
        mainPanel.add(buttonPanel);

        setVisible(true);
    }

    private void addVehicle() {
        String yearInput = yearTextField.getText();
        boolean validYear = yearInput.matches("\\d{4}"); // simple regex for 4-digit year

        if (validYear) {
            carReport report = new carReport();
            report.setVehicleName(nameTextField.getText());
            report.setVehicleYear(Integer.parseInt(yearInput));
            report.setVehicleVIN(vinTextField.getText());
            report.setAssessment(Integer.parseInt((String) assessmentComboBox.getSelectedItem()));
            report.setIssues(yesRadioButton.isSelected());
            JOptionPane.showMessageDialog(this, "✅ Vehicle Added Successfully!",
                    "Vehicle Report", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "❌ Invalid year. Please enter a valid 4-digit year.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generateReport() {
        try {
            carReport report = new carReport();
            report.setVehicleName(nameTextField.getText());
            report.setVehicleYear(Integer.parseInt(yearTextField.getText()));
            report.setVehicleVIN(vinTextField.getText());
            report.setAssessment(Integer.parseInt((String) assessmentComboBox.getSelectedItem()));
            report.setIssues(yesRadioButton.isSelected());

            report.generateReport();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "⚠ Please enter a valid numeric year.",
                    "Input Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void clearForm() {
        nameTextField.setText("");
        yearTextField.setText("");
        vinTextField.setText("");
        assessmentComboBox.setSelectedIndex(0);
        noRadioButton.setSelected(true); // reset to default
    }
}
