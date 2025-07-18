private void executeSQL() {
    if (!dbManager.isConnected()) {
        taSQLResult.setText("Please connect to a database first");
        return;
    }

    String sqlCommands = tasqlCommand.getText().trim();
    String[] commands = sqlCommands.replace('\n', ' ').split(";");

    StringBuilder result = new StringBuilder();

    for (String aCommand : commands) {
        if (aCommand.trim().isEmpty()) continue;

        try {
            if (aCommand.trim().toUpperCase().startsWith("SELECT")) {
                result.append(dbManager.executeSelect(aCommand)).append("\n");
            } else {
                int count = dbManager.executeUpdate(aCommand);
                result.append(count).append(" row(s) affected.\n");
            }
        } catch (SQLException e) {
            result.append("SQL Error: ").append(e.getMessage()).append("\n");
        }
    }

    taSQLResult.setText(result.toString());
}
