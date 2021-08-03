package com.example.hpc.model.entity;

import com.example.hpc.utils.enums.ApplicationType;
import com.example.hpc.utils.enums.JobPlan;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "jobs")
public class Job extends EntityBase {

    private String name;

    @Column(name = "job_file")
    private String jobFile;

    @Column(name = "advance_mode")
    private Boolean advanceMode;

    private String description;

    private JobPlan plan;

    private ApplicationType application;

    @Column(name = "node_number")
    private long nodeNumber;

    @Column(name = "core_number")
    private long coreNumber;

    @Column(name = "ram_in_GB")
    private long ramInGB;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany
    private List<JobResult> jobResults;


    public List<JobResult> getJobResults() {
        return jobResults;
    }

    public void setJobResults(List<JobResult> jobResults) {
        this.jobResults = jobResults;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobFile() {
        return jobFile;
    }

    public void setJobFile(String jobFile) {
        this.jobFile = jobFile;
    }

    public Boolean getAdvanceMode() {
        return advanceMode;
    }

    public void setAdvanceMode(Boolean advanceMode) {
        this.advanceMode = advanceMode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JobPlan getPlan() {
        return plan;
    }

    public void setPlan(JobPlan plan) {
        this.plan = plan;
    }

    public ApplicationType getApplication() {
        return application;
    }

    public void setApplication(ApplicationType application) {
        this.application = application;
    }

    public long getNodeNumber() {
        return nodeNumber;
    }

    public void setNodeNumber(long nodeNumber) {
        this.nodeNumber = nodeNumber;
    }

    public long getCoreNumber() {
        return coreNumber;
    }

    public void setCoreNumber(long coreNumber) {
        this.coreNumber = coreNumber;
    }

    public long getRamInGB() {
        return ramInGB;
    }

    public void setRamInGB(long ramInGB) {
        this.ramInGB = ramInGB;
    }
}
