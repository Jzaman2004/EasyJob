/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.easyjob;

import java.util.List;

/**
 * Job Model Class
 * @author Jawad Zaman
 */
public class Job {
    private String companyLogo;
    private String jobTitle;
    private String company;
    private String location;
    private String salaryMin;
    private String salaryMax;
    private List<String> requiredSkills;
    private int matchPercentage;
    
    public Job(String companyLogo, String jobTitle, String company, String location, 
               String salaryMin, String salaryMax, List<String> requiredSkills, int matchPercentage) {
        this.companyLogo = companyLogo;
        this.jobTitle = jobTitle;
        this.company = company;
        this.location = location;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.requiredSkills = requiredSkills;
        this.matchPercentage = matchPercentage;
    }
    
    // Getters
    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public int getMatchPercentage() {
        return matchPercentage;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public String getLocation() {
        return location;
    }

    public String getSalaryMin() {
        return salaryMin;
    }

    public String getSalaryMax() {
        return salaryMax;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }
    
    // Get formatted salary range
    public String getSalaryRange() {
        return "$" + salaryMin + "k - $" + salaryMax + "k / year";
    }
}
