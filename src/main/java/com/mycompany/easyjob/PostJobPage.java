package com.mycompany.easyjob;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class PostJobPage {

    private VBox root;

    private TextField jobTitleField;
    private TextField companyField;
    private TextArea descriptionField;
    private TextField minSalaryField;
    private TextField maxSalaryField;
    private ComboBox<String> locationBox;
    private ComboBox<String> jobTypeBox;
    private ComboBox<String> experienceBox;
    private Runnable onSubmitSuccess;
    private Runnable onCancel;

    public PostJobPage() {
        root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #f5f7fb;");

        Label title = new Label("Post a New Job");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        Label subtitle = new Label("Fill in the details below to create your job posting");

        VBox card = new VBox(20);
        card.setPadding(new Insets(25));
        card.setStyle("-fx-background-color: white;"
                + "-fx-background-radius: 12;"
                + "-fx-border-radius: 12;"
                + "-fx-border-color: #e5e7eb;");

        GridPane grid = new GridPane();
        grid.setHgap(25);
        grid.setVgap(20);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        grid.getColumnConstraints().addAll(col1, col2);

        jobTitleField = input();
        companyField = input();
        descriptionField = textArea();

        minSalaryField = input();
        minSalaryField.setPromptText("Min");
        maxSalaryField = input();
        maxSalaryField.setPromptText("Max");
        HBox salaryBox = new HBox(10, minSalaryField, maxSalaryField);

        locationBox = new ComboBox<>();
        locationBox.getItems().addAll(
                "NYC", "San Francisco", "Los Angeles", "Chicago", "Seattle",
                "Boston", "Austin", "Denver", "Atlanta", "Remote"
        );
        locationBox.setPromptText("Select location");

        jobTypeBox = new ComboBox<>();
        jobTypeBox.getItems().addAll("Internship", "Part-Time", "Full-Time");
        jobTypeBox.setPromptText("Select type");
        styleCombo(jobTypeBox);

        experienceBox = new ComboBox<>();
        experienceBox.getItems().addAll("Entry Level", "Mid Level", "Senior Level", "Lead/Manager");
        experienceBox.setPromptText("Select level");
        styleCombo(experienceBox);

        VBox left = new VBox(15,
                field("Job Title *", jobTitleField),
                field("Company Name *", companyField),
                field("Job Description *", descriptionField)
        );

        VBox right = new VBox(15,
                field("Salary Range *", salaryBox),
                field("Location *", locationBox),
                field("Job Type *", jobTypeBox),
                field("Experience Level *", experienceBox)
        );

        grid.add(left, 0, 0);
        grid.add(right, 1, 0);

        Button submit = new Button("Submit Job");
        submit.setStyle("-fx-background-color: #3b82f6;"
                + "-fx-text-fill: white;"
                + "-fx-background-radius: 10;"
                + "-fx-padding: 10 25;");
        submit.setOnAction(e -> handleSubmit());

        Button cancel = new Button("Cancel");
        cancel.setStyle("-fx-background-color: #f3f4f6;"
                + "-fx-background-radius: 10;"
                + "-fx-padding: 10 25;");
        cancel.setOnAction(e -> {
            if (onCancel != null) {
                onCancel.run();
            } else {
                System.out.println("Cancel clicked - would navigate back");
            }
        });

        HBox buttons = new HBox(15, submit, cancel);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        card.getChildren().addAll(grid, buttons);
        root.getChildren().addAll(title, subtitle, card);
    }

    private void handleSubmit() {
        clearErrors();

        boolean isValid = true;

        if (jobTitleField.getText().trim().isEmpty()) {
            setError(jobTitleField);
            isValid = false;
        }
        if (companyField.getText().trim().isEmpty()) {
            setError(companyField);
            isValid = false;
        }
        if (descriptionField.getText().trim().isEmpty()) {
            setError(descriptionField);
            isValid = false;
        }
        if (minSalaryField.getText().trim().isEmpty() || maxSalaryField.getText().trim().isEmpty()) {
            if (minSalaryField.getText().trim().isEmpty()) {
                setError(minSalaryField);
            }
            if (maxSalaryField.getText().trim().isEmpty()) {
                setError(maxSalaryField);
            }
            isValid = false;
        }
        if (locationBox.getValue() == null) {
            locationBox.setStyle("-fx-border-color: #ef4444; -fx-background-radius: 10; -fx-border-radius: 10;");
            isValid = false;
        }
        if (jobTypeBox.getValue() == null) {
            jobTypeBox.setStyle("-fx-border-color: #ef4444; -fx-background-radius: 10; -fx-border-radius: 10;");
            isValid = false;
        }
        if (experienceBox.getValue() == null) {
            experienceBox.setStyle("-fx-border-color: #ef4444; -fx-background-radius: 10; -fx-border-radius: 10;");
            isValid = false;
        }

        if (!isValid) {
            showAlert("Missing Required Fields", "Please fill in all fields marked with *");
            return;
        }

        System.out.println("Job Posted:");
        System.out.println("   Title: " + jobTitleField.getText());
        System.out.println("   Company: " + companyField.getText());
        System.out.println("   Location: " + locationBox.getValue());
        System.out.println("   Salary: $" + minSalaryField.getText() + "k - $" + maxSalaryField.getText() + "k");
        System.out.println("   Type: " + jobTypeBox.getValue() + " | Level: " + experienceBox.getValue());

        showAlert("Job Posted Successfully!", "Your job listing is now live.");

        if (onSubmitSuccess != null) {
            onSubmitSuccess.run();
        }
    }

    private void clearErrors() {
        jobTitleField.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #d1d5db; -fx-padding: 8;");
        companyField.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #d1d5db; -fx-padding: 8;");
        descriptionField.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #d1d5db;");
        minSalaryField.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #d1d5db; -fx-padding: 8;");
        maxSalaryField.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #d1d5db; -fx-padding: 8;");
        locationBox.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #d1d5db;");
        jobTypeBox.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #d1d5db;");
        experienceBox.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #d1d5db;");
    }

    private void setError(javafx.scene.Node node) {
        if (node instanceof TextField || node instanceof TextArea) {
            node.setStyle("-fx-border-color: #ef4444; -fx-border-width: 2px; -fx-background-radius: 10; -fx-border-radius: 10;");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public VBox getView() {
        return root;
    }

    public void setOnSubmitSuccess(Runnable action) {
        this.onSubmitSuccess = action;
    }

    public void setOnCancel(Runnable action) {
        this.onCancel = action;
    }

    public Job getPostedJob(String employerName) {
        return new Job(
                employerName.substring(0, 2).toUpperCase(),
                jobTitleField.getText(),
                companyField.getText(),
                locationBox.getValue(),
                minSalaryField.getText(),
                maxSalaryField.getText(),
                java.util.Arrays.asList("Python", "AWS"),
                85
        );
    }

    private VBox field(String labelText, javafx.scene.Node input) {
        Label label = new Label(labelText);
        return new VBox(5, label, input);
    }

    private TextField input() {
        TextField tf = new TextField();
        tf.setStyle("-fx-background-radius: 10;"
                + "-fx-border-radius: 10;"
                + "-fx-border-color: #d1d5db;"
                + "-fx-padding: 8;");
        return tf;
    }

    private TextArea textArea() {
        TextArea ta = new TextArea();
        ta.setPrefRowCount(4);
        ta.setStyle("-fx-background-radius: 10;"
                + "-fx-border-radius: 10;"
                + "-fx-border-color: #d1d5db;");
        return ta;
    }

    private void styleCombo(ComboBox<String> box) {
        box.setStyle("-fx-background-radius: 10;"
                + "-fx-border-radius: 10;"
                + "-fx-border-color: #d1d5db;");
        box.setPrefHeight(35);
    }
}
