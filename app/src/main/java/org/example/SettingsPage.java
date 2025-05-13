package org.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SettingsPage {

    public static Scene createSettingsScene(Stage primaryStage) {
        BorderPane settingsRoot = new BorderPane();
        settingsRoot.setStyle("-fx-background-color: #1e1e1e;");

        Label settingsLabel = new Label("⚙ إعدادات البرنامج");
        settingsLabel.setFont(new Font("SansSerif", 24));
        settingsLabel.setTextFill(Color.WHITE);
        settingsLabel.setPadding(new Insets(10));

        Label nameLabel = new Label("اسم المستخدم:");
        nameLabel.setTextFill(Color.WHITE);
        TextField nameField = new TextField();
        nameField.setPromptText("اكتب اسمك...");
        nameField.setStyle("-fx-background-color: #2e2e2e; -fx-text-fill: white;");

        Label themeLabel = new Label("اختيار الثيم:");
        themeLabel.setTextFill(Color.WHITE);
        ComboBox<String> themeBox = new ComboBox<>();
        themeBox.getItems().addAll("داكن", "فاتح");
        themeBox.setValue("داكن");

        Button collectButton = new Button("🔍 البحث التجميعي");
        collectButton.setStyle("-fx-background-color: #6c63ff; -fx-text-fill: white;");
        collectButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "سيتم تنفيذ البحث لاحقًا.");
            alert.showAndWait();
        });

        Button saveButton = new Button("💾 حفظ");
        saveButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
        saveButton.setOnAction(e -> {
            String theme = themeBox.getValue();
            if ("فاتح".equals(theme)) {
                settingsRoot.setStyle("-fx-background-color: #ffffff;");
                settingsLabel.setTextFill(Color.BLACK);
                nameLabel.setTextFill(Color.BLACK);
                themeLabel.setTextFill(Color.BLACK);
                nameField.setStyle("-fx-background-color: white; -fx-text-fill: black;");
            } else {
                settingsRoot.setStyle("-fx-background-color: #1e1e1e;");
                settingsLabel.setTextFill(Color.WHITE);
                nameLabel.setTextFill(Color.WHITE);
                themeLabel.setTextFill(Color.WHITE);
                nameField.setStyle("-fx-background-color: #2e2e2e; -fx-text-fill: white;");
            }
        });

        Button backButton = new Button("🔙 رجوع");
        backButton.setStyle("-fx-background-color: #555; -fx-text-fill: white;");
        backButton.setOnAction(e -> {
            ChatUIFX mainApp = new ChatUIFX();
            mainApp.start(primaryStage);
        });

        VBox content = new VBox(15,
            settingsLabel,
            nameLabel, nameField,
            themeLabel, themeBox,
            collectButton,
            saveButton,
            backButton
        );
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(30));

        settingsRoot.setCenter(content);
        return new Scene(settingsRoot, 900, 700);
    }
}
