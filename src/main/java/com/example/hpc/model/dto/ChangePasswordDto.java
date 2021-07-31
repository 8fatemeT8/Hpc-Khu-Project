package com.example.hpc.model.dto;

import com.example.hpc.utils.validation.Password;

public class ChangePasswordDto {
    @Password
    private String oldPass;
    @Password
    private String newPass;
    @Password
    private String repeatNewPass;

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getRepeatNewPass() {
        return repeatNewPass;
    }

    public void setRepeatNewPass(String repeatNewPass) {
        this.repeatNewPass = repeatNewPass;
    }
}
