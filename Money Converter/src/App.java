import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application 
{
    // Conversion rates for currency USD, JPY, AUD, EUR, CAD, and GBP
    final double USD_JPY_RATE = 131.14;
    final double USD_AUD_RATE = 1.49;
    final double USD_EUR_RATE = 0.92;
    final double USD_CAD_RATE = 1.35;
    final double USD_GBP_RATE = 0.80;

    final double JPY_USD_RATE = 0.0076;
    final double JPY_AUD_RATE = 0.011;
    final double JPY_EUR_RATE = 0.007;
    final double JPY_CAD_RATE = 0.010;
    final double JPY_GBP_RATE = 0.0061;

    final double AUD_USD_RATE = 0.67;
    final double AUD_JPY_RATE = 88.01;
    final double AUD_EUR_RATE = 0.62;
    final double AUD_CAD_RATE = 0.90;
    final double AUD_GBP_RATE = 0.54;

    final double EUR_USD_RATE = 1.09;
    final double EUR_JPY_RATE = 142.89;
    final double EUR_AUD_RATE = 1.62;
    final double EUR_CAD_RATE = 1.47;
    final double EUR_GBP_RATE = 0.87;

    final double CAD_USD_RATE = 0.74;
    final double CAD_JPY_RATE = 97.39;
    final double CAD_AUD_RATE = 1.11;
    final double CAD_EUR_RATE = 0.68;
    final double CAD_GBP_RATE = 0.60;

    final double GBP_USD_RATE = 1.25;
    final double GBP_JPY_RATE = 163.28;
    final double GBP_AUD_RATE = 1.86;
    final double GBP_EUR_RATE = 1.14;
    final double GBP_CAD_RATE = 1.68;

    // GUI components
    ChoiceBox<String> fromBox = new ChoiceBox<>();
    ChoiceBox<String> toBox = new ChoiceBox<>();
    TextField inputField = new TextField();
    Label resultLabel = new Label();

    @Override
public void start(Stage primaryStage) {
    // Set up GUI components
    Label welcomeLabel = new Label("Welcome to Money Converter!");
    welcomeLabel.setFont(new Font("Arial", 15));
    Label fromLabel = new Label("From:");
    Label toLabel = new Label("To:");
    Button convertButton = new Button("Convert");
    convertButton.setOnAction(e -> convert());
    resultLabel.setAlignment(Pos.CENTER_RIGHT);
    resultLabel.setPrefWidth(200);

    // Set up choice boxes
    fromBox.getItems().addAll("US Dollar", "Japanese Yen", "Australian Dollar", "Euro", "Canadian Dollar", "British Pound");
    fromBox.setValue("US Dollar");
    toBox.getItems().addAll("Japanese Yen", "Australian Dollar", "Euro", "Canadian Dollar", "British Pound", "US Dollar");
    toBox.setValue("Japanese Yen");

    // Set up flag images
    ImageView fromFlag = new ImageView();
    ImageView toFlag = new ImageView();
    fromBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
        fromFlag.setImage(new Image(getFlagUrl(newValue)));
    });
    fromFlag.setImage(new Image(getFlagUrl(fromBox.getValue())));
    toBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
        toFlag.setImage(new Image(getFlagUrl(newValue)));
    });
    toFlag.setImage(new Image(getFlagUrl(toBox.getValue())));

    // Set up grid pane layout
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setAlignment(Pos.CENTER);
    grid.add(welcomeLabel, 1, 0);
    grid.add(fromLabel, 0, 1);
    grid.add(fromBox, 1, 1);
    grid.add(fromFlag, 2, 1);
    grid.add(toLabel, 0, 2);
    grid.add(toBox, 1, 2);
    grid.add(toFlag, 2, 2);
    grid.add(inputField, 0, 3, 3, 1);
    grid.add(convertButton, 0, 4);
    grid.add(resultLabel, 2, 4);

    // Set up scene and show stage
    Scene scene = new Scene(grid, 500, 400);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Money Converter");
    primaryStage.show();
}

private String getFlagUrl(String country) {
    switch (country) {
        case "US Dollar":
            return "https://flagcdn.com/w80/us.png";
        case "Japanese Yen":
            return "https://flagcdn.com/w80/jp.png";
        case "Australian Dollar":
            return "https://flagcdn.com/w80/au.png";
        case "Euro":
            return "https://flagcdn.com/w80/eu.png";
        case "Canadian Dollar":
            return "https://flagcdn.com/w80/ca.png";
        case "British Pound":
            return "https://flagcdn.com/w80/gb.png";
        default:
            return "";
    }
}


// Convert class
private void convert() 
{
    double input = Double.parseDouble(inputField.getText());
    double fromValue = 0.0;
    switch (fromBox.getValue()) 
    {
        case "US Dollar":
            fromValue = input;
            break;
        case "Japanese Yen":
            fromValue = input * JPY_USD_RATE;
            break;
        case "Australian Dollar":
            fromValue = input * AUD_USD_RATE;
            break;
        case "Euro":
            fromValue = input * EUR_USD_RATE;
            break;
        case "Canadian Dollar":
            fromValue = input * CAD_USD_RATE;
            break;
        case "British Pound":
            fromValue = input * GBP_USD_RATE;
            break;
    }

    // Convert from US Dollar to selected currency example
    double toValue = 0.0;
    switch (toBox.getValue()) 
    {
        case "US Dollar":
            toValue = fromValue;
            break;
        case "Japanese Yen":
            toValue = fromValue / JPY_USD_RATE;
            break;
        case "Australian Dollar":
            toValue = fromValue / AUD_USD_RATE;
            break;
        case "Euro":
            toValue = fromValue / EUR_USD_RATE;
            break;
        case "Canadian Dollar":
            toValue = fromValue / CAD_USD_RATE;
            break;
        case "British Pound":
            toValue = fromValue / GBP_USD_RATE;
            break;
    }

    resultLabel.setText(String.format("%.2f", toValue));
}

public static void main(String[] args) 
{
    launch(args);
}

}