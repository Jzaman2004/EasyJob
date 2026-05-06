package com.mycompany.easyjob;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProfilePage {

    private VBox root;
    private VBox content;
    private NavigationBar navBar;

    private User currentUser; 
    private String userName;
    private String userTitle;
    private String aboutText;
    private String degree;
    private String gradInfo;
    private String skillsText;
    private String jobType;
    private String jobRoles;
    private String jobLocation;
    private int appsSent;
    private int matches;
    private int interviews;
    private int profilePct;

    // Callbacks
    private Runnable onLogout;
    private OnPostJobListener onPostJobListener;
    private OnProfileUpdateListener onProfileUpdateListener;

    public ProfilePage(User user) {
        this.currentUser = user; 
        this.userName = user.getName();
        
        // Dynamic title: remove "Job Seeker" for Employers
        if ("Employer".equals(user.getRole())) {
            this.userTitle = user.getRole();
        } else {
            this.userTitle = user.getRole() + "  |  Job Seeker";
        }
        
        this.aboutText = "Click Edit Profile to write your bio.";
        this.degree = "Bachelors of Science (Honors)  |  St. Joseph's University New York";
        this.gradInfo = "Expected Graduation: 2027     GPA: 4.00 / 4.0";
        this.skillsText = "Python, Java, React";
        this.jobType = "Full-time, Internship";
        this.jobRoles = "Software Engineer";
        this.jobLocation = "Remote, New York";
        this.appsSent = 0;
        this.matches = 0;
        this.interviews = 0;
        this.profilePct = 0;

        navBar = new NavigationBar();
        navBar.setActive("Profile");
        navBar.setUserProfile(userName);

        content = new VBox(0);
        content.setStyle("-fx-background-color: #f5f7fb;");

        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: #f5f7fb; -fx-border-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        root = new VBox();
        root.getChildren().addAll(navBar.getView(), scroll);

        buildContent();
    }

    private void buildContent() {
        content.getChildren().clear();
        content.getChildren().addAll(
                buildBanner(),
                buildStatsRow(),
                buildContentRow(),
                buildFooter()
        );
    }

    private HBox buildBanner() {
        HBox banner = new HBox(20);
        banner.setAlignment(Pos.CENTER_LEFT);
        banner.setPadding(new Insets(28, 40, 28, 40));
        banner.setStyle("-fx-background-color: #2b4edb;");

        Circle circle = new Circle(34);
        circle.setFill(Color.web("#ffffff", 0.18));
        Label initial = new Label(String.valueOf(userName.charAt(0)).toUpperCase());
        initial.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: white;");
        StackPane avatar = new StackPane(circle, initial);

        Label nameLabel = new Label(userName);
        nameLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: white;");
        Label subtitleLabel = new Label(userTitle);
        subtitleLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: rgba(255,255,255,0.8);");
        VBox nameBox = new VBox(4, nameLabel, subtitleLabel);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button editBtn = new Button("Edit Profile");
        editBtn.setStyle("-fx-background-color: white; -fx-text-fill: #2b4edb; -fx-font-size: 13px; "
                + "-fx-font-weight: bold; -fx-cursor: hand; -fx-background-radius: 6; -fx-padding: 8 18;");
        editBtn.setOnAction(e -> openEditDialog());

        Button logoutBtn = new Button("Logout");
        logoutBtn.setStyle("-fx-background-color: rgba(255,255,255,0.2); -fx-text-fill: white; "
                + "-fx-font-size: 13px; -fx-cursor: hand; -fx-background-radius: 6; -fx-padding: 8 18;");
        logoutBtn.setOnMouseEntered(e -> logoutBtn.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-text-fill: white; "
                + "-fx-font-size: 13px; -fx-cursor: hand; -fx-background-radius: 6; -fx-padding: 8 18;"));
        logoutBtn.setOnMouseExited(e -> logoutBtn.setStyle("-fx-background-color: rgba(255,255,255,0.2); -fx-text-fill: white; "
                + "-fx-font-size: 13px; -fx-cursor: hand; -fx-background-radius: 6; -fx-padding: 8 18;"));
        logoutBtn.setOnAction(e -> {
            if (onLogout != null) {
                onLogout.run();
            }
        });

        banner.getChildren().addAll(avatar, nameBox, spacer, editBtn, logoutBtn);
        return banner;
    }

    private HBox buildStatsRow() {
        HBox row = new HBox(0);
        row.setAlignment(Pos.CENTER);
        row.setStyle("-fx-background-color: white; -fx-border-color: #e5e7eb; -fx-border-width: 0 0 1 0;");

        // Hide stats for Employers (optional UX choice)
        if (currentUser != null && "Employer".equals(currentUser.getRole())) {
            row.getChildren().addAll(
                    statCard("0", "Jobs Posted", "#3b82f6", true),
                    statCard("0", "Active Listings", "#10b981", true),
                    statCard("0", "Applications Received", "#f59e0b", true),
                    statCard(profilePct + "%", "Profile Strength", "#8b5cf6", false)
            );
        } else {
            row.getChildren().addAll(
                    statCard(String.valueOf(appsSent), "Applications Sent", "#3b82f6", true),
                    statCard(String.valueOf(matches), "Matches", "#10b981", true),
                    statCard(String.valueOf(interviews), "Interviews Scheduled", "#f59e0b", true),
                    statCard(profilePct + "%", "Profile Strength", "#8b5cf6", false)
            );
        }
        return row;
    }

    private VBox statCard(String value, String label, String color, boolean border) {
        VBox card = new VBox(4);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(20, 10, 20, 10));
        card.setPrefWidth(200);
        if (border) {
            card.setStyle("-fx-border-color: #e5e7eb; -fx-border-width: 0 1 0 0;");
        }

        Label val = new Label(value);
        val.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: " + color + ";");
        Label desc = new Label(label);
        desc.setStyle("-fx-font-size: 12px; -fx-text-fill: #6b7280;");

        card.getChildren().addAll(val, desc);
        return card;
    }

    private HBox buildContentRow() {
        HBox row = new HBox(20);
        row.setPadding(new Insets(24, 32, 24, 32));
        row.setStyle("-fx-background-color: #f5f7fb;");

        VBox aboutCard = buildAboutCard();
        VBox skillsCard = buildSkillsCard();
        HBox.setHgrow(aboutCard, Priority.ALWAYS);
        HBox.setHgrow(skillsCard, Priority.ALWAYS);

        row.getChildren().addAll(aboutCard, skillsCard);
        return row;
    }

    private VBox buildAboutCard() {
        VBox card = new VBox(16);
        card.setPadding(new Insets(20));
        card.setStyle("-fx-background-color: white; -fx-border-color: #e5e7eb; "
                + "-fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 1;");

        Label heading = new Label("About Me");
        heading.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #1a1a2e;");

        Label bio = new Label(aboutText);
        bio.setWrapText(true);
        bio.setStyle("-fx-font-size: 13px; -fx-text-fill: #6b7280;");

        Label eduHeading = new Label("Education");
        eduHeading.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #1a1a2e;");

        Label degreeLabel = new Label(degree);
        degreeLabel.setWrapText(true);
        degreeLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #1a1a2e;");

        Label gradLabel = new Label(gradInfo);
        gradLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #6b7280;");

        card.getChildren().addAll(heading, bio, new Separator(), eduHeading, degreeLabel, gradLabel);
        return card;
    }

    private VBox buildSkillsCard() {
        VBox card = new VBox(16);
        card.setPadding(new Insets(20));
        card.setStyle("-fx-background-color: white; -fx-border-color: #e5e7eb; "
                + "-fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 1;");

        Label heading = new Label("Skills & Technologies");
        heading.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #1a1a2e;");

        FlowPane tagsPane = new FlowPane(8, 8);
        String[][] tagColors = {
            {"#dbeafe", "#1e40af"}, {"#fce7f3", "#9d174d"}, {"#d1fae5", "#065f46"},
            {"#fef3c7", "#92400e"}, {"#e0e7ff", "#3730a3"}, {"#ecfdf5", "#047857"},
            {"#fee2e2", "#991b1b"}
        };

        String[] skills = skillsText.split(",");
        for (int i = 0; i < skills.length; i++) {
            String skill = skills[i].trim();
            if (!skill.isEmpty()) {
                String bg = tagColors[i % tagColors.length][0];
                String fg = tagColors[i % tagColors.length][1];
                tagsPane.getChildren().add(skillTag(skill, bg, fg));
            }
        }

        Label prefHeading = new Label("Job Preferences");
        prefHeading.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #1a1a2e;");

        Label typeLabel = new Label("Type: " + jobType);
        Label rolesLabel = new Label("Roles: " + jobRoles);
        Label locationLabel = new Label("Location: " + jobLocation);
        for (Label l : new Label[]{typeLabel, rolesLabel, locationLabel}) {
            l.setStyle("-fx-font-size: 13px; -fx-text-fill: #6b7280;");
        }

        // Employer-specific section with Add Job button
        if (currentUser != null && "Employer".equals(currentUser.getRole())) {
            Label employerNote = new Label("💼 Posting jobs as: " + userName);
            employerNote.setStyle("-fx-font-size: 12px; -fx-text-fill: #10b981; -fx-font-style: italic;");
            
            Button addJobBtn = new Button("Add Job");
            addJobBtn.setStyle("-fx-background-color: #10b981; -fx-text-fill: white; "
                    + "-fx-font-size: 12px; -fx-font-weight: bold; -fx-cursor: hand; "
                    + "-fx-background-radius: 6; -fx-padding: 6 16;");
            addJobBtn.setOnAction(e -> {
                if (onPostJobListener != null) {
                    onPostJobListener.onPostJobRequested();
                }
            });

            card.getChildren().addAll(heading, tagsPane, new Separator(),
                    prefHeading, typeLabel, rolesLabel, locationLabel,
                    new Separator(), employerNote, addJobBtn);
        } else {
            card.getChildren().addAll(heading, tagsPane, new Separator(),
                    prefHeading, typeLabel, rolesLabel, locationLabel);
        }

        return card;
    }

    private Label skillTag(String text, String bg, String fg) {
        Label tag = new Label(text);
        tag.setStyle("-fx-background-color: " + bg + "; -fx-text-fill: " + fg + "; "
                + "-fx-font-size: 12px; -fx-font-weight: bold; -fx-padding: 5 12; "
                + "-fx-background-radius: 20; -fx-border-radius: 20;");
        return tag;
    }

    private HBox buildFooter() {
        HBox footer = new HBox(24);
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(24));
        footer.setStyle("-fx-background-color: #f5f7fb; -fx-border-color: #e5e7eb; -fx-border-width: 1 0 0 0;");

        for (String link : new String[]{"About Us", "Privacy Policy", "Terms of Service", "Contact", "Help Center"}) {
            Label lbl = new Label(link);
            lbl.setStyle("-fx-font-size: 12px; -fx-text-fill: #6b7280; -fx-cursor: hand;");
            lbl.setOnMouseEntered(e -> lbl.setStyle("-fx-font-size: 12px; -fx-text-fill: #3b82f6; -fx-cursor: hand; -fx-underline: true;"));
            lbl.setOnMouseExited(e -> lbl.setStyle("-fx-font-size: 12px; -fx-text-fill: #6b7280; -fx-cursor: hand;"));
            // Demo click feedback
            lbl.setOnMouseClicked(e -> System.out.println("Footer link clicked: " + link));
            footer.getChildren().add(lbl);
        }
        return footer;
    }

    private void openEditDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Edit Profile");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setResizable(false);

        // Fields pre-filled with current values
        TextField nameField = styledField(userName, "e.g. Alice Student");
        TextField titleField = styledField(userTitle, "e.g. Computer Science Student | Job Seeker");
        TextArea bioField = new TextArea(aboutText);
        bioField.setPromptText("Write a short bio about yourself...");
        bioField.setPrefRowCount(3);
        bioField.setWrapText(true);
        TextField degreeField = styledField(degree, "e.g. B.S. Computer Science | St. Joseph's University");
        TextField gradField = styledField(gradInfo, "e.g. Expected Graduation: May 2027   GPA: X.XX / 4.0");
        TextField skillsField = styledField(skillsText, "e.g. Python, Java, React, SQL");
        TextField jobTypeField = styledField(jobType, "e.g. Full-time, Internship");
        TextField jobRolesField = styledField(jobRoles, "e.g. Software Engineer, Data Scientist");
        TextField jobLocField = styledField(jobLocation, "e.g. Remote, New York");

        // Spinners for stats
        Spinner<Integer> appsSpinner = spinner(0, 999, appsSent);
        Spinner<Integer> matchSpinner = spinner(0, 999, matches);
        Spinner<Integer> intSpinner = spinner(0, 999, interviews);
        Spinner<Integer> pctSpinner = spinner(0, 100, profilePct);

        HBox statsRow = new HBox(12,
                labeledSpinner("Apps Sent", appsSpinner),
                labeledSpinner("Matches", matchSpinner),
                labeledSpinner("Interviews", intSpinner),
                labeledSpinner("Profile %", pctSpinner)
        );

        // Buttons
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setStyle("-fx-background-color: #f3f4f6; -fx-font-size: 13px; "
                + "-fx-padding: 8 20; -fx-background-radius: 6; -fx-cursor: hand;");
        cancelBtn.setOnAction(e -> dialog.close());

        Button saveBtn = new Button("Save Changes");
        saveBtn.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-font-size: 13px; "
                + "-fx-font-weight: bold; -fx-padding: 8 20; -fx-background-radius: 6; -fx-cursor: hand;");
        saveBtn.setOnAction(e -> {
            if (!validateEditFields(nameField, titleField, gradField)) {
                return;
            }

            userName = nameField.getText().trim();
            userTitle = titleField.getText().trim();
            aboutText = bioField.getText().trim();
            degree = degreeField.getText().trim();
            gradInfo = gradField.getText().trim();
            skillsText = skillsField.getText().trim();
            jobType = jobTypeField.getText().trim();
            jobRoles = jobRolesField.getText().trim();
            jobLocation = jobLocField.getText().trim();
            appsSent = appsSpinner.getValue();
            matches = matchSpinner.getValue();
            interviews = intSpinner.getValue();
            profilePct = pctSpinner.getValue();

            navBar.setUserProfile(userName);
            dialog.close();
            buildContent();

            // Notify listeners of profile update
            if (onProfileUpdateListener != null && currentUser != null) {
                currentUser.setName(userName);
                onProfileUpdateListener.onProfileUpdated(currentUser);
            }

            showAlert("Profile Updated", "Your changes have been saved successfully.");
        });

        HBox btnRow = new HBox(12, cancelBtn, saveBtn);
        btnRow.setAlignment(Pos.CENTER_RIGHT);

        // Assemble form
        VBox form = new VBox(5);
        form.setPadding(new Insets(24));
        form.setStyle("-fx-background-color: white;");
        form.setPrefWidth(480);

        Label formTitle = new Label("Edit Your Profile");
        formTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #1a1a2e; -fx-padding: 0 0 10 0;");

        Label statsHeading = new Label("Stats");
        statsHeading.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #6b7280; -fx-padding: 6 0 0 0;");

        form.getChildren().addAll(
                formTitle,
                formRow("Full Name *", nameField),
                formRow("Title / Tagline *", titleField),
                formRow("About Me (Bio)", bioField),
                formRow("Degree & University", degreeField),
                formRow("Graduation Year & GPA", gradField),
                formRow("Skills (comma-separated)", skillsField),
                formRow("Job Type", jobTypeField),
                formRow("Desired Roles", jobRolesField),
                formRow("Preferred Location", jobLocField),
                statsHeading, statsRow,
                btnRow
        );

        ScrollPane scroll = new ScrollPane(form);
        scroll.setFitToWidth(true);
        dialog.setScene(new Scene(scroll, 520, 620));
        dialog.show();
    }

    private boolean validateEditFields(TextField name, TextField title, TextField grad) {
        boolean valid = true;

        if (name.getText().trim().isEmpty()) {
            name.setStyle("-fx-border-color: #ef4444; -fx-border-width: 2px; -fx-background-radius: 8; -fx-border-radius: 8;");
            valid = false;
        } else {
            name.setStyle("-fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #d1d5db; -fx-background-color: #f9fafb; -fx-padding: 8;");
        }

        if (title.getText().trim().isEmpty()) {
            title.setStyle("-fx-border-color: #ef4444; -fx-border-width: 2px; -fx-background-radius: 8; -fx-border-radius: 8;");
            valid = false;
        } else {
            title.setStyle("-fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #d1d5db; -fx-background-color: #f9fafb; -fx-padding: 8;");
        }

        String gradText = grad.getText().trim();
        if (!gradText.matches(".*\\d+\\.\\d+\\s*/\\s*\\d+\\.\\d+.*") && !gradText.isEmpty()) {
            grad.setStyle("-fx-border-color: #ef4444; -fx-border-width: 2px; -fx-background-radius: 8; -fx-border-radius: 8;");
            showAlert("Invalid GPA Format", "Please use format: X.XX / 4.0");
            valid = false;
        } else {
            grad.setStyle("-fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #d1d5db; -fx-background-color: #f9fafb; -fx-padding: 8;");
        }

        if (!valid) {
            showAlert("Missing Required Fields", "Please fill in all fields marked with *");
        }
        return valid;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private VBox formRow(String labelText, Node input) {
        Label label = new Label(labelText);
        label.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #374151; -fx-padding: 8 0 2 0;");
        return new VBox(4, label, input);
    }

    private TextField styledField(String value, String prompt) {
        TextField tf = new TextField(value);
        tf.setPromptText(prompt);
        tf.setStyle("-fx-background-radius: 8; -fx-border-radius: 8; "
                + "-fx-border-color: #d1d5db; -fx-background-color: #f9fafb; -fx-padding: 8;");
        return tf;
    }

    private Spinner<Integer> spinner(int min, int max, int initial) {
        Spinner<Integer> s = new Spinner<>(min, max, initial);
        s.setEditable(true);
        s.setPrefWidth(85);
        return s;
    }

    private VBox labeledSpinner(String text, Spinner<Integer> s) {
        Label l = new Label(text);
        l.setStyle("-fx-font-size: 12px; -fx-text-fill: #374151;");
        return new VBox(4, l, s);
    }

    //PUBLIC SETTERS FOR CALLBACKS 
    
    public void setOnLogout(Runnable action) {
        this.onLogout = action;
    }

    public void setOnPostJobListener(OnPostJobListener listener) {
        this.onPostJobListener = listener;
    }

    public void setOnProfileUpdateListener(OnProfileUpdateListener listener) {
        this.onProfileUpdateListener = listener;
    }

    //PUBLIC METHODS FOR STATE UPDATES

    public VBox getView() {
        return root;
    }

    public void incrementAppsSent() {
        appsSent++;
        buildContent();
    }

    public void incrementMatches() {
        matches++;
        buildContent();
    }

    public void incrementInterviews() {
        interviews++;
        buildContent();
    }

    public void updateProfileStrength(int pct) {
        profilePct = pct;
        buildContent();
    }
    
    public void setUser(User user) {
        this.currentUser = user;
        this.userName = user.getName();
        
        // Dynamic title update
        if ("Employer".equals(user.getRole())) {
            this.userTitle = user.getRole();
        } else {
            this.userTitle = user.getRole() + "  |  Job Seeker";
        }
        
        if (navBar != null) {
            navBar.setUserProfile(userName);
        }
        buildContent();
    }
}