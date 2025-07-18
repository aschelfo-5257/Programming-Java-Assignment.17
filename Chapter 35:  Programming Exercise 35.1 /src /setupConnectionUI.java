public class setupConnectionUI {
    @Override // Override the start method in the Application class
      public void start(Stage primaryStage) {
      cboURL.getItems().addAll(FXCollections.observableArrayList(
      "jdbc:mysql://localhost/javabook",
      "jdbc:mysql://liang.armstrong.edu/javabook",
      "jdbc:odbc:exampleMDBDataSource",
      "jdbc:oracle:thin:@liang.armstrong.edu:1521:orcl"));
      cboURL.getSelectionModel().selectFirst();
      
      cboDriver.getItems().addAll(FXCollections.observableArrayList(
      "com.mysql.jdbc.Driver", "sun.jdbc.odbc.dbcOdbcDriver",
      "oracle.jdbc.driver.OracleDriver"));
      cboDriver.getSelectionModel().selectFirst();
      
      // Create UI for connecting to the database
      GridPane gridPane = new GridPane();
      gridPane.add(cboURL, 1, 0);
      gridPane.add(cboDriver, 1, 1);
      gridPane.add(tfUsername, 1, 2);
      gridPane.add(pfPassword, 1, 3);
      gridPane.add(new Label("JDBC Driver"), 0, 0);
      gridPane.add(new Label("Database URL"), 0, 1);
      gridPane.add(new Label("Username"), 0, 2);
      gridPane.add(new Label("Password"), 0, 3);
      
      HBox hBoxConnection = new HBox();
      hBoxConnection.getChildren().addAll(
      lblConnectionStatus, btConnectDB);
      hBoxConnection.setAlignment(Pos.CENTER_RIGHT);
      
      VBox vBoxConnection = new VBox(5);
      vBoxConnection.getChildren().addAll(
      new Label("Enter Database Information"),
      M35_LIAN0182_11_SE_C35.indd 3 5/23/17 5:59 PM
      35-4 Chapter 35 Advanced Java Database Programming
       gridPane, hBoxConnection);
      
      gridPane.setStyle("-fx-border-color: black;");
      
      HBox hBoxSQLCommand = new HBox(5);
      hBoxSQLCommand.getChildren().addAll( btClearSQLCommand, btExecuteSQL);
      hBoxSQLCommand.setAlignment(Pos.CENTER_RIGHT);
      
      BorderPane borderPaneSqlCommand = new BorderPane();
      borderPaneSqlCommand.setTop(
      new Label("Enter an SQL Command"));
      borderPaneSqlCommand.setCenter(
      new ScrollPane(tasqlCommand));
      borderPaneSqlCommand.setBottom(
      hBoxSQLCommand);
      
      HBox hBoxConnectionCommand = new HBox(10);
      hBoxConnectionCommand.getChildren().addAll(
      vBoxConnection, borderPaneSqlCommand);
      
      BorderPane borderPaneExecutionResult = new BorderPane();
      borderPaneExecutionResult.setTop(
      new Label("SQL Execution Result"));
      borderPaneExecutionResult.setCenter(taSQLResult);
      borderPaneExecutionResult.setBottom(btClearSQLResult);
      
      BorderPane borderPane = new BorderPane();
      borderPane.setTop(hBoxConnectionCommand);
      borderPane.setCenter(borderPaneExecutionResult);
}
