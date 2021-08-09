package me.ronygomes.reference;

import me.ronygomes.reference.annotation.*;

import java.util.Objects;

@TypeAnnotation
public record Person(@RecordComponentAnnotation @FieldAnnotation String name,
                     @TypeUseAnnotation @MethodAnnotation String email,
                     @ParameterAnnotation int age) implements Validator {

    private static final String DEFAULT_EMAIL_SUFFIX = "@gmail.com";

    @ConstructorAnnotation
    public Person {
        if (age < 0) {
            throw new IllegalArgumentException("Age can't be negative");
        }
    }

    public Person(String name, int age) {
        this(name, name + DEFAULT_EMAIL_SUFFIX, age);
    }

    // Redundant but can be defined if needed
    public int age() {
        return age;
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
