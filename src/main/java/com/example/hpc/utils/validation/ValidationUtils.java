package com.example.hpc.utils.validation;


import com.example.hpc.model.dto.ChangePasswordDto;
import com.example.hpc.utils.exceptions.ErrorCodes;
import com.example.hpc.utils.exceptions.ExceptionHandler;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ValidationUtils {

    // valid both changePassword and forgetPassword
    public static void validNewPasswords(ChangePasswordDto changePasswordDTO, String oldPass, PasswordEncoder passwordEncoder) {
        if (changePasswordDTO.getOldPass() != null && !passwordEncoder.matches(changePasswordDTO.getOldPass(), oldPass))
            throw new ExceptionHandler("old.pass.is.wrong", ErrorCodes.ERROR_CODE_INVALID_OLD_PASS);
        if (passwordEncoder.matches(changePasswordDTO.getNewPass(), oldPass))
            throw new ExceptionHandler("this.is.use.before", ErrorCodes.ERROR_CODE_TAKEN_PASSWORD_BEFORE);
        if (!changePasswordDTO.getNewPass().equals(changePasswordDTO.getRepeatNewPass()))
            throw new ExceptionHandler("these.password.not.match", ErrorCodes.ERROR_CODE_INPUT_PASSWORDS_NOT_MATCH);

    }
}
