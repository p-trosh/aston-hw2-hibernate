package ru.trosh.astontrainee.model.speciality;

import ru.trosh.astontrainee.model.worker.WorkerShortResponse;

import java.util.ArrayList;
import java.util.List;

public class SpecialityFullResponse {
    private Long id;
    private String name;
    private List<WorkerShortResponse> workers = new ArrayList<>();

    public SpecialityFullResponse() {
    }

    public SpecialityFullResponse(Long id, String name, List<WorkerShortResponse> workers) {
        this.id = id;
        this.name = name;
        this.workers = workers;
    }

    public static SpecialityFullResponse.SpecialityBuilder builder() {
        return new SpecialityFullResponse.SpecialityBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<WorkerShortResponse> getWorkers() {
        return this.workers;
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

    public static class SpecialityBuilder {
        private Long id;
        private String name;
        private List<WorkerShortResponse> workers;

        SpecialityBuilder() {
        }

        public SpecialityFullResponse.SpecialityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public SpecialityFullResponse.SpecialityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SpecialityFullResponse.SpecialityBuilder workers(List<WorkerShortResponse> workers) {
            this.workers = workers;
            return this;
        }

        public SpecialityFullResponse build() {
            return new SpecialityFullResponse(this.id, this.name, this.workers);
        }
    }
}
