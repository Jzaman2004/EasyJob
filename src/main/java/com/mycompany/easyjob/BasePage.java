package com.mycompany.easyjob;

import javafx.scene.layout.VBox;
import javafx.scene.Node;

public abstract class BasePage {
    protected NavigationBar navBar;
    protected User currentUser;
    protected VBox root;

    public BasePage() {
        navBar = new NavigationBar();
        root = new VBox();
        root.setStyle("-fx-background-color: white;");
    }

    public void setUser(User user) {
        this.currentUser = user;
        if (navBar != null) {
            navBar.setUserProfile(user.getName());
        }
    }

    protected void buildNavBar(String activePage) {
        if (navBar != null) {
            navBar.setActive(activePage);
            root.getChildren().add(0, navBar.getView()); // Ensure nav is always on top
        }
    }

    public abstract Node getView();
}