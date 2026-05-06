package com.mycompany.easyjob;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class App extends Application implements MatchListener {

    private Stage stage;
    private final Auth auth = new Auth();
    private User currentUser;
    private final List<Job> interestedJobs = new ArrayList<>();
    private final List<Job> skippedJobs = new ArrayList<>();

    private Scene homeScene;
    private Scene matchScene;
    private Scene listingScene;
    private Scene profileScene;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("Easy Job");
        showLogin();
        stage.show();
    }

    private void showLogin() {
        currentUser = null;
        interestedJobs.clear();
        skippedJobs.clear();
        LoginController lc = new LoginController(auth, this::showSignUp, this::showMainApp);
        stage.setScene(new Scene(lc.buildView(), 1200, 800));
    }

    private void showSignUp() {
        SignUpController sc = new SignUpController(auth, this::showLogin);
        stage.setScene(new Scene(sc.buildView(), 1200, 800));
    }

    private void showMainApp(User user) {
        this.currentUser = user;

        HomePage homePage = new HomePage();
        JobMatchmakingPage matchPage = new JobMatchmakingPage();
        JobListingPage listingPage = new JobListingPage();
        ProfilePage profilePage = new ProfilePage(user);

        homePage.setUser(user);
        matchPage.setUser(user);
        listingPage.setUser(user);
        profilePage.setUser(user);

        homeScene = new Scene(homePage.getView(), 1200, 800);
        matchScene = new Scene(matchPage.getView(), 1200, 800);
        listingScene = new Scene(listingPage.setJobListingPage(), 1200, 800);
        profileScene = new Scene(profilePage.getView(), 1200, 800);

        wireNav(homePage.getView(), homeScene, matchScene, listingScene, profileScene);
        wireNav(matchPage.getView(), homeScene, matchScene, listingScene, profileScene);
        wireNav(listingPage.setJobListingPage(), homeScene, matchScene, listingScene, profileScene);
        wireNav(profilePage.getView(), homeScene, matchScene, listingScene, profileScene);

        matchPage.setOnSkipAction(this);
        matchPage.setOnInterestedAction(this);

        profilePage.setOnLogout(this::logout);
        profilePage.setOnPostJobListener(this::openPostJobForm);
        profilePage.setOnProfileUpdateListener(this::handleProfileUpdate);

        stage.setScene(homeScene);
    }

    private void wireNav(Node root, Scene homeScene, Scene matchScene,
            Scene listingScene, Scene profileScene) {
        root.lookupAll(".button").forEach(node -> {
            if (node instanceof Button) {
                Button btn = (Button) node;
                String text = btn.getText();
                if (btn.getOnAction() != null) {
                    return;
                }
                switch (text) {
                    case "Home":
                        btn.setOnAction(e -> stage.setScene(homeScene));
                        break;
                    case "Jobs":
                        btn.setOnAction(e -> stage.setScene(matchScene));
                        break;
                    case "Filters":
                        btn.setOnAction(e -> stage.setScene(listingScene));
                        break;
                    case "Profile":
                        btn.setOnAction(e -> stage.setScene(profileScene));
                        break;
                }
            }
        });
    }

    @Override
    public void onSkip(Job job) {
        skippedJobs.add(job);
        System.out.println("Skipped: " + job.getJobTitle() + " @ " + job.getCompany());
    }

    @Override
    public void onInterested(Job job) {
        interestedJobs.add(job);
        System.out.println("Interested: " + job.getJobTitle() + " @ " + job.getCompany());
        System.out.println("Match: " + job.getMatchPercentage() + "%");
    }

    private void logout() {
        currentUser = null;
        interestedJobs.clear();
        skippedJobs.clear();
        showLogin();
    }

    private void openPostJobForm() {
        System.out.println("Opening job posting form for: " + currentUser.getName());

        PostJobPage postJobPage = new PostJobPage();

        postJobPage.setOnSubmitSuccess(() -> {
            Job newJob = postJobPage.getPostedJob(currentUser.getName());
            System.out.println("Job created: " + newJob.getJobTitle());
            stage.setScene(profileScene);
        });

        postJobPage.setOnCancel(() -> {
            stage.setScene(profileScene);
        });

        Scene postJobScene = new Scene(postJobPage.getView(), 1200, 800);
        stage.setScene(postJobScene);
    }

    private void handleProfileUpdate(User updatedUser) {
        System.out.println("Profile updated for: " + updatedUser.getName());
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}
