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
public class Worker {
    private Long id;
    private String firstName;
    private String lastName;
    @EqualsAndHashCode.Exclude
    private Department department;
    @EqualsAndHashCode.Exclude
    private Speciality speciality;
    @EqualsAndHashCode.Exclude
    private List<Task> tasks = new ArrayList<>();
}
