package com.example.hpc.utils.criteria;


public class JobCriteria {
    private StringFilter name;

    private Boolean advanceMode;

    private StringFilter description;

    private IdFilter plan;

    private IdFilter application;

    private IdFilter nodeNumber;

    private IdFilter coreNumber;

    private IdFilter ramInGB;

    private PersonCriteria person;

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public Boolean getAdvanceMode() {
        return advanceMode;
    }

    public void setAdvanceMode(Boolean advanceMode) {
        this.advanceMode = advanceMode;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public IdFilter getPlan() {
        return plan;
    }

    public void setPlan(IdFilter plan) {
        this.plan = plan;
    }

    public IdFilter getApplication() {
        return application;
    }

    public void setApplication(IdFilter application) {
        this.application = application;
    }

    public IdFilter getNodeNumber() {
        return nodeNumber;
    }

    public void setNodeNumber(IdFilter nodeNumber) {
        this.nodeNumber = nodeNumber;
    }

    public IdFilter getCoreNumber() {
        return coreNumber;
    }

    public void setCoreNumber(IdFilter coreNumber) {
        this.coreNumber = coreNumber;
    }

    public IdFilter getRamInGB() {
        return ramInGB;
    }

    public void setRamInGB(IdFilter ramInGB) {
        this.ramInGB = ramInGB;
    }

    public PersonCriteria getPerson() {
        return person;
    }

    public void setPerson(PersonCriteria person) {
        this.person = person;
    }
}
