package ru.trosh.astontrainee.model.department;

import ru.trosh.astontrainee.model.task.TaskShortResponse;
import ru.trosh.astontrainee.model.worker.WorkerShortResponse;

import java.util.List;

public class DepartmentFullResponse {
    private Long id;
    private String name;
    private List<WorkerShortResponse> workers;
    private List<TaskShortResponse> tasks;

    public static DepartmentFullResponse.DepartmentBuilder builder() {
        return new DepartmentFullResponse.DepartmentBuilder();
    }

    public DepartmentFullResponse() {
    }

    public DepartmentFullResponse(
            Long id,
            String name,
            List<WorkerShortResponse> workers,
            List<TaskShortResponse> tasks) {
        this.id = id;
        this.name = name;
        this.workers = workers;
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<WorkerShortResponse> getWorkers() {
        return workers;
    }

    public List<TaskShortResponse> getTasks() {
        return tasks;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorkers(List<WorkerShortResponse> workers) {
        this.workers = workers;
    }

    public void setTasks(List<TaskShortResponse> tasks) {
        this.tasks = tasks;
    }

    public static class DepartmentBuilder {
        private Long id;
        private String name;
        private List<WorkerShortResponse> workers;
        private List<TaskShortResponse> tasks;

        DepartmentBuilder() {
        }

        public DepartmentFullResponse.DepartmentBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DepartmentFullResponse.DepartmentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public DepartmentFullResponse.DepartmentBuilder workers(List<WorkerShortResponse> workers) {
            this.workers = workers;
            return this;
        }

        public DepartmentFullResponse.DepartmentBuilder tasks(List<TaskShortResponse> tasks) {
            this.tasks = tasks;
            return this;
        }

        public DepartmentFullResponse build() {
            return new DepartmentFullResponse(this.id, this.name, this.workers, this.tasks);
        }
    }
}
