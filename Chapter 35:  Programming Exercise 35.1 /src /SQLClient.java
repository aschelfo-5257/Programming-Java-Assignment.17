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
        primaryStage.setTitle("SQLClient"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage.
        
        btConnectDB.setOnAction(e -> connectToDB());
        btExecuteSQL.setOnAction(e -> executeSQL());
        btClearSQLCommand.setOnAction(e -> tasqlCommand.setText(null));
        btClearSQLResult.setOnAction(e -> taSQLResult.setText(null));
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
