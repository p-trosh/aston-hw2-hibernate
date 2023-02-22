package ru.trosh.astontrainee.domain;

import java.util.ArrayList;
import java.util.List;

public class Worker {
    private Long id;
    private String firstName;
    private String lastName;
    private Department department;
    private Speciality speciality;
    private List<Task> tasks = new ArrayList<>();

    public static Worker.WorkerBuilder builder() {
        return new Worker.WorkerBuilder();
    }

    public Worker() {
    }

    public Worker(Long id, String firstName, String lastName, Department department, Speciality speciality, List<Task> tasks) {
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

    public Department getDepartment() {
        return this.department;
    }

    public Speciality getSpeciality() {
        return this.speciality;
    }

    public List<Task> getTasks() {
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

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Worker worker = (Worker) o;

        if (id != null ? !id.equals(worker.id) : worker.id != null) return false;
        if (firstName != null ? !firstName.equals(worker.firstName) : worker.firstName != null) return false;
        return lastName != null ? lastName.equals(worker.lastName) : worker.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    public static class WorkerBuilder {
        private Long id;
        private String firstName;
        private String lastName;
        private Department department;
        private Speciality speciality;
        private List<Task> tasks;

        WorkerBuilder() {
        }

        public Worker.WorkerBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public Worker.WorkerBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Worker.WorkerBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Worker.WorkerBuilder department(Department department) {
            this.department = department;
            return this;
        }

        public Worker.WorkerBuilder speciality(Speciality speciality) {
            this.speciality = speciality;
            return this;
        }

        public Worker.WorkerBuilder tasks(List<Task> tasks) {
            this.tasks = tasks;
            return this;
        }

        public Worker build() {
            return new Worker(this.id, this.firstName, this.lastName, this.department, this.speciality, this.tasks);
        }
    }
}
