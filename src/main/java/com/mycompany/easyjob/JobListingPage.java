package com.mycompany.easyjob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class JobListingPage {

    BorderPane root = new BorderPane();
    private NavigationBar navbar = new NavigationBar();

    // Filter controls
    private ComboBox<String> jobTypeCombo;
    private VBox expOptions;
    private TextField minSalaryField;
    private TextField maxSalaryField;
    private VBox locationBox;

    // Job data
    private List<Job> allJobs;
    private VBox jobListContainer;
    private Label jobsCountLabel;

    public JobListingPage() {
        navbar.setActive("Filters");
        root.setTop(navbar.getView());

        // Filter panel
        Label filterTitle = new Label("Filters");
        filterTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        jobTypeCombo = new ComboBox<>();
        jobTypeCombo.setPromptText("Job Type");
        jobTypeCombo.getItems().addAll("Internship", "Part-Time", "Full-Time");

        Label expLabel = new Label("Experience Level");

        expOptions = new VBox(5,
                new CheckBox("Entry Level"),
                new CheckBox("Mid Level"),
                new CheckBox("Senior Level"),
                new CheckBox("Lead/Manager")
        );

        Label salaryRange = new Label("Salary Range");
        minSalaryField = new TextField();
        minSalaryField.setPromptText("Min");
        maxSalaryField = new TextField();
        maxSalaryField.setPromptText("Max");
        HBox salaryRangeBox = new HBox(10, minSalaryField, maxSalaryField);

        Label location = new Label("Location");

        locationBox = new VBox(5,
                new CheckBox("NYC"),
                new CheckBox("San Francisco"),
                new CheckBox("Los Angeles"),
                new CheckBox("Chicago"),
                new CheckBox("Seattle"),
                new CheckBox("Boston"),
                new CheckBox("Austin"),
                new CheckBox("Denver"),
                new CheckBox("Atlanta"),
                new CheckBox("Remote")
        );

        ScrollPane locationScroll = new ScrollPane(locationBox);
        locationScroll.setPrefHeight(120);

        Button applyBtn = new Button("Apply Filters");
        applyBtn.setMaxWidth(Double.MAX_VALUE);
        applyBtn.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white;");
        applyBtn.setOnAction(e -> applyFilters());

        Button resetBtn = new Button("Reset");
        resetBtn.setMaxWidth(Double.MAX_VALUE);
        resetBtn.setOnAction(e -> resetFilters());

        VBox leftPanel = new VBox(15, filterTitle, jobTypeCombo, expLabel, expOptions,
                salaryRange, salaryRangeBox, location, locationScroll,
                applyBtn, resetBtn);
        leftPanel.setPadding(new Insets(20));
        leftPanel.setPrefWidth(250);
        leftPanel.setStyle("-fx-background-color: #f5f7fb;");
        root.setLeft(leftPanel);

        // Initialize job list using the shared Job model
        allJobs = Arrays.asList(
                new Job("TC", "Senior Software Engineer", "TechCorp Inc.", "San Francisco, CA", "140", "180", Arrays.asList("Python", "AWS", "Kubernetes"), 87),
                new Job("DS", "Data Scientist", "DataFlow Analytics", "New York, NY", "120", "160", Arrays.asList("Python", "R", "Machine Learning"), 92),
                new Job("MR", "Research Scientist", "Microsoft Research", "Seattle, WA", "130", "170", Arrays.asList("AI/ML", "Python", "Deep Learning"), 85),
                new Job("FE", "Frontend Engineer", "Google", "Mountain View, CA", "135", "175", Arrays.asList("React", "TypeScript", "CSS"), 78),
                new Job("BE", "Backend Engineer", "Amazon", "Austin, TX", "125", "165", Arrays.asList("Java", "AWS", "Microservices"), 90),
                new Job("ML", "ML Engineer", "Meta", "Menlo Park, CA", "145", "185", Arrays.asList("PyTorch", "Python", "NLP"), 88),
                new Job("SE", "Security Engineer", "Cisco", "San Jose, CA", "130", "170", Arrays.asList("Cybersecurity", "Network", "Python"), 82),
                new Job("DE", "DevOps Engineer", "Netflix", "Los Gatos, CA", "140", "180", Arrays.asList("Docker", "Kubernetes", "CI/CD"), 91),
                new Job("AP", "Android Developer", "Spotify", "New York, NY", "115", "155", Arrays.asList("Kotlin", "Java", "Android SDK"), 84),
                new Job("IO", "iOS Developer", "Uber", "San Francisco, CA", "120", "160", Arrays.asList("Swift", "UIKit", "SwiftUI"), 79),
                new Job("FS", "Full Stack Developer", "Airbnb", "Remote", "125", "165", Arrays.asList("React", "Node.js", "PostgreSQL"), 93),
                new Job("QA", "QA Engineer", "Salesforce", "Seattle, WA", "100", "140", Arrays.asList("Selenium", "Java", "Testing"), 76),
                new Job("PM", "Product Manager", "LinkedIn", "San Francisco, CA", "130", "170", Arrays.asList("Agile", "SQL", "Analytics"), 81),
                new Job("UX", "UX Designer", "Adobe", "San Jose, CA", "110", "150", Arrays.asList("Figma", "User Research", "Prototyping"), 88),
                new Job("DB", "Database Admin", "Oracle", "Austin, TX", "105", "145", Arrays.asList("Oracle", "SQL", "Performance Tuning"), 73),
                new Job("CL", "Cloud Architect", "IBM", "Remote", "145", "185", Arrays.asList("AWS", "Azure", "Cloud Security"), 90),
                new Job("BI", "BI Analyst", "Tableau", "Seattle, WA", "95", "135", Arrays.asList("SQL", "Tableau", "Data Visualization"), 85),
                new Job("SC", "Scrum Master", "Atlassian", "Austin, TX", "100", "140", Arrays.asList("Agile", "Jira", "Team Leadership"), 77),
                new Job("NT", "Network Engineer", "Juniper Networks", "San Jose, CA", "110", "150", Arrays.asList("Cisco", "Routing", "Firewalls"), 82),
                new Job("EM", "Embedded Engineer", "Tesla", "Palo Alto, CA", "120", "160", Arrays.asList("C++", "RTOS", "Hardware"), 86),
                new Job("AI", "AI Researcher", "OpenAI", "San Francisco, CA", "150", "200", Arrays.asList("Python", "TensorFlow", "Research"), 94),
                new Job("BL", "Blockchain Dev", "Coinbase", "Remote", "130", "170", Arrays.asList("Solidity", "Web3", "Smart Contracts"), 80),
                new Job("GA", "Game Developer", "Electronic Arts", "Los Angeles, CA", "105", "145", Arrays.asList("Unity", "C#", "3D Graphics"), 75),
                new Job("VR", "VR Engineer", "Meta", "Menlo Park, CA", "125", "165", Arrays.asList("Unity", "C++", "3D Math"), 83),
                new Job("AR", "AR Developer", "Snap Inc.", "Los Angeles, CA", "115", "155", Arrays.asList("ARKit", "Unity", "Computer Vision"), 78),
                new Job("DT", "Data Engineer", "Databricks", "San Francisco, CA", "130", "170", Arrays.asList("Spark", "Python", "ETL"), 91),
                new Job("ML", "MLOps Engineer", "Hugging Face", "Remote", "125", "165", Arrays.asList("Docker", "Python", "Kubernetes"), 87),
                new Job("SI", "Site Reliability Eng", "Google", "Mountain View, CA", "140", "180", Arrays.asList("Linux", "Python", "Monitoring"), 89),
                new Job("PL", "Platform Engineer", "Stripe", "San Francisco, CA", "135", "175", Arrays.asList("Go", "Kubernetes", "API Design"), 92),
                new Job("IN", "Integration Eng", "Zapier", "Remote", "110", "150", Arrays.asList("APIs", "Python", "Webhooks"), 84),
                new Job("FR", "Frontend Lead", "Shopify", "Toronto, ON", "120", "160", Arrays.asList("React", "GraphQL", "TypeScript"), 86),
                new Job("BK", "Backend Lead", "Twilio", "Denver, CO", "130", "170", Arrays.asList("Java", "Microservices", "Redis"), 88),
                new Job("MO", "Mobile Lead", "Square", "San Francisco, CA", "135", "175", Arrays.asList("Swift", "Kotlin", "Mobile Architecture"), 90),
                new Job("CS", "Customer Success Eng", "Zendesk", "Remote", "90", "130", Arrays.asList("JavaScript", "APIs", "Customer Support"), 72),
                new Job("TE", "Technical Writer", "GitLab", "Remote", "85", "125", Arrays.asList("Documentation", "Markdown", "Git"), 70),
                new Job("SU", "Support Engineer", "MongoDB", "Austin, TX", "95", "135", Arrays.asList("MongoDB", "Linux", "Troubleshooting"), 74),
                new Job("SO", "Solutions Architect", "AWS", "Seattle, WA", "140", "180", Arrays.asList("AWS", "Architecture", "Client Facing"), 91),
                new Job("PR", "Principal Engineer", "Netflix", "Los Gatos, CA", "180", "240", Arrays.asList("System Design", "Java", "Leadership"), 95),
                new Job("ST", "Staff Engineer", "Uber", "San Francisco, CA", "160", "210", Arrays.asList("Distributed Systems", "Go", "Mentoring"), 93),
                new Job("EN", "Engineering Manager", "Airbnb", "San Francisco, CA", "150", "200", Arrays.asList("Leadership", "Agile", "Technical Strategy"), 87),
                new Job("VP", "VP Engineering", "Stripe", "San Francisco, CA", "200", "280", Arrays.asList("Leadership", "Strategy", "Scaling"), 89),
                new Job("CT", "CTO", "Startup XYZ", "Remote", "180", "250", Arrays.asList("Strategy", "Architecture", "Team Building"), 85),
                new Job("IN", "Intern Software Eng", "Microsoft", "Redmond, WA", "60", "80", Arrays.asList("Python", "Java", "Learning"), 78),
                new Job("JR", "Junior Developer", "Accenture", "Chicago, IL", "70", "95", Arrays.asList("JavaScript", "HTML/CSS", "Git"), 75),
                new Job("SR", "Senior Data Analyst", "Facebook", "Menlo Park, CA", "115", "155", Arrays.asList("SQL", "Python", "Statistics"), 86),
                new Job("DS", "Data Science Manager", "Amazon", "Seattle, WA", "145", "190", Arrays.asList("ML", "Team Leadership", "Python"), 90),
                new Job("AN", "Analytics Engineer", "Fivetran", "Remote", "110", "150", Arrays.asList("dbt", "SQL", "Data Modeling"), 83),
                new Job("ET", "ETL Developer", "Informatica", "Redwood City, CA", "100", "140", Arrays.asList("ETL", "SQL", "Data Warehousing"), 77),
                new Job("DW", "Data Warehouse Eng", "Snowflake", "San Mateo, CA", "125", "165", Arrays.asList("SQL", "Snowflake", "Performance"), 88),
                new Job("CV", "Computer Vision Eng", "Waymo", "Mountain View, CA", "135", "175", Arrays.asList("OpenCV", "Python", "Deep Learning"), 91),
                new Job("NL", "NLP Engineer", "Grammarly", "San Francisco, CA", "130", "170", Arrays.asList("NLP", "Python", "Transformers"), 89),
                new Job("RO", "Robotics Engineer", "Boston Dynamics", "Boston, MA", "120", "160", Arrays.asList("C++", "ROS", "Control Systems"), 84),
                new Job("AU", "Autonomous Systems", "Cruise", "San Francisco, CA", "135", "175", Arrays.asList("C++", "Perception", "Planning"), 87),
                new Job("IO", "IoT Engineer", "Philips", "Cambridge, MA", "105", "145", Arrays.asList("Embedded C", "MQTT", "Sensors"), 79),
                new Job("QU", "Quantum Computing", "IBM Quantum", "Yorktown Heights, NY", "140", "180", Arrays.asList("Quantum", "Python", "Physics"), 82),
                new Job("BI", "Bioinformatics", "23andMe", "Sunnyvale, CA", "110", "150", Arrays.asList("Python", "Genomics", "Statistics"), 80),
                new Job("HE", "HealthTech Engineer", "Teladoc", "Remote", "115", "155", Arrays.asList("React", "Node.js", "HIPAA"), 85),
                new Job("FI", "FinTech Developer", "Robinhood", "Menlo Park, CA", "130", "170", Arrays.asList("Java", "Trading Systems", "Low Latency"), 88),
                new Job("ED", "EdTech Developer", "Coursera", "Mountain View, CA", "110", "150", Arrays.asList("React", "Python", "Video Streaming"), 81),
                new Job("EC", "E-commerce Eng", "Walmart Labs", "Sunnyvale, CA", "115", "155", Arrays.asList("Java", "Microservices", "Scalability"), 83),
                new Job("SO", "Social Media Eng", "Twitter", "San Francisco, CA", "125", "165", Arrays.asList("Scala", "Distributed Systems", "Real-time"), 86),
                new Job("ST", "Streaming Engineer", "Twitch", "San Francisco, CA", "130", "170", Arrays.asList("Video", "CDN", "WebRTC"), 84),
                new Job("AD", "AdTech Engineer", "The Trade Desk", "New York, NY", "120", "160", Arrays.asList("Java", "Real-time Bidding", "Analytics"), 82),
                new Job("CY", "Cybersecurity Analyst", "Palo Alto Networks", "Santa Clara, CA", "110", "150", Arrays.asList("Security", "SIEM", "Threat Analysis"), 87),
                new Job("PE", "Penetration Tester", "HackerOne", "Remote", "115", "155", Arrays.asList("Security", "Ethical Hacking", "Python"), 85),
                new Job("CO", "Compliance Engineer", "OneTrust", "Atlanta, GA", "100", "140", Arrays.asList("Privacy", "GDPR", "Security"), 78),
                new Job("RI", "Risk Engineer", "Goldman Sachs", "New York, NY", "130", "170", Arrays.asList("Python", "Risk Modeling", "Finance"), 83),
                new Job("TR", "Trading Systems", "Jane Street", "New York, NY", "150", "220", Arrays.asList("OCaml", "Low Latency", "Finance"), 90),
                new Job("IN", "Infrastructure Eng", "DigitalOcean", "New York, NY", "120", "160", Arrays.asList("Linux", "Networking", "Automation"), 86),
                new Job("RE", "Release Engineer", "GitHub", "Remote", "115", "155", Arrays.asList("CI/CD", "Git", "Automation"), 84),
                new Job("TO", "Tools Engineer", "Unity Technologies", "San Francisco, CA", "110", "150", Arrays.asList("C#", "Unity", "Tooling"), 79),
                new Job("BU", "Build Engineer", "Epic Games", "Cary, NC", "105", "145", Arrays.asList("Jenkins", "Python", "Build Systems"), 77),
                new Job("LO", "Localization Eng", "Crowdin", "Remote", "90", "130", Arrays.asList("i18n", "JavaScript", "APIs"), 73),
                new Job("AC", "Accessibility Eng", "Deque Systems", "Remote", "100", "140", Arrays.asList("WCAG", "HTML/CSS", "Testing"), 81),
                new Job("PE", "Performance Eng", "Dynatrace", "Waltham, MA", "115", "155", Arrays.asList("Performance Testing", "APM", "Java"), 85),
                new Job("OB", "Observability Eng", "Datadog", "Boston, MA", "125", "165", Arrays.asList("Monitoring", "Metrics", "Go"), 88),
                new Job("SE", "Search Engineer", "Elastic", "Amsterdam, NL", "120", "160", Arrays.asList("Elasticsearch", "Java", "Distributed Systems"), 86),
                new Job("RE", "Recommendation Eng", "Pinterest", "San Francisco, CA", "130", "170", Arrays.asList("ML", "Scala", "Recommendation Systems"), 89),
                new Job("AB", "A/B Testing Eng", "Optimizely", "San Francisco, CA", "115", "155", Arrays.asList("Statistics", "Python", "Experimentation"), 82),
                new Job("GR", "Growth Engineer", "Dropbox", "San Francisco, CA", "120", "160", Arrays.asList("React", "Analytics", "A/B Testing"), 84)
        );
        

        // Create job list container
        jobsCountLabel = new Label("Showing " + allJobs.size() + " matching jobs");
        jobsCountLabel.setPadding(new Insets(5));
        jobsCountLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        jobListContainer = new VBox(15);
        jobListContainer.getChildren().add(jobsCountLabel);

        // Display all jobs initially
        for (Job job : allJobs) {
            jobListContainer.getChildren().add(JobCard(job));
        }

        ScrollPane scrollPane = new ScrollPane(jobListContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #ffffff;");
        root.setCenter(scrollPane);
    }

    // Filter implementation
    private void applyFilters() {
        List<Job> filtered = new ArrayList<>();

        // Get selected filters
        String selectedJobType = jobTypeCombo.getValue();

        // Get selected experience levels
        List<String> selectedExpLevels = new ArrayList<>();
        for (Node node : expOptions.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox cb = (CheckBox) node;
                if (cb.isSelected()) {
                    selectedExpLevels.add(cb.getText());
                }
            }
        }

        // Get selected locations
        List<String> selectedLocations = new ArrayList<>();
        for (Node node : locationBox.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox cb = (CheckBox) node;
                if (cb.isSelected()) {
                    selectedLocations.add(cb.getText());
                }
            }
        }

        // Get salary range
        int minSalary = 0;
        int maxSalary = Integer.MAX_VALUE;
        try {
            if (!minSalaryField.getText().trim().isEmpty()) {
                minSalary = Integer.parseInt(minSalaryField.getText().trim());
            }
            if (!maxSalaryField.getText().trim().isEmpty()) {
                maxSalary = Integer.parseInt(maxSalaryField.getText().trim());
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary input");
        }

        // Apply filters
        for (Job job : allJobs) {
            boolean matches = true;

            // Filter by location
            if (!selectedLocations.isEmpty()) {
                boolean locationMatch = false;
                for (String loc : selectedLocations) {
                    if (job.getLocation().contains(loc)) {
                        locationMatch = true;
                        break;
                    }
                }
                if (!locationMatch) {
                    matches = false;
                }
            }

            // Filter by salary
            try {
                int jobMinSalary = Integer.parseInt(job.getSalaryMin());
                if (jobMinSalary < minSalary || jobMinSalary > maxSalary) {
                    matches = false;
                }
            } catch (NumberFormatException e) {}

            if (matches) {
                filtered.add(job);
            }
        }

        // Update display
        updateJobList(filtered);
        System.out.println("Filters applied: " + filtered.size() + " jobs found");
    }

    // Reset filters
    private void resetFilters() {
        jobTypeCombo.getSelectionModel().clearSelection();
        minSalaryField.clear();
        maxSalaryField.clear();

        for (Node node : expOptions.getChildren()) {
            if (node instanceof CheckBox) {
                ((CheckBox) node).setSelected(false);
            }
        }

        for (Node node : locationBox.getChildren()) {
            if (node instanceof CheckBox) {
                ((CheckBox) node).setSelected(false);
            }
        }

        updateJobList(allJobs);
        System.out.println("Filters reset: showing all " + allJobs.size() + " jobs");
    }

    // Update the job list display
    private void updateJobList(List<Job> jobs) {
        jobListContainer.getChildren().clear();

        jobsCountLabel.setText("Showing " + jobs.size() + " matching jobs");
        jobListContainer.getChildren().add(jobsCountLabel);

        if (jobs.isEmpty()) {
            Label noJobs = new Label("No jobs match your filters.");
            noJobs.setStyle("-fx-font-size: 14px; -fx-text-fill: #666666; -fx-padding: 20;");
            jobListContainer.getChildren().add(noJobs);
        } else {
            for (Job job : jobs) {
                jobListContainer.getChildren().add(JobCard(job));
            }
        }
    }

    public BorderPane setJobListingPage() {
        return root;
    }

    public void setUser(User user) {
        navbar.setUserProfile(user.getName());
    }

    public void change(Stage stage) {
        BorderPane layout = new BorderPane();
        NavigationBar navbar2 = new NavigationBar();
        navbar2.setActive("Post");
        PostJobPage page = new PostJobPage();
        layout.setTop(navbar2.getView());
        layout.setCenter(page.getView());
        stage.setScene(new Scene(layout, 1100, 700));
    }

    public VBox JobCard(Job job) {
        Label title = new Label(job.getJobTitle());
        title.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

        Label company = new Label(job.getCompany() + " • " + job.getLocation());
        Label salary = new Label(job.getSalaryRange());
        salary.setTextFill(Color.BLUE);

        Button apply = new Button("Apply Now");
        apply.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white;");
        apply.setOnAction(e -> System.out.println("Applied to: " + job.getJobTitle()));

        Button save = new Button("Save");
        save.setOnAction(e -> System.out.println("Saved: " + job.getJobTitle()));

        HBox select = new HBox(10, apply, save);
        VBox jobCard = new VBox(10, title, company, salary, select);
        jobCard.setSpacing(10);
        jobCard.setPadding(new Insets(15));
        jobCard.setStyle("-fx-padding: 15; -fx-background-color: white; "
                + "-fx-border-color: #ddd; -fx-border-radius: 10; -fx-background-radius: 10;");
        return jobCard;
    }
}
