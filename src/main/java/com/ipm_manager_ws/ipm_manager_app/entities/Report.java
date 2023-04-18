package com.ipm_manager_ws.ipm_manager_app.entities;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;

    @Entity
    @Table(name = "reports")
    public class Report {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "group_codes")
    private String groupCode;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private IpmUser user;

    @Column(name = "reportTitles")
    private String reportTitle;
  
    @Column(name = "reportDescriptions")
    private String reportDescription;

    @Past
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "date_of_report")
    private LocalDateTime date;

    @Column(name = "resolved")
    private boolean resolved;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    private List<Action> actions;

    public Report(){

    }

    public Report(String groupCode, String reportTitle, String reportDescription, IpmUser user) {
        this.reportTitle = reportTitle;
        this.reportDescription = reportDescription;
        this.user = user;
        this.groupCode = groupCode;
        this.date = LocalDateTime.now();
        this.resolved = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public IpmUser getUser() {
        return user;
    }

    public void setUser(IpmUser user) {
        this.user = user;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public void setAction(List<Action> actions) {
        this.actions = actions;
    }

    @JsonManagedReference
    public List<Action> getAction() {
        return actions;
    }


}
