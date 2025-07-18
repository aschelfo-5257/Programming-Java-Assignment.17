private void connectToDB() {
    String driver = cboDriver.getValue();
    String url = cboURL.getValue();
    String username = tfUsername.getText();
    String password = pfPassword.getText();

    boolean connected = dbManager.connect(driver, url, username, password);
    lblConnectionStatus.setText(
        connected ? "Connected to " + url : "Failed to connect to database."
    );
}
