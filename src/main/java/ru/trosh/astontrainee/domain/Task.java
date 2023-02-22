package ru.trosh.astontrainee.domain;

import java.util.ArrayList;
import java.util.List;

public class Task {
    private Long id;
    private String title;
    private String description;
    private Department department;
    private List<Worker> workers = new ArrayList<>();

    public static Task.TaskBuilder builder() {
        return new Task.TaskBuilder();
    }

    public Task() {
    }

    public Task(Long id, String title, String description, Department department, List<Worker> workers) {
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

    public Department getDepartment() {
        return this.department;
    }

    public List<Worker> getWorkers() {
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

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != null ? !id.equals(task.id) : task.id != null) return false;
        if (title != null ? !title.equals(task.title) : task.title != null) return false;
        if (description != null ? !description.equals(task.description) : task.description != null) return false;
        return department != null ? department.equals(task.department) : task.department == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        return result;
    }

    public static class TaskBuilder {
        private Long id;
        private String title;
        private String description;
        private Department department;
        private List<Worker> workers;

        TaskBuilder() {
        }

        public Task.TaskBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public Task.TaskBuilder title(String title) {
            this.title = title;
            return this;
        }

        public Task.TaskBuilder description(String description) {
            this.description = description;
            return this;
        }

        public Task.TaskBuilder department(Department department) {
            this.department = department;
            return this;
        }

        public Task.TaskBuilder workers(List<Worker> workers) {
            this.workers = workers;
            return this;
        }

        public Task build() {
            return new Task(this.id, this.title, this.description, this.department, this.workers);
        }
    }
}
