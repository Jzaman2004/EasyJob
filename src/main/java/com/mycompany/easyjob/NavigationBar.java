package com.mycompany.easyjob;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class NavigationBar {

    private HBox nav;
    private Button home;
    private Button jobs;
    private Button filters; 
    private Button profile;
    private Button avatarBtn; 

    private Runnable onAvatarClicked;

    public NavigationBar() {
        Button logo = createNavButton("Easy Job");
        logo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;" + defaultButtonStyle());

        home = createNavButton("Home");
        jobs = createNavButton("Jobs");
        filters = createNavButton("Filters");
        profile = createNavButton("Profile");
        avatarBtn = createNavButton("Login");
        avatarBtn.setStyle(defaultButtonStyle() + "-fx-background-color: #3b82f6; -fx-text-fill: white;");

        avatarBtn.setOnAction(e -> {
            if (onAvatarClicked != null) {
                onAvatarClicked.run();
            }
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        nav = new HBox(10, logo, spacer, home, jobs, filters, profile, avatarBtn);
        nav.setSpacing(20);
        nav.setPadding(new Insets(15));
        nav.setStyle("-fx-background-color: #f5f7fb;");
    }

    public Button createNavButton(String name) {
        Button btn = new Button(name);
        btn.setStyle(defaultButtonStyle());
        return btn;
    }

    public String defaultButtonStyle() {
        return "-fx-background-color: transparent; -fx-text-fill: black;";
    }

    public String activeButtonStyle() {
        return "-fx-text-fill: #3b82f6; -fx-font-weight: bold;";
    }

    public void setActive(String name) {
        // Reset all nav buttons to default
        home.setStyle(defaultButtonStyle());
        jobs.setStyle(defaultButtonStyle());
        filters.setStyle(defaultButtonStyle());
        profile.setStyle(defaultButtonStyle());

        // Apply active style to target
        if (name.equalsIgnoreCase("Home")) {
            home.setStyle(defaultButtonStyle() + activeButtonStyle());
        } else if (name.equalsIgnoreCase("Jobs")) {
            jobs.setStyle(defaultButtonStyle() + activeButtonStyle());
        } else if (name.equalsIgnoreCase("Filters")) {
            filters.setStyle(defaultButtonStyle() + activeButtonStyle());
        } else if (name.equalsIgnoreCase("Profile")) {
            profile.setStyle(defaultButtonStyle() + activeButtonStyle());
        }
    }

    public void setUserProfile(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            return;
        }
        String initial = firstName.substring(0, 1).toUpperCase();
        avatarBtn.setText(initial);
        avatarBtn.setStyle(
                "-fx-background-color: #3b82f6;"
                + "-fx-text-fill: white;"
                + "-fx-font-weight: bold;"
                + "-fx-background-radius: 50%;"
                + "-fx-min-width: 30px;"
                + "-fx-min-height: 30px;"
                + "-fx-max-width: 30px;"
                + "-fx-max-height: 30px;"
                + "-fx-padding: 0;"
        );
    }

    public void setOnAvatarClicked(Runnable action) {
        this.onAvatarClicked = action;
    }

    public HBox getView() {
        return nav;
    }
}
