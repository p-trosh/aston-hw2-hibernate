package ru.trosh.astontrainee.domain;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private Long id;
    private String name;
    private List<Worker> workers = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();

    public static Department.DepartmentBuilder builder() {
        return new Department.DepartmentBuilder();
    }

    public Department() {
    }

    public Department(Long id, String name, List<Worker> workers, List<Task> tasks) {
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

    public List<Worker> getWorkers() {
        return workers;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public static class DepartmentBuilder {
        private Long id;
        private String name;
        private List<Worker> workers;
        private List<Task> tasks;

        DepartmentBuilder() {
        }

        public Department.DepartmentBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public Department.DepartmentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Department.DepartmentBuilder workers(List<Worker> workers) {
            this.workers = workers;
            return this;
        }

        public Department.DepartmentBuilder tasks(List<Task> tasks) {
            this.tasks = tasks;
            return this;
        }

        public Department build() {
            return new Department(this.id, this.name, this.workers, this.tasks);
        }
    }
}
