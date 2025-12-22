package com.tlu.unigrade.enums;

public enum EnrollmentStatus {
    IN_PROGRESS,
    COMPLETED,
    RETAKE_REQUIRED,
    CANCELLED;

    public boolean isCompleted() {
        return this == COMPLETED;
    }
}
