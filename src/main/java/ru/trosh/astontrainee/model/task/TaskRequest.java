package ru.trosh.astontrainee.model.task;

public class TaskRequest {

    private String title;

    private String description;

    private Long departmentId;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
