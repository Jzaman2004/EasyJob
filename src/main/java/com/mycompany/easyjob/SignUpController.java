/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.easyjob;

/**
 *
 * @author alish
 */
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class SignUpController {

    private Auth auth;
    private Runnable onBackToLogin;

    private TextField nameField;
    private TextField emailField;
    private PasswordField passwordField;
    private PasswordField confirmPasswordField;
    private RadioButton studentBtn;
    private RadioButton employerBtn;

    public SignUpController(Auth auth, Runnable onBackToLogin) {
        this.auth = auth;
        this.onBackToLogin = onBackToLogin;
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

        // --- Icon ---
        StackPane iconBox = new StackPane();
        iconBox.setStyle("-fx-background-color: #3b5bdb; -fx-background-radius: 14;" +
                         "-fx-min-width: 64; -fx-max-width: 64; -fx-min-height: 64; -fx-max-height: 64;");
        Label icon = new Label("💼");
        icon.setStyle("-fx-font-size: 28px; -fx-text-fill: white;");
        iconBox.getChildren().add(icon);

        // --- Title & Subtitle ---
        Label title = new Label("Create Account");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #1a1a2e; -fx-padding: 14 0 4 0;");

        Label subtitle = new Label("Start your STEM career journey today");
        subtitle.setStyle("-fx-font-size: 13.5px; -fx-text-fill: #6b7280; -fx-padding: 0 0 20 0;");

        // --- Name ---
        Label nameLabel = new Label("Full Name");
        nameLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #374151; -fx-padding: 8 0 4 0;");
        nameField = new TextField();
        nameField.setPromptText("John Doe");
        nameField.setStyle("-fx-pref-height: 42px; -fx-background-color: #f9fafb; -fx-border-color: #d1d5db;" +
                           "-fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 0 12 0 12; -fx-font-size: 14px;");

        // --- Email ---
        Label emailLabel = new Label("Email Address");
        emailLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #374151; -fx-padding: 8 0 4 0;");
        emailField = new TextField();
        emailField.setPromptText("you@example.com");
        emailField.setStyle("-fx-pref-height: 42px; -fx-background-color: #f9fafb; -fx-border-color: #d1d5db;" +
                            "-fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 0 12 0 12; -fx-font-size: 14px;");

        // --- Password ---
        Label passwordLabel = new Label("Password");
        passwordLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #374151; -fx-padding: 8 0 4 0;");
        passwordField = new PasswordField();
        passwordField.setPromptText("At least 6 characters");
        passwordField.setStyle("-fx-pref-height: 42px; -fx-background-color: #f9fafb; -fx-border-color: #d1d5db;" +
                               "-fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 0 12 0 12; -fx-font-size: 14px;");

        // --- Confirm Password ---
        Label confirmLabel = new Label("Confirm Password");
        confirmLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #374151; -fx-padding: 8 0 4 0;");
        confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Re-enter your password");
        confirmPasswordField.setStyle("-fx-pref-height: 42px; -fx-background-color: #f9fafb; -fx-border-color: #d1d5db;" +
                                      "-fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 0 12 0 12; -fx-font-size: 14px;");

        // --- Role Picker ---
        Label roleLabel = new Label("I am a...");
        roleLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #374151; -fx-padding: 8 0 4 0;");

        ToggleGroup roleGroup = new ToggleGroup();

        studentBtn = new RadioButton("Student");
        studentBtn.setToggleGroup(roleGroup);
        studentBtn.setSelected(true);
        studentBtn.setStyle("-fx-font-size: 13px; -fx-text-fill: #374151;");

        employerBtn = new RadioButton("Employer");
        employerBtn.setToggleGroup(roleGroup);
        employerBtn.setStyle("-fx-font-size: 13px; -fx-text-fill: #374151;");

        HBox roleRow = new HBox(24, studentBtn, employerBtn);
        roleRow.setPadding(new Insets(4, 0, 8, 0));

        // --- Create Account Button ---
        Button registerBtn = new Button("Create Account");
        registerBtn.setMaxWidth(Double.MAX_VALUE);
        registerBtn.setStyle("-fx-background-color: #3b5bdb; -fx-text-fill: white; -fx-font-size: 15px;" +
                             "-fx-font-weight: bold; -fx-pref-height: 46px; -fx-background-radius: 8; -fx-cursor: hand;");
        registerBtn.setOnAction(e -> handleRegister());

        // --- Back to Login ---
        Label hint = new Label("Already have an account?");
        hint.setStyle("-fx-text-fill: #6b7280; -fx-font-size: 13px;");
        Label loginLink = new Label("Sign in");
        loginLink.setStyle("-fx-text-fill: #3b5bdb; -fx-font-size: 13px; -fx-cursor: hand; -fx-font-weight: bold;");
        loginLink.setOnMouseClicked(e -> onBackToLogin.run());
        HBox loginRow = new HBox(4, hint, loginLink);
        loginRow.setAlignment(Pos.CENTER);
        loginRow.setPadding(new Insets(8, 0, 0, 0));

        // --- Assemble Card ---
        VBox card = new VBox(6);
        card.setAlignment(Pos.CENTER);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 16;" +
                      "-fx-padding: 40 50 40 50; -fx-min-width: 420; -fx-max-width: 460;");
        card.setEffect(new DropShadow(20, 0, 4, Color.rgb(0, 0, 0, 0.10)));
        card.getChildren().addAll(
            iconBox, title, subtitle,
            nameLabel, nameField,
            emailLabel, emailField,
            passwordLabel, passwordField,
            confirmLabel, confirmPasswordField,
            roleLabel, roleRow,
            registerBtn, loginRow
        );

        return card;
    }

    private HBox buildFooter() {
        HBox footer = new HBox(30);
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(16, 0, 16, 0));
        footer.setStyle("-fx-background-color: #f4f6f9; -fx-border-color: #e5e7eb; -fx-border-width: 1 0 0 0;");

        Label about   = new Label("About Us");
        Label privacy = new Label("Privacy Policy");
        Label terms   = new Label("Terms of Service");
        Label contact = new Label("Contact");
        Label help    = new Label("Help Center");

        String linkStyle = "-fx-text-fill: #6b7280; -fx-font-size: 12px; -fx-cursor: hand;";
        about.setStyle(linkStyle);
        privacy.setStyle(linkStyle);
        terms.setStyle(linkStyle);
        contact.setStyle(linkStyle);
        help.setStyle(linkStyle);

        footer.getChildren().addAll(about, privacy, terms, contact, help);
        return footer;
    }

    private void handleRegister() {
        String name    = nameField.getText().trim();
        String email   = emailField.getText().trim();
        String password = passwordField.getText();
        String confirm  = confirmPasswordField.getText();

        String role;
        if (studentBtn.isSelected()) {
            role = "Student";
        } else {
            role = "Employer";
        }

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            showAlert("Please fill in all fields.");
            return;
        }
        if (!auth.isValidEmail(email)) {
            showAlert("Please enter a valid email address.");
            return;
        }
        if (password.length() < 6) {
            showAlert("Password must be at least 6 characters.");
            return;
        }
        if (!password.equals(confirm)) {
            showAlert("Passwords do not match.");
            return;
        }
        if (auth.emailExists(email)) {
            showAlert("An account with that email already exists.");
            return;
        }

        boolean success = auth.register(name, email, password, role);
        if (success) {
            showAlert("Account created! You can now sign in.");
            onBackToLogin.run();
        } else {
            showAlert("Registration failed. Please try again.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sign Up");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

