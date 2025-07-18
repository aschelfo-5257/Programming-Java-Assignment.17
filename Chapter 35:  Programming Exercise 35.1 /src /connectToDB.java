public class connectToDB() {
// Get database information from the user input
String driver = cboDriver.getSelectionModel().getSelectedItem();
String url = cboURL.getSelectionModel().getSelectedItem();
String username = tfUsername.getText().trim();
String password = pfPassword.getText().trim();

// Connection to the database
try {
Class.forName(driver);
connection = DriverManager.getConnection(
url, username, password);
lblConnectionStatus.setText("Connected to " + url);
}
catch (java.lang.Exception ex) {
ex.printStackTrace();
}
}
