package dev.stymjs0515.lectures.member.domain;

public final class PasswordPolicy {
    private PasswordPolicy() {};

    public static boolean twoOfThree(String password) {
        int count = 0;
        if (password.chars().anyMatch(Character::isUpperCase)) count++;
        if (password.chars().anyMatch(Character::isLowerCase)) count++;
        if (password.chars().anyMatch(Character::isDigit)) count++;
        return count >= 2;
    }
}
