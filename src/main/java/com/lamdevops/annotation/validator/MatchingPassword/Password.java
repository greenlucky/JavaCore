package com.lamdevops.annotation.validator.MatchingPassword;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatching.List({
        @FieldMatching(first = "password", second = "confirmPassword", message = "FieldMatching.message")
})
public class Password {

    @NotNull
    @Size(min = 6, max = 30)
    String password;

    @NotNull
    @Size(min = 6, max = 30)
    String confirmPassword;

    public Password(String password, String confirmPassword) {
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
