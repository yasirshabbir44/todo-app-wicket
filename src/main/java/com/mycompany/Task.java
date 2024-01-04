package com.mycompany;

// Task.java
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;


@ToString
@AllArgsConstructor
@Data
public class Task implements Serializable {
    private String title;
    private String description;
    private LocalDate dueDate;
    private Priority priority;
    private boolean completed;


}
