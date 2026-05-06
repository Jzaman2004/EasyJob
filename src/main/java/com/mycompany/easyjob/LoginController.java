package com.mycompany.easyjob;

import java.util.function.Consumer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class LoginController {

    private final Auth auth;
    private final Runnable onSignUp;
    private final Consumer<User> onSuccess;

    private TextField emailField;
    private PasswordField passwordField;

    public LoginController(Auth auth, Runnable onSignUp, Consumer<User> onSuccess) {
        this.auth      = auth;
        this.onSignUp  = onSignUp;
        this.onSuccess = onSuccess;
    }

    public Parent buildView() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f4f6f9;");

        VBox center = new VBox(buildCard());
        center.setAlignment(Pos.CENTER);
        center.setPadding(new Insets(40, 0, 20, 0));

        root.setCenter(center);
        root.setBottom(buildFooter());
        return root;
    }

    private VBox buildCard() {

        StackPane iconBox = new StackPane();
        iconBox.setStyle("-fx-background-color: #3b5bdb; -fx-background-radius: 14;" +
                         "-fx-min-width: 64; -fx-max-width: 64; -fx-min-height: 64; -fx-max-height: 64;");
        Label icon = new Label("💼");
        icon.setStyle("-fx-font-size: 28px; -fx-text-fill: white;");
        iconBox.getChildren().add(icon);

        Label title = new Label("Welcome Back");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #1a1a2e; -fx-padding: 14 0 4 0;");

        Label subtitle = new Label("Sign in to continue your STEM career journey");
        subtitle.setStyle("-fx-font-size: 13.5px; -fx-text-fill: #6b7280; -fx-padding: 0 0 20 0;");

        Label emailLabel = new Label("Email Address");
        emailLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #374151; -fx-padding: 8 0 4 0;");
        emailField = new TextField();
        emailField.setPromptText("you@example.com");
        emailField.setStyle("-fx-pref-height: 42px; -fx-background-color: #f9fafb; -fx-border-color: #d1d5db;" +
                            "-fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 0 12 0 12; -fx-font-size: 14px;");

        Label passwordLabel = new Label("Password");
        passwordLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #374151; -fx-padding: 8 0 4 0;");
        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-pref-height: 42px; -fx-background-color: #f9fafb; -fx-border-color: #d1d5db;" +
                               "-fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 0 12 0 12; -fx-font-size: 14px;");

        // Demo credentials hint
        Label hint = new Label("Demo: alice@example.com / password123");
        hint.setStyle("-fx-font-size: 11px; -fx-text-fill: #9ca3af; -fx-padding: 2 0 0 0;");

        Button signInBtn = new Button("Sign In");
        signInBtn.setMaxWidth(Double.MAX_VALUE);
        signInBtn.setStyle("-fx-background-color: #3b5bdb; -fx-text-fill: white; -fx-font-size: 15px;" +
                           "-fx-font-weight: bold; -fx-pref-height: 46px; -fx-background-radius: 8; -fx-cursor: hand;");
        signInBtn.setOnAction(e -> handleSignIn());
        // Allow Enter key in password field to trigger login
        passwordField.setOnAction(e -> handleSignIn());

        Label noAccount = new Label("Don't have an account?");
        noAccount.setStyle("-fx-text-fill: #6b7280; -fx-font-size: 13px;");
        Label signupLink = new Label("Sign up free");
        signupLink.setStyle("-fx-text-fill: #3b5bdb; -fx-font-size: 13px; -fx-cursor: hand; -fx-font-weight: bold;");
        signupLink.setOnMouseClicked(e -> onSignUp.run());
        HBox signupRow = new HBox(4, noAccount, signupLink);
        signupRow.setAlignment(Pos.CENTER);
        signupRow.setPadding(new Insets(8, 0, 0, 0));

        VBox card = new VBox(6);
        card.setAlignment(Pos.CENTER);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 16;" +
                      "-fx-padding: 40 50 40 50; -fx-min-width: 420; -fx-max-width: 460;");
        card.setEffect(new DropShadow(20, 0, 4, Color.rgb(0, 0, 0, 0.10)));
        card.getChildren().addAll(
            iconBox, title, subtitle,
            emailLabel, emailField,
            passwordLabel, passwordField, hint,
            signInBtn, signupRow
        );

        return card;
    }

    private HBox buildFooter() {
        HBox footer = new HBox(30);
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(16, 0, 16, 0));
        footer.setStyle("-fx-background-color: #f4f6f9; -fx-border-color: #e5e7eb; -fx-border-width: 1 0 0 0;");

        String linkStyle = "-fx-text-fill: #6b7280; -fx-font-size: 12px; -fx-cursor: hand;";
        for (String text : new String[]{"About Us", "Privacy Policy", "Terms of Service", "Contact", "Help Center"}) {
            Label lbl = new Label(text);
            lbl.setStyle(linkStyle);
            footer.getChildren().add(lbl);
        }
        return footer;
    }

    private void handleSignIn() {
        String email    = emailField.getText().trim();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Please enter your email and password.");
            return;
        }
        if (!auth.isValidEmail(email)) {
            showAlert("Please enter a valid email address.");
            return;
        }

        User user = auth.login(email, password);
        if (user != null) {
            onSuccess.accept(user);
        } else {
            showAlert("Invalid email or password.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sign In");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
