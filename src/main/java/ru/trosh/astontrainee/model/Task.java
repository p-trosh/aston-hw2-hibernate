package ru.trosh.astontrainee.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Task {
    private Long id;
    private String title;
    private String description;
    @EqualsAndHashCode.Exclude
    private Department department;
    @EqualsAndHashCode.Exclude
    private List<Worker> workers = new ArrayList<>();
}
