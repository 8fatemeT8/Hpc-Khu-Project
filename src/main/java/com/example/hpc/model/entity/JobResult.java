package com.example.hpc.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "job_results")
public class JobResult extends EntityBase {

    @Column(name = "execute_time")
    private long executeTime;

    private String resultFileName;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    public String getResultFileName() {
        return resultFileName;
    }

    public void setResultFileName(String resultFileName) {
        this.resultFileName = resultFileName;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(long executeTime) {
        this.executeTime = executeTime;
    }
}
