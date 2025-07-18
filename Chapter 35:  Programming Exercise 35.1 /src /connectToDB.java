public class processSQLSelect(String sql) {
  try {
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        StringBuilder result = new StringBuilder();

        // Column headers
        for (int i = 1; i <= columnCount; i++) {
            result.append(metaData.getColumnName(i)).append("\t");
        }
        result.append("\n");

        // Data rows
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                result.append(resultSet.getString(i)).append("\t");
            }
            result.append("\n");
        }
        taSQLResult.setText(result.toString());
    } catch (SQLException ex) {
        taSQLResult.setText("SQL Error: " + ex.getMessage());
    }
}
