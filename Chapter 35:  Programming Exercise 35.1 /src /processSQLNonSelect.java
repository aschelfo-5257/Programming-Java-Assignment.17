/** Execute SQL DDL, and modification commands */
public class processSQLNonSelect(String sqlCommand) {
try {
// Get a new statement for the current connection
statement = connection.createStatement();

// Execute a non-SELECT SQL command
statement.executeUpdate(sqlCommand);

taSQLResult.setText("SQL command executed");
}
catch (SQLException ex) {
taSQLResult.setText(ex.toString());
}
}
