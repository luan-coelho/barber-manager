package com.luan.barbermanager.constraints;

@PasswordMatches
public record PasswordValidationDto(String password, String matchingPassword) {

}
