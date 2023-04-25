package ru.trosh.astontrainee.model.task;

public class TaskShortResponse {
    private Long id;
    private String title;
    private String description;
    private String departmentName;

    public TaskShortResponse(Long id, String title, String description, String departmentName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.departmentName = departmentName;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDepartmentName() {
        return departmentName;
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

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
