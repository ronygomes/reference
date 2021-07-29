package me.ronygomes.reference;

import java.util.Objects;

public record Person(String name, String email, int age) implements Validator {

    private static final String DEFAULT_EMAIL_SUFFIX = "@gmail.com";

    public Person {
        if (age < 0) {
            throw new IllegalArgumentException("Age can't be negative");
        }
    }

    public Person(String name, int age) {
        this(name, name + DEFAULT_EMAIL_SUFFIX, age);
    }

    public boolean isEmailPrefixAndNameSame() {
        record EmailParts(String prefix, String suffix) {
            public boolean isPrefixEqualsTo(String name) {
                return prefix().equalsIgnoreCase(name);
            }
        }

        String[] parts = email.split("@");
        EmailParts emailParts = new EmailParts(parts[0], parts[1]);

        return emailParts.isPrefixEqualsTo(name);
    }

    @Override
    public boolean isValid() {
        return Objects.nonNull(name) && Objects.nonNull(email);
    }
}
