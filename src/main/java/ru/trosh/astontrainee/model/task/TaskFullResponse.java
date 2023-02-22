package ru.trosh.astontrainee.model.task;

import ru.trosh.astontrainee.model.department.DepartmentShortResponse;
import ru.trosh.astontrainee.model.worker.WorkerShortResponse;

import java.util.ArrayList;
import java.util.List;

public class TaskFullResponse {
    private Long id;
    private String title;
    private String description;
    private DepartmentShortResponse department;
    private List<WorkerShortResponse> workers = new ArrayList<>();

    public static TaskFullResponse.TaskBuilder builder() {
        return new TaskFullResponse.TaskBuilder();
    }

    public TaskFullResponse() {
    }

    public TaskFullResponse(
            Long id,
            String title,
            String description,
            DepartmentShortResponse department,
            List<WorkerShortResponse> workers) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.department = department;
        this.workers = workers;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public DepartmentShortResponse getDepartment() {
        return this.department;
    }

    public List<WorkerShortResponse> getWorkers() {
        return this.workers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDepartment(DepartmentShortResponse department) {
        this.department = department;
    }

    public void setWorkers(List<WorkerShortResponse> workers) {
        this.workers = workers;
    }

    public static class TaskBuilder {
        private Long id;
        private String title;
        private String description;
        private DepartmentShortResponse department;
        private List<WorkerShortResponse> workers;

        TaskBuilder() {
        }

        public TaskFullResponse.TaskBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TaskFullResponse.TaskBuilder title(String title) {
            this.title = title;
            return this;
        }

        public TaskFullResponse.TaskBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TaskFullResponse.TaskBuilder department(DepartmentShortResponse department) {
            this.department = department;
            return this;
        }

        public TaskFullResponse.TaskBuilder workers(List<WorkerShortResponse> workers) {
            this.workers = workers;
            return this;
        }

        public TaskFullResponse build() {
            return new TaskFullResponse(this.id, this.title, this.description, this.department, this.workers);
        }
    }
}
