package com.lamdevops.annotation.validator.MatchingPassword;


import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ScriptAssert(lang="javascript",
        script = "_this.password.equals(_this.confirmPassword)",
        message = "{FieldMatching.message}")
public class PasswordScriptAssert {

    @NotNull
    @Size(min = 6, max = 30)
    String password;

    @NotNull
    @Size(min = 6, max = 30)
    String confirmPassword;

    public PasswordScriptAssert(String password, String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
