package com.mycompany.easyjob;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class HomePage {

    private VBox mainLayout;
    private NavigationBar navBar;
    private TextField searchField;
    private Button searchButton;

    private Runnable onSearchTriggered;

    public HomePage() {
        navBar = new NavigationBar();
        navBar.setActive("Home");
        mainLayout = new VBox();
        mainLayout.setStyle("-fx-background-color: white;");
        createPage();
    }

    private void createPage() {
        mainLayout.getChildren().add(navBar.getView());

        VBox content = new VBox(20);
        content.setPadding(new Insets(40, 60, 40, 60));
        content.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Find Your Perfect STEM Career");
        titleLabel.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-text-fill: #1a1a2e;");
        titleLabel.setWrapText(true);
        titleLabel.setMaxWidth(800);
        titleLabel.setAlignment(Pos.CENTER);

        Label subtitleLabel = new Label("Connect with top employers in science, technology, engineering, and mathematics");
        subtitleLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #666666;");
        subtitleLabel.setWrapText(true);
        subtitleLabel.setMaxWidth(600);
        subtitleLabel.setAlignment(Pos.CENTER);

        HBox searchBox = new HBox(10);
        searchBox.setAlignment(Pos.CENTER);
        searchField = new TextField();
        searchField.setPromptText("Search jobs, companies, or keywords...");
        searchField.setPrefWidth(500);
        searchField.setPrefHeight(45);
        searchField.setStyle("-fx-border-color: #dddddd; -fx-border-radius: 8px; "
                + "-fx-background-radius: 8px; -fx-padding: 10px; -fx-font-size: 14px;");

        searchButton = new Button("Search");
        searchButton.setPrefHeight(45);
        searchButton.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white; "
                + "-fx-font-weight: bold; -fx-background-radius: 8px; "
                + "-fx-cursor: hand; -fx-font-size: 14px;");

        searchButton.setOnMouseEntered(e -> searchButton.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; "
                + "-fx-font-weight: bold; -fx-background-radius: 8px; -fx-cursor: hand; -fx-font-size: 14px;"));
        searchButton.setOnMouseExited(e -> searchButton.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white; "
                + "-fx-font-weight: bold; -fx-background-radius: 8px; -fx-cursor: hand; -fx-font-size: 14px;"));

        searchButton.setOnAction(e -> triggerSearch());
        searchField.setOnAction(e -> triggerSearch()); // Enter key support

        searchBox.getChildren().addAll(searchField, searchButton);

        HBox footer = new HBox(20);
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(40, 0, 20, 0));
        footer.setStyle("-fx-border-color: #eeeeee; -fx-border-width: 1px 0 0 0;");

        for (String link : new String[]{"About Us", "Privacy Policy", "Terms of Service", "Contact", "Help Center"}) {
            Label footerLabel = new Label(link);
            footerLabel.setStyle("-fx-text-fill: #999999; -fx-font-size: 12px; -fx-cursor: hand;");
            footerLabel.setOnMouseEntered(e -> footerLabel.setStyle("-fx-text-fill: #3b82f6; -fx-font-size: 12px; -fx-cursor: hand; -fx-underline: true;"));
            footerLabel.setOnMouseExited(e -> footerLabel.setStyle("-fx-text-fill: #999999; -fx-font-size: 12px; -fx-cursor: hand;"));
            footerLabel.setOnMouseClicked(e
                    -> System.out.println("Footer link clicked: " + link)
            );
            footer.getChildren().add(footerLabel);
        }

        content.getChildren().addAll(titleLabel, subtitleLabel, searchBox, footer);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        mainLayout.getChildren().addAll(content, spacer);
    }

    // Centralized search handler
    private void triggerSearch() {
        String query = searchField.getText().trim();
        if (query.isEmpty()) {
            searchField.setStyle("-fx-border-color: #ef4444;"); 
            return;
        }
        searchField.setStyle("-fx-border-color: #dddddd; -fx-border-radius: 8px; "
                + "-fx-background-radius: 8px; -fx-padding: 10px; -fx-font-size: 14px;");

        System.out.println("🔍 Search triggered: \"" + query + "\"");

        if (onSearchTriggered != null) {
            onSearchTriggered.run();
        }
    }

    public void setUser(User user) {
        navBar.setUserProfile(user.getName());
    }

    public VBox getView() {
        return mainLayout;
    }

    public TextField getSearchField() {
        return searchField;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public void setOnSearchTriggered(Runnable action) {
        this.onSearchTriggered = action;
    }
}
