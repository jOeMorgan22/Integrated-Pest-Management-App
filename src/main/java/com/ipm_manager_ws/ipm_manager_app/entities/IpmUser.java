package com.ipm_manager_ws.ipm_manager_app.entities;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



@Entity
@Table(name = "users")
public class IpmUser {

    public IpmUser(String username, String email, String usdaZone, String password, String role) {
        this.username = username;
        this.email = email;
        this.usdaZone = usdaZone;
        this.password = password;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private Long id;

    @Column(name = "group_names")
    private String groupName;
    
    @Column(name = "group_codes")
    private String groupCode;

    @Column(name = "usernames", nullable = false, unique = true)
    private String username;

    @Column(name = "user_emails", nullable = false, unique = true)
    private String email;

    @Column(name = "verified", nullable = false)
    private boolean isVerified;

    @Column(name = "usda_zones", nullable = false)
    private String usdaZone;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "passwords", nullable = false)
    private String password;

    @Column(name = "roles")
    private String role;

    @Column(name = "authorities")
    @JsonIgnore
    private List<String> authorities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Report> reports;

    public IpmUser(){

    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsdaZone() {
        return usdaZone;
    }
    public void setUsdaZone(String usdaZone) {
        this.usdaZone = usdaZone;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void addAuthority(String authority){
        this.authorities.add(authority);
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
   
    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "IpmUser [id=" + id + 
                ", username=" + username + 
                ", usdaZone=" + usdaZone + 
                "]";
    }

}
