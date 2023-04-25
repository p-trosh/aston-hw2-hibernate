package ru.trosh.astontrainee.model.worker;

public class WorkerRequest {

    private String firstName;

    private String lastName;

    private Long departmentId;

    private Long specialityId;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public Long getSpecialityId() {
        return specialityId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public void setSpecialityId(Long specialityId) {
        this.specialityId = specialityId;
    }
}
