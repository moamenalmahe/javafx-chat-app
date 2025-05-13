package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ChatUIFX extends Application {

    private VBox chatBox;
    private Label welcomeLabel;
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        stage.setTitle("Future Modern Chat");

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1e1e1e;");

        // ✅ الشريط الجانبي
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(15));
        sidebar.setStyle("-fx-background-color: rgba(43,43,43,0.6); -fx-background-radius: 15;");
        sidebar.setPrefWidth(80);
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.setEffect(new javafx.scene.effect.DropShadow(10, Color.BLACK));

        Button settingsButton = new Button("⚙");
        settingsButton.setStyle("-fx-background-color: transparent;-fx-text-fill: white;-fx-font-size: 24px;-fx-cursor: hand;");
        settingsButton.setTooltip(new Tooltip("الإعدادات"));
        settingsButton.setOnAction(e -> openSettingsScene());

        Button helpButton = new Button("❓");
        helpButton.setStyle("-fx-background-color: transparent;-fx-text-fill: white;-fx-font-size: 24px;-fx-cursor: hand;");
        helpButton.setTooltip(new Tooltip("المساعدة"));
        helpButton.setOnAction(e -> new Alert(Alert.AlertType.INFORMATION, "لم يتم تنفيذ المساعدة بعد.").showAndWait());

        sidebar.getChildren().addAll(settingsButton, helpButton);
        root.setLeft(sidebar);

        // ✅ منطقة الرسائل
        chatBox = new VBox(10);
        chatBox.setPadding(new Insets(15));
        chatBox.setStyle("-fx-background-color: transparent;");
        ScrollPane scrollPane = new ScrollPane(chatBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        root.setCenter(scrollPane);

        // ✅ رسالة ترحيب
        welcomeLabel = new Label("Future Modern School Chat");
        welcomeLabel.setFont(new Font("SansSerif", 24));
        welcomeLabel.setTextFill(Color.web("#bbbbbb"));
        welcomeLabel.setPadding(new Insets(30, 0, 30, 0));
        welcomeLabel.setAlignment(Pos.CENTER);
        welcomeLabel.setStyle("-fx-text-fill: linear-gradient(to right, #f3ec78, #af4261); -fx-font-weight: bold;");
        chatBox.getChildren().add(welcomeLabel);

        // ✅ منطقة الإدخال
        TextField inputField = new TextField();
        inputField.setFont(new Font("SansSerif", 16));
        inputField.setPromptText("اكتب رسالتك...");
        inputField.setStyle("-fx-background-color: #2e2e2e; -fx-text-fill: #ffffff; -fx-border-radius: 10;");
        inputField.setPrefHeight(40);

        Button sendButton = new Button("إرسال");
        sendButton.setFont(new Font("SansSerif", 16));
        sendButton.setStyle("-fx-background-color: linear-gradient(to right, #ff416c, #ff4b2b); -fx-text-fill: white;");
        sendButton.setPrefHeight(40);
        sendButton.setOnAction(e -> sendMessage(inputField));
        inputField.setOnAction(e -> sendMessage(inputField));

        HBox inputArea = new HBox(10, inputField, sendButton);
        inputArea.setPadding(new Insets(10));
        inputArea.setAlignment(Pos.CENTER);
        root.setBottom(inputArea);

        // ✅ إعداد المشهد
        Scene scene = new Scene(root, 900, 700);
        stage.setScene(scene);
        stage.show();
    }

    private void sendMessage(TextField inputField) {
        String message = inputField.getText().trim();
        if (!message.isEmpty()) {
            if (welcomeLabel != null) {
                chatBox.getChildren().remove(welcomeLabel);
                welcomeLabel = null;
            }

            addMessage(message, true);
            inputField.clear();

            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    String reply = AIService.ask(message);
                    javafx.application.Platform.runLater(() -> addMessage(reply, false));
                } catch (InterruptedException e) {
                    javafx.application.Platform.runLater(() -> addMessage("⚠️ حدث خطأ أثناء الاتصال.", false));
                }
            }).start();
        }
    }

    private void addMessage(String text, boolean isUser) {
        Label msg = new Label(text);
        msg.setWrapText(true);
        msg.setFont(new Font("SansSerif", 14));
        msg.setPadding(new Insets(10));
        msg.maxWidthProperty().bind(chatBox.widthProperty().multiply(0.7));
        msg.setStyle("-fx-background-color: " + (isUser ? "#3c3f41" : "#2a2d2e") + "; -fx-background-radius: 15; -fx-text-fill: #ffffff;");

        HBox messageContainer = new HBox(msg);
        messageContainer.setPadding(new Insets(5));
        messageContainer.setAlignment(isUser ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        chatBox.getChildren().add(messageContainer);
    }

    private void openSettingsScene() {
        primaryStage.setScene(SettingsPage.createSettingsScene(primaryStage));
    }
}
