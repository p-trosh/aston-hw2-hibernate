package ru.trosh.astontrainee.model.worker;

import ru.trosh.astontrainee.model.department.DepartmentShortResponse;
import ru.trosh.astontrainee.model.speciality.SpecialityShortResponse;
import ru.trosh.astontrainee.model.task.TaskShortResponse;

import java.util.ArrayList;
import java.util.List;

public class WorkerFullResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private DepartmentShortResponse department;
    private SpecialityShortResponse speciality;
    private List<TaskShortResponse> tasks = new ArrayList<>();

    public static WorkerFullResponse.WorkerBuilder builder() {
        return new WorkerFullResponse.WorkerBuilder();
    }

    public WorkerFullResponse() {
    }

    public WorkerFullResponse(
            Long id,
            String firstName,
            String lastName,
            DepartmentShortResponse department,
            SpecialityShortResponse speciality,
            List<TaskShortResponse> tasks) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.speciality = speciality;
        this.tasks = tasks;
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public DepartmentShortResponse getDepartment() {
        return this.department;
    }

    public SpecialityShortResponse getSpeciality() {
        return this.speciality;
    }

    public List<TaskShortResponse> getTasks() {
        return this.tasks;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDepartment(DepartmentShortResponse department) {
        this.department = department;
    }

    public void setSpeciality(SpecialityShortResponse speciality) {
        this.speciality = speciality;
    }

    public void setTasks(List<TaskShortResponse> tasks) {
        this.tasks = tasks;
    }

    public static class WorkerBuilder {
        private Long id;
        private String firstName;
        private String lastName;
        private DepartmentShortResponse department;
        private SpecialityShortResponse speciality;
        private List<TaskShortResponse> tasks;

        WorkerBuilder() {
        }

        public WorkerFullResponse.WorkerBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public WorkerFullResponse.WorkerBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public WorkerFullResponse.WorkerBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public WorkerFullResponse.WorkerBuilder department(DepartmentShortResponse department) {
            this.department = department;
            return this;
        }

        public WorkerFullResponse.WorkerBuilder speciality(SpecialityShortResponse speciality) {
            this.speciality = speciality;
            return this;
        }

        public WorkerFullResponse.WorkerBuilder tasks(List<TaskShortResponse> tasks) {
            this.tasks = tasks;
            return this;
        }

        public WorkerFullResponse build() {
            return new WorkerFullResponse(
                    this.id,
                    this.firstName,
                    this.lastName,
                    this.department,
                    this.speciality,
                    this.tasks);
        }
    }
}
