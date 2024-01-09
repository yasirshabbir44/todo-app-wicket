package com.equitativa.model;

// Task.java
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;


@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Task implements Serializable {
    private UUID id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Priority priority;
    private Status status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
