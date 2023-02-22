package ru.trosh.astontrainee.model.worker;

public class WorkerShortResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String specialityName;

    private String departmentName;

    public static WorkerShortResponse.WorkerBuilder builder() {
        return new WorkerShortResponse.WorkerBuilder();
    }

    public WorkerShortResponse() {
    }

    public WorkerShortResponse(Long id, String firstName, String lastName, String specialityName, String departmentName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialityName = specialityName;
        this.departmentName = departmentName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public String getDepartmentName() {
        return departmentName;
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

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public static class WorkerBuilder {
        private Long id;
        private String firstName;
        private String lastName;
        private String specialityName;
        private String departmentName;

        WorkerBuilder() {
        }

        public WorkerShortResponse.WorkerBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public WorkerShortResponse.WorkerBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public WorkerShortResponse.WorkerBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public WorkerShortResponse.WorkerBuilder departmentName(String departmentName) {
            this.departmentName = departmentName;
            return this;
        }

        public WorkerShortResponse.WorkerBuilder specialityName(String specialityName) {
            this.specialityName = specialityName;
            return this;
        }

        public WorkerShortResponse build() {
            return new WorkerShortResponse(
                    this.id,
                    this.firstName,
                    this.lastName,
                    this.specialityName,
                    this.departmentName);
        }
    }
}
