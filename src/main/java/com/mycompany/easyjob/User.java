/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.easyjob;

/**
 *
 * @author alish
 */
public class User {

    private String name;
    private String email;
    private String password;
    private String role;

    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getName()
    { 
        return name; 
    }
    public String getEmail()
    {
        return email; 
    }
    public String getPassword()
    { 
        return password; 
    }
    public String getRole()
    { 
        return role; 
    }

    public String toString() 
    {
        return "User: " + name + ", " + email + ", " + role;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
