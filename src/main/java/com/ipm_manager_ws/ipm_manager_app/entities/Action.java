package com.ipm_manager_ws.ipm_manager_app.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;

@Entity
@Table(name = "actions")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_action")
    private LocalDateTime date;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "report_id", referencedColumnName = "id")
    private Report report;

    @Column(name = "usernames")
    private String username;

    @NotBlank(message = "Title cannot be blank")
    @NotEmpty
    @Column(name = "actionTitles")
    private String actionTitle;

    @NotBlank(message = "Desciption cannot be blank")
    @NotEmpty
    @Column(name = "actionDescriptions", nullable = false)
    private String actionDescription;

    public Action(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActionTitle() {
        return actionTitle;
    }

    public void setActionTitle(String actionTitle) {
        this.actionTitle = actionTitle;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    

    
}
