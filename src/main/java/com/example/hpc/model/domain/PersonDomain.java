package com.example.hpc.model.domain;

import java.util.List;

public class PersonDomain extends DomainBase {
    private String firstName;

    private String lastName;

    private String studyField;

    private String nationalCode;

    private String personalImage;

    private String phoneNumber;

    private String faculty;

    private UserDomain user;

    private WalletDomain wallet;

    private List<JobDomain> jobs;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudyField() {
        return studyField;
    }

    public void setStudyField(String studyField) {
        this.studyField = studyField;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getPersonalImage() {
        return personalImage;
    }

    public void setPersonalImage(String personalImage) {
        this.personalImage = personalImage;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public UserDomain getUser() {
        return user;
    }

    public void setUser(UserDomain user) {
        this.user = user;
    }

    public WalletDomain getWallet() {
        return wallet;
    }

    public void setWallet(WalletDomain wallet) {
        this.wallet = wallet;
    }

    public List<JobDomain> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobDomain> jobs) {
        this.jobs = jobs;
    }
}
