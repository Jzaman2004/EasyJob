package com.mycompany.easyjob;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JobMatchmakingPage {

    private VBox mainLayout;
    private NavigationBar navBar;
    private VBox skipButton;
    private VBox interestedButton;
    private Label jobCounterLabel;

    private List<Job> jobs;
    private int currentJobIndex;
    private VBox jobCardContainer;

    // Use MatchListener interface
    private MatchListener matchListener;

    public JobMatchmakingPage() {
        navBar = new NavigationBar();
        navBar.setActive("Jobs");
        mainLayout = new VBox();
        mainLayout.setStyle("-fx-background-color: white;");
        initializeJobs();
        currentJobIndex = 0;
        createPage();
    }

    private void initializeJobs() {
        jobs = new ArrayList<>();
        jobs = new ArrayList<>();
        jobs.add(new Job("TC", "Senior Software Engineer", "TechCorp Inc.", "San Francisco, CA", "140", "180", Arrays.asList("Python", "AWS", "Kubernetes"), 87));
        jobs.add(new Job("DS", "Data Scientist", "DataFlow Analytics", "New York, NY", "120", "160", Arrays.asList("Python", "R", "Machine Learning"), 92));
        jobs.add(new Job("MR", "Research Scientist", "Microsoft Research", "Seattle, WA", "130", "170", Arrays.asList("AI/ML", "Python", "Deep Learning"), 85));
        jobs.add(new Job("FE", "Frontend Engineer", "Google", "Mountain View, CA", "135", "175", Arrays.asList("React", "TypeScript", "CSS"), 78));
        jobs.add(new Job("BE", "Backend Engineer", "Amazon", "Austin, TX", "125", "165", Arrays.asList("Java", "AWS", "Microservices"), 90));
        jobs.add(new Job("ML", "ML Engineer", "Meta", "Menlo Park, CA", "145", "185", Arrays.asList("PyTorch", "Python", "NLP"), 88));
        jobs.add(new Job("SE", "Security Engineer", "Cisco", "San Jose, CA", "130", "170", Arrays.asList("Cybersecurity", "Network", "Python"), 82));
        jobs.add(new Job("DE", "DevOps Engineer", "Netflix", "Los Gatos, CA", "140", "180", Arrays.asList("Docker", "Kubernetes", "CI/CD"), 91));
        jobs.add(new Job("AP", "Android Developer", "Spotify", "New York, NY", "115", "155", Arrays.asList("Kotlin", "Java", "Android SDK"), 84));
        jobs.add(new Job("IO", "iOS Developer", "Uber", "San Francisco, CA", "120", "160", Arrays.asList("Swift", "UIKit", "SwiftUI"), 79));
        jobs.add(new Job("FS", "Full Stack Developer", "Airbnb", "Remote", "125", "165", Arrays.asList("React", "Node.js", "PostgreSQL"), 93));
        jobs.add(new Job("QA", "QA Engineer", "Salesforce", "Seattle, WA", "100", "140", Arrays.asList("Selenium", "Java", "Testing"), 76));
        jobs.add(new Job("PM", "Product Manager", "LinkedIn", "San Francisco, CA", "130", "170", Arrays.asList("Agile", "SQL", "Analytics"), 81));
        jobs.add(new Job("UX", "UX Designer", "Adobe", "San Jose, CA", "110", "150", Arrays.asList("Figma", "User Research", "Prototyping"), 88));
        jobs.add(new Job("DB", "Database Admin", "Oracle", "Austin, TX", "105", "145", Arrays.asList("Oracle", "SQL", "Performance Tuning"), 73));
        jobs.add(new Job("CL", "Cloud Architect", "IBM", "Remote", "145", "185", Arrays.asList("AWS", "Azure", "Cloud Security"), 90));
        jobs.add(new Job("BI", "BI Analyst", "Tableau", "Seattle, WA", "95", "135", Arrays.asList("SQL", "Tableau", "Data Visualization"), 85));
        jobs.add(new Job("SC", "Scrum Master", "Atlassian", "Austin, TX", "100", "140", Arrays.asList("Agile", "Jira", "Team Leadership"), 77));
        jobs.add(new Job("NT", "Network Engineer", "Juniper Networks", "San Jose, CA", "110", "150", Arrays.asList("Cisco", "Routing", "Firewalls"), 82));
        jobs.add(new Job("EM", "Embedded Engineer", "Tesla", "Palo Alto, CA", "120", "160", Arrays.asList("C++", "RTOS", "Hardware"), 86));
        jobs.add(new Job("AI", "AI Researcher", "OpenAI", "San Francisco, CA", "150", "200", Arrays.asList("Python", "TensorFlow", "Research"), 94));
        jobs.add(new Job("BL", "Blockchain Dev", "Coinbase", "Remote", "130", "170", Arrays.asList("Solidity", "Web3", "Smart Contracts"), 80));
        jobs.add(new Job("GA", "Game Developer", "Electronic Arts", "Los Angeles, CA", "105", "145", Arrays.asList("Unity", "C#", "3D Graphics"), 75));
        jobs.add(new Job("VR", "VR Engineer", "Meta", "Menlo Park, CA", "125", "165", Arrays.asList("Unity", "C++", "3D Math"), 83));
        jobs.add(new Job("AR", "AR Developer", "Snap Inc.", "Los Angeles, CA", "115", "155", Arrays.asList("ARKit", "Unity", "Computer Vision"), 78));
        jobs.add(new Job("DT", "Data Engineer", "Databricks", "San Francisco, CA", "130", "170", Arrays.asList("Spark", "Python", "ETL"), 91));
        jobs.add(new Job("ML", "MLOps Engineer", "Hugging Face", "Remote", "125", "165", Arrays.asList("Docker", "Python", "Kubernetes"), 87));
        jobs.add(new Job("SI", "Site Reliability Eng", "Google", "Mountain View, CA", "140", "180", Arrays.asList("Linux", "Python", "Monitoring"), 89));
        jobs.add(new Job("PL", "Platform Engineer", "Stripe", "San Francisco, CA", "135", "175", Arrays.asList("Go", "Kubernetes", "API Design"), 92));
        jobs.add(new Job("IN", "Integration Eng", "Zapier", "Remote", "110", "150", Arrays.asList("APIs", "Python", "Webhooks"), 84));
        jobs.add(new Job("FR", "Frontend Lead", "Shopify", "Toronto, ON", "120", "160", Arrays.asList("React", "GraphQL", "TypeScript"), 86));
        jobs.add(new Job("BK", "Backend Lead", "Twilio", "Denver, CO", "130", "170", Arrays.asList("Java", "Microservices", "Redis"), 88));
        jobs.add(new Job("MO", "Mobile Lead", "Square", "San Francisco, CA", "135", "175", Arrays.asList("Swift", "Kotlin", "Mobile Architecture"), 90));
        jobs.add(new Job("CS", "Customer Success Eng", "Zendesk", "Remote", "90", "130", Arrays.asList("JavaScript", "APIs", "Customer Support"), 72));
        jobs.add(new Job("TE", "Technical Writer", "GitLab", "Remote", "85", "125", Arrays.asList("Documentation", "Markdown", "Git"), 70));
        jobs.add(new Job("SU", "Support Engineer", "MongoDB", "Austin, TX", "95", "135", Arrays.asList("MongoDB", "Linux", "Troubleshooting"), 74));
        jobs.add(new Job("SO", "Solutions Architect", "AWS", "Seattle, WA", "140", "180", Arrays.asList("AWS", "Architecture", "Client Facing"), 91));
        jobs.add(new Job("PR", "Principal Engineer", "Netflix", "Los Gatos, CA", "180", "240", Arrays.asList("System Design", "Java", "Leadership"), 95));
        jobs.add(new Job("ST", "Staff Engineer", "Uber", "San Francisco, CA", "160", "210", Arrays.asList("Distributed Systems", "Go", "Mentoring"), 93));
        jobs.add(new Job("EN", "Engineering Manager", "Airbnb", "San Francisco, CA", "150", "200", Arrays.asList("Leadership", "Agile", "Technical Strategy"), 87));
        jobs.add(new Job("VP", "VP Engineering", "Stripe", "San Francisco, CA", "200", "280", Arrays.asList("Leadership", "Strategy", "Scaling"), 89));
        jobs.add(new Job("CT", "CTO", "Startup XYZ", "Remote", "180", "250", Arrays.asList("Strategy", "Architecture", "Team Building"), 85));
        jobs.add(new Job("IN", "Intern Software Eng", "Microsoft", "Redmond, WA", "60", "80", Arrays.asList("Python", "Java", "Learning"), 78));
        jobs.add(new Job("JR", "Junior Developer", "Accenture", "Chicago, IL", "70", "95", Arrays.asList("JavaScript", "HTML/CSS", "Git"), 75));
        jobs.add(new Job("SR", "Senior Data Analyst", "Facebook", "Menlo Park, CA", "115", "155", Arrays.asList("SQL", "Python", "Statistics"), 86));
        jobs.add(new Job("DS", "Data Science Manager", "Amazon", "Seattle, WA", "145", "190", Arrays.asList("ML", "Team Leadership", "Python"), 90));
        jobs.add(new Job("AN", "Analytics Engineer", "Fivetran", "Remote", "110", "150", Arrays.asList("dbt", "SQL", "Data Modeling"), 83));
        jobs.add(new Job("ET", "ETL Developer", "Informatica", "Redwood City, CA", "100", "140", Arrays.asList("ETL", "SQL", "Data Warehousing"), 77));
        jobs.add(new Job("DW", "Data Warehouse Eng", "Snowflake", "San Mateo, CA", "125", "165", Arrays.asList("SQL", "Snowflake", "Performance"), 88));
        jobs.add(new Job("CV", "Computer Vision Eng", "Waymo", "Mountain View, CA", "135", "175", Arrays.asList("OpenCV", "Python", "Deep Learning"), 91));
        jobs.add(new Job("NL", "NLP Engineer", "Grammarly", "San Francisco, CA", "130", "170", Arrays.asList("NLP", "Python", "Transformers"), 89));
        jobs.add(new Job("RO", "Robotics Engineer", "Boston Dynamics", "Boston, MA", "120", "160", Arrays.asList("C++", "ROS", "Control Systems"), 84));
        jobs.add(new Job("AU", "Autonomous Systems", "Cruise", "San Francisco, CA", "135", "175", Arrays.asList("C++", "Perception", "Planning"), 87));
        jobs.add(new Job("IO", "IoT Engineer", "Philips", "Cambridge, MA", "105", "145", Arrays.asList("Embedded C", "MQTT", "Sensors"), 79));
        jobs.add(new Job("QU", "Quantum Computing", "IBM Quantum", "Yorktown Heights, NY", "140", "180", Arrays.asList("Quantum", "Python", "Physics"), 82));
        jobs.add(new Job("BI", "Bioinformatics", "23andMe", "Sunnyvale, CA", "110", "150", Arrays.asList("Python", "Genomics", "Statistics"), 80));
        jobs.add(new Job("HE", "HealthTech Engineer", "Teladoc", "Remote", "115", "155", Arrays.asList("React", "Node.js", "HIPAA"), 85));
        jobs.add(new Job("FI", "FinTech Developer", "Robinhood", "Menlo Park, CA", "130", "170", Arrays.asList("Java", "Trading Systems", "Low Latency"), 88));
        jobs.add(new Job("ED", "EdTech Developer", "Coursera", "Mountain View, CA", "110", "150", Arrays.asList("React", "Python", "Video Streaming"), 81));
        jobs.add(new Job("EC", "E-commerce Eng", "Walmart Labs", "Sunnyvale, CA", "115", "155", Arrays.asList("Java", "Microservices", "Scalability"), 83));
        jobs.add(new Job("SO", "Social Media Eng", "Twitter", "San Francisco, CA", "125", "165", Arrays.asList("Scala", "Distributed Systems", "Real-time"), 86));
        jobs.add(new Job("ST", "Streaming Engineer", "Twitch", "San Francisco, CA", "130", "170", Arrays.asList("Video", "CDN", "WebRTC"), 84));
        jobs.add(new Job("AD", "AdTech Engineer", "The Trade Desk", "New York, NY", "120", "160", Arrays.asList("Java", "Real-time Bidding", "Analytics"), 82));
        jobs.add(new Job("CY", "Cybersecurity Analyst", "Palo Alto Networks", "Santa Clara, CA", "110", "150", Arrays.asList("Security", "SIEM", "Threat Analysis"), 87));
        jobs.add(new Job("PE", "Penetration Tester", "HackerOne", "Remote", "115", "155", Arrays.asList("Security", "Ethical Hacking", "Python"), 85));
        jobs.add(new Job("CO", "Compliance Engineer", "OneTrust", "Atlanta, GA", "100", "140", Arrays.asList("Privacy", "GDPR", "Security"), 78));
        jobs.add(new Job("RI", "Risk Engineer", "Goldman Sachs", "New York, NY", "130", "170", Arrays.asList("Python", "Risk Modeling", "Finance"), 83));
        jobs.add(new Job("TR", "Trading Systems", "Jane Street", "New York, NY", "150", "220", Arrays.asList("OCaml", "Low Latency", "Finance"), 90));
        jobs.add(new Job("IN", "Infrastructure Eng", "DigitalOcean", "New York, NY", "120", "160", Arrays.asList("Linux", "Networking", "Automation"), 86));
        jobs.add(new Job("RE", "Release Engineer", "GitHub", "Remote", "115", "155", Arrays.asList("CI/CD", "Git", "Automation"), 84));
        jobs.add(new Job("TO", "Tools Engineer", "Unity Technologies", "San Francisco, CA", "110", "150", Arrays.asList("C#", "Unity", "Tooling"), 79));
        jobs.add(new Job("BU", "Build Engineer", "Epic Games", "Cary, NC", "105", "145", Arrays.asList("Jenkins", "Python", "Build Systems"), 77));
        jobs.add(new Job("LO", "Localization Eng", "Crowdin", "Remote", "90", "130", Arrays.asList("i18n", "JavaScript", "APIs"), 73));
        jobs.add(new Job("AC", "Accessibility Eng", "Deque Systems", "Remote", "100", "140", Arrays.asList("WCAG", "HTML/CSS", "Testing"), 81));
        jobs.add(new Job("PE", "Performance Eng", "Dynatrace", "Waltham, MA", "115", "155", Arrays.asList("Performance Testing", "APM", "Java"), 85));
        jobs.add(new Job("OB", "Observability Eng", "Datadog", "Boston, MA", "125", "165", Arrays.asList("Monitoring", "Metrics", "Go"), 88));
        jobs.add(new Job("SE", "Search Engineer", "Elastic", "Amsterdam, NL", "120", "160", Arrays.asList("Elasticsearch", "Java", "Distributed Systems"), 86));
        jobs.add(new Job("RE", "Recommendation Eng", "Pinterest", "San Francisco, CA", "130", "170", Arrays.asList("ML", "Scala", "Recommendation Systems"), 89));
        jobs.add(new Job("AB", "A/B Testing Eng", "Optimizely", "San Francisco, CA", "115", "155", Arrays.asList("Statistics", "Python", "Experimentation"), 82));
        jobs.add(new Job("GR", "Growth Engineer", "Dropbox", "San Francisco, CA", "120", "160", Arrays.asList("React", "Analytics", "A/B Testing"), 84));
        }

    private void createPage() {
        mainLayout.getChildren().add(navBar.getView());

        VBox content = new VBox(20);
        content.setPadding(new Insets(30, 60, 40, 60));

        Label titleLabel = new Label("Job Matchmaking");
        titleLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #1a1a2e;");

        Label subtitleLabel = new Label("Swipe right to express interest, swipe left to skip. We'll notify you when it's a mutual match!");
        subtitleLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #666666;");
        subtitleLabel.setWrapText(true);

        HBox matchmakingArea = new HBox(40);
        matchmakingArea.setAlignment(Pos.CENTER);
        matchmakingArea.setPadding(new Insets(20, 0, 20, 0));

        skipButton = createActionButton("✕", "#fecaca", "#ef4444", "Skip");

        jobCardContainer = new VBox();
        jobCardContainer.setPrefWidth(500);
        updateJobCard();

        interestedButton = createActionButton("✓", "#93c5fd", "#3b82f6", "Interested");

        matchmakingArea.getChildren().addAll(skipButton, jobCardContainer, interestedButton);

        // ✅ Store direct reference to counter label
        jobCounterLabel = new Label((currentJobIndex + 1) + " of " + jobs.size());
        jobCounterLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #999999;");
        jobCounterLabel.setId("jobCounter");

        content.getChildren().addAll(titleLabel, subtitleLabel, matchmakingArea, jobCounterLabel);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        mainLayout.getChildren().addAll(content, spacer);
    }

    private void updateJobCard() {
        if (currentJobIndex >= jobs.size()) {
            jobCardContainer.getChildren().clear();
            Label noMore = new Label("No more jobs available!");
            noMore.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #666666;");
            jobCardContainer.getChildren().add(noMore);
            jobCardContainer.setAlignment(Pos.CENTER);
            jobCardContainer.setPrefHeight(400);
            return;
        }

        jobCardContainer.getChildren().clear();
        jobCardContainer.getChildren().add(createJobCard(jobs.get(currentJobIndex)));
        jobCardContainer.setAlignment(Pos.TOP_CENTER);

        // Direct update using stored reference 
        if (jobCounterLabel != null) {
            jobCounterLabel.setText((currentJobIndex + 1) + " of " + jobs.size());
        }
    }

    private VBox createJobCard(Job job) {
        VBox card = new VBox();
        card.setStyle("-fx-background-color: white; -fx-border-color: #e5e7eb; "
                + "-fx-border-radius: 16px; -fx-background-radius: 16px; "
                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 20, 0, 0, 4);");

        StackPane logoHeader = new StackPane();
        logoHeader.setPrefHeight(120);
        logoHeader.setStyle("-fx-background-color: linear-gradient(to bottom, #3b82f6, #8b5cf6);");

        VBox logoContainer = new VBox();
        logoContainer.setAlignment(Pos.CENTER);
        logoContainer.setPrefSize(70, 70);
        logoContainer.setStyle("-fx-background-color: white; -fx-background-radius: 12px;");

        Label logoLabel = new Label(job.getCompanyLogo());
        logoLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #3b82f6;");
        logoContainer.getChildren().add(logoLabel);
        logoHeader.getChildren().add(logoContainer);

        VBox jobDetails = new VBox(15);
        jobDetails.setPadding(new Insets(25, 30, 25, 30));

        Label jobTitle = new Label(job.getJobTitle());
        jobTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #1a1a2e;");

        HBox companyLocation = new HBox(10);
        companyLocation.setAlignment(Pos.CENTER);
        Label companyLabel = new Label(job.getCompany());
        companyLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #3b82f6;");
        Label sep = new Label("|");
        sep.setStyle("-fx-font-size: 14px; -fx-text-fill: #999999;");
        Label locationLabel = new Label(job.getLocation());
        locationLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #3b82f6;");
        companyLocation.getChildren().addAll(companyLabel, sep, locationLabel);

        Label salaryLabel = new Label(job.getSalaryRange());
        salaryLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #059669;");

        Region separatorLine = new Region();
        separatorLine.setPrefHeight(1);
        separatorLine.setStyle("-fx-background-color: #e5e7eb;");

        VBox skillsSection = new VBox(10);
        skillsSection.setAlignment(Pos.CENTER);

        Label skillsTitle = new Label("Required Skills");
        skillsTitle.setStyle("-fx-font-size: 12px; -fx-text-fill: #666666;");

        HBox skillsContainer = new HBox(10);
        skillsContainer.setAlignment(Pos.CENTER);

        String[] skillBgColors = {"#dbeafe", "#fef3c7", "#d1fae5", "#fce7f3", "#e0e7ff"};
        String[] skillTextColors = {"#3b82f6", "#f59e0b", "#10b981", "#ec4899", "#6366f1"};

        List<String> skills = job.getRequiredSkills();
        for (int i = 0; i < skills.size(); i++) {
            skillsContainer.getChildren().add(
                    createSkillTag(skills.get(i), skillBgColors[i % skillBgColors.length], skillTextColors[i % skillTextColors.length])
            );
        }

        Label matchLabel = new Label(job.getMatchPercentage() + "% Match");
        matchLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #059669; "
                + "-fx-background-color: #d1fae5; -fx-background-radius: 20px; "
                + "-fx-padding: 8px 20px; -fx-border-color: #10b981; -fx-border-width: 1px; "
                + "-fx-border-radius: 20px;");

        skillsSection.getChildren().addAll(skillsTitle, skillsContainer, matchLabel);
        jobDetails.getChildren().addAll(jobTitle, companyLocation, salaryLabel, separatorLine, skillsSection);
        card.getChildren().addAll(logoHeader, jobDetails);
        return card;
    }

    private HBox createSkillTag(String skill, String bgColor, String textColor) {
        Label tag = new Label(skill);
        tag.setStyle("-fx-background-color: " + bgColor + "; -fx-text-fill: " + textColor + "; "
                + "-fx-background-radius: 12px; -fx-padding: 6px 14px; "
                + "-fx-font-size: 12px; -fx-font-weight: bold;");
        HBox container = new HBox();
        container.getChildren().add(tag);
        return container;
    }

    private VBox createActionButton(String symbol, String bgColor, String borderColor, String labelText) {
        VBox buttonContainer = new VBox(10);
        buttonContainer.setAlignment(Pos.CENTER);

        Circle circle = new Circle(50);
        circle.setFill(Color.web(bgColor));
        circle.setStroke(Color.web(borderColor));
        circle.setStrokeWidth(2);

        Label symbolLabel = new Label(symbol);
        symbolLabel.setStyle("-fx-font-size: 32px; -fx-text-fill: " + borderColor + "; -fx-font-weight: bold;");

        StackPane circleContainer = new StackPane();
        circleContainer.getChildren().addAll(circle, symbolLabel);
        circleContainer.setPrefSize(100, 100);

        Label label = new Label(labelText);
        label.setStyle("-fx-font-size: 13px; -fx-text-fill: " + borderColor + ";");

        buttonContainer.getChildren().addAll(circleContainer, label);
        buttonContainer.setStyle("-fx-cursor: hand;");
        buttonContainer.setOnMouseEntered(e -> {
            circle.setOpacity(0.8);
            symbolLabel.setScaleX(1.1);
            symbolLabel.setScaleY(1.1);
        });
        buttonContainer.setOnMouseExited(e -> {
            circle.setOpacity(1.0);
            symbolLabel.setScaleX(1.0);
            symbolLabel.setScaleY(1.0);
        });

        return buttonContainer;
    }

    public void setUser(User user) {
        navBar.setUserProfile(user.getName());
    }

    public VBox getView() {
        return mainLayout;
    }

    public VBox getSkipButton() {
        return skipButton;
    }

    public VBox getInterestedButton() {
        return interestedButton;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public int getCurrentJobIndex() {
        return currentJobIndex;
    }

    public void setOnSkipAction(MatchListener listener) {
    this.matchListener = listener;
    skipButton.setOnMouseClicked(e -> {
        if (currentJobIndex < jobs.size() && matchListener != null) {
            Job currentJob = jobs.get(currentJobIndex);
            matchListener.onSkip(currentJob);
        }
        currentJobIndex++;
        updateJobCard();
    });
}

public void setOnInterestedAction(MatchListener listener) {
    this.matchListener = listener;
    interestedButton.setOnMouseClicked(e -> {
        if (currentJobIndex < jobs.size() && matchListener != null) {
            Job currentJob = jobs.get(currentJobIndex);
            matchListener.onInterested(currentJob);
        }
        currentJobIndex++;
        updateJobCard();
    });
}
}
