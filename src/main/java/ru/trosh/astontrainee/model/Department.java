package ru.trosh.astontrainee.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    private Long id;
    private String name;
    private List<Worker> workers = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();
}
