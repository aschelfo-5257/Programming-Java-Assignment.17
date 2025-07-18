import java.sql.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SQLClient extends Application {
// Connection to the database
private Connection connection;

// Statement to execute SQL commands
private Statement statement;

// Text area to enter SQL commands
private TextArea tasqlCommand = new TextArea();

// Text area to display results from SQL commands
private TextArea taSQLResult = new TextArea();

// DBC info for a database connection
private TextField tfUsername = new TextField();
private PasswordField pfPassword = new PasswordField();
private ComboBox<String> cboURL = new ComboBox<>();
private ComboBox<String> cboDriver = new ComboBox<>();

private Button btExecuteSQL = new Button("Execute SQL Command");
private Button btClearSQLCommand = new Button("Clear");
private Button btConnectDB = new Button("Connect to Database");
private Button btClearSQLResult = new Button("Clear Result");
private Label lblConnectionStatus = new Label("No connection now");

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
    
        // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane, 670, 400);
        BorderPane borderPane = new BorderPane();
        
        // Top area: DB Connection controls
        GridPane connectionPane = new GridPane();
        connectionPane.setHgap(5);
        connectionPane.setVgap(5);
        connectionPane.add(new Label("JDBC Driver"), 0, 0);
        connectionPane.add(cboDriver, 1, 0);
        connectionPane.add(new Label("Database URL"), 0, 1);
        connectionPane.add(cboURL, 1, 1);
        connectionPane.add(new Label("Username"), 0, 2);
        connectionPane.add(tfUsername, 1, 2);
        connectionPane.add(new Label("Password"), 0, 3);
        connectionPane.add(pfPassword, 1, 3);
        connectionPane.add(btConnectDB, 1, 4);
        
        VBox topBox = new VBox(10, connectionPane, lblConnectionStatus);
        topBox.setAlignment(Pos.CENTER_LEFT);
        borderPane.setTop(topBox);
        
        // Center area: SQL Command input
        tasqlCommand.setWrapText(true);
        ScrollPane commandScroll = new ScrollPane(tasqlCommand);
        commandScroll.setFitToWidth(true);
        commandScroll.setFitToHeight(true);
        commandScroll.setPrefHeight(120);
        borderPane.setCenter(commandScroll);
        
        // Bottom area: Buttons and result output
        HBox buttonBox = new HBox(10, btExecuteSQL, btClearSQLCommand, btClearSQLResult);
        buttonBox.setAlignment(Pos.CENTER);
        
        taSQLResult.setWrapText(true);
        ScrollPane resultScroll = new ScrollPane(taSQLResult);
        resultScroll.setFitToWidth(true);
        resultScroll.setFitToHeight(true);
        
        VBox bottomBox = new VBox(10, buttonBox, resultScroll);
        borderPane.setBottom(bottomBox);
        }
        
          /** Execute SQL commands */
          private void executeSQL() {
          if (connection == null) {
          taSQLResult.setText("Please connect to a database first");
          return;
          }
          else {
          String sqlCommands = tasqlCommand.getText().trim();
          String[] commands = sqlCommands.replace('\n', ' ').split(";");
          
          for (String aCommand: commands) {
          if (aCommand.trim().toUpperCase().startsWith("SELECT")) {
          processSQLSelect(aCommand);
          }
          else {
          processSQLNonSelect(aCommand);
        }
      }
    }
  }
}
