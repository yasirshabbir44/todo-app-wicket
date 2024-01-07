package com.equitativa.model;

// Task.java
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
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
    private boolean completed;


}
