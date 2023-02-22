package ru.trosh.astontrainee.domain;

import java.util.ArrayList;
import java.util.List;

public class Speciality {
    private Long id;
    private String name;
    private List<Worker> workers = new ArrayList<>();

    public Speciality() {
    }

    public Speciality(Long id, String name, List<Worker> workers) {
        this.id = id;
        this.name = name;
        this.workers = workers;
    }

    public static Speciality.SpecialityBuilder builder() {
        return new Speciality.SpecialityBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<Worker> getWorkers() {
        return this.workers;
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

    public static class SpecialityBuilder {
        private Long id;
        private String name;
        private List<Worker> workers;

        SpecialityBuilder() {
        }

        public Speciality.SpecialityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public Speciality.SpecialityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Speciality.SpecialityBuilder workers(List<Worker> workers) {
            this.workers = workers;
            return this;
        }

        public Speciality build() {
            return new Speciality(this.id, this.name, this.workers);
        }
    }
}
