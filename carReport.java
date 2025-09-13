import javax.swing.JOptionPane;

public class carReport {
    private String vehicleName;
    private int vehicleYear;
    private String vehicleVIN;
    private int assessment;
    private boolean issues;

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public void setVehicleYear(int vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public void setVehicleVIN(String vin) {
        this.vehicleVIN = vin.toUpperCase();
        if (!validateVin(vin)) {
            JOptionPane.showMessageDialog(null, "Cannot generate report for invalid VIN", "Error Message!", JOptionPane.ERROR_MESSAGE);
            stopProgram();
        }
    }

    public void setAssessment(int assessment) {
        this.assessment = validateAssessment(assessment);
    }

    public void setIssues(boolean issues) {
        this.issues = issues;
    }

    public void generateReport() {
        if (!validateVin(vehicleVIN)) {
            JOptionPane.showMessageDialog(null, "Cannot generate report for invalid VIN", "Error Message!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String report;
        report = "\t\t          *** CARSEER Comprehensive Report ***\n" +
                "\t\t\t\t                       *** Cost 9.99 JOD ***\n" +
                "\t\t\t                *** Report Date: 12/6/2023 ***\n\n" +
                "General information:\n\n" +
                "\t\t Vehicle\t\t\t\t             VIN\n" +
                "\t\t" + vehicleName + "  " + vehicleYear + "\t\t  " + vehicleVIN + "\n\n" +
                "Vehicle assessment " + assessment + " out of 5\n";

        if (issues) {
            report = report + "Vehicle has several issues such as Junks, Fire, Damage, and others.\n";
        } else {
            report = report + "No issues found.\n";
        }

        report = report + "\n\t\t\t                *** End of CARSEER report ***";

        JOptionPane.showMessageDialog(null, report, "Vehicle Report", JOptionPane.INFORMATION_MESSAGE);
    }

    ///// Validation //////
    private boolean validateVin(String vin) {
        return vin.length() == 17 && !vin.contains(" ");
    }

    private void stopProgram() {
        System.exit(0);
    }

    private int validateAssessment(int assessment) {
        if (assessment < 1 || assessment > 5) {
            return 1; // default
        }
        return assessment;
    }
}
