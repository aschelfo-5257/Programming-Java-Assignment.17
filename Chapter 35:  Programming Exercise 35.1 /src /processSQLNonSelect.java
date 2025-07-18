/** Execute SQL DDL and data modification commands (e.g., INSERT, UPDATE, DELETE) */
private void processSQLNonSelect(String sqlCommand) {
    try {
        // Get a new statement for the current connection
        statement = connection.createStatement();

        // Execute a non-SELECT SQL command
        int affectedRows = statement.executeUpdate(sqlCommand);

        taSQLResult.setText("SQL command executed successfully. Rows affected: " + affectedRows);
    } catch (SQLException ex) {
        taSQLResult.setText("SQL Error: " + ex.getMessage());
    }
}
