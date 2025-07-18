/** Execute SQL SELECT commands */
public class processSQLSelect(String sqlCommand) {
  try {
  // Get a new statement for the current connection
  statement = connection.createStatement();
  
  // Execute a SELECT SQL command
  ResultSet resultSet = statement.executeQuery(sqlCommand);
  
  // Find the number of columns in the result set
  int columnCount = resultSet.getMetaData().getColumnCount();
  String row = "";
  
  // Display column names
  for (int i = 1; i <= columnCount; i++) {
  row += resultSet.getMetaData().getColumnName(i) + "\t";
  }
  
  taSQLResult.appendText(row + '\n');
  
  while (resultSet.next()) {
  // Reset row to empty
  row = "";
  
  for (int i = 1; i <= columnCount; i++) {
  // A non-String column is converted to a string
  row += resultSet.getString(i) + "\t";
  }
  
  taSQLResult.appendText(row + '\n');
  }
  }
  catch (SQLException ex) {
  taSQLResult.setText(ex.toString());
  }
}
