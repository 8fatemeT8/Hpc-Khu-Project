package com.example.hpc.model.dto;

import org.springframework.web.multipart.MultipartFile;

public class JobDto extends DtoBase{
    private String  name;

    private MultipartFile jobFile;

    private Boolean advanceMode;

    private String description;

    private Integer plan;

    private Integer application;

    private long nodeNumber;

    private long coreNumber;

    private long ramInGB;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getJobFile() {
        return jobFile;
    }

    public void setJobFile(MultipartFile jobFile) {
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

    public Integer getPlan() {
        return plan;
    }

    public void setPlan(Integer plan) {
        this.plan = plan;
    }

    public Integer getApplication() {
        return application;
    }

    public void setApplication(Integer application) {
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
