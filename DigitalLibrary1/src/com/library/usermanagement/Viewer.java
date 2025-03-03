package com.library.usermanagement;

public class Viewer {
    private int viewerId;
    private String name;
    private String email;

    public Viewer(int viewerId, String name, String email) {
        this.viewerId = viewerId;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public int getViewerId() { return viewerId; }
    public void setViewerId(int viewerId) { this.viewerId = viewerId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
