package ru.trosh.astontrainee.dao;

import org.springframework.stereotype.Component;
import ru.trosh.astontrainee.config.JDBCConnectionManager;
import ru.trosh.astontrainee.domain.Department;
import ru.trosh.astontrainee.domain.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskDAOImpl implements TaskDAO{
    private static final String CREATE_TASK =
            "INSERT INTO task (title, description, department) VALUES ( ? , ? , ? );";
    private static final String SELECT_TASK =
            "SELECT task.id, title, description, department.id as d_id, department.name as d_name" +
            " FROM task" +
            " LEFT JOIN department ON department.id = task.department" +
            " WHERE task.id = ? ;";
    private static final String UPDATE_TASK =
            "UPDATE task SET title = ?, description = ?, department = ? WHERE id = ? ;";
    private static final String DELETE_TASK = "DELETE FROM task WHERE id = ? ;";
    private static final String SELECT_ALL_TASKS =
            "SELECT task.id, title, description, department.id as d_id, department.name as d_name" +
                    " FROM task" +
            " LEFT JOIN department ON department.id = task.department;";

    @Override
    public Task create(Task task) {
        Long createdId = execute(CREATE_TASK, task.getTitle(), task.getDescription(), task.getDepartment().getId());
        return selectById(createdId);
    }

    @Override
    public Task selectById(Long id) {
        return query(SELECT_TASK, id).get(0);
    }

    @Override
    public List<Task> selectAll() {
        return query(SELECT_ALL_TASKS);
    }

    @Override
    public Task update(Task task) {
        Long updatedId = execute(
                UPDATE_TASK,
                task.getTitle(),
                task.getDescription(),
                task.getDepartment().getId(),
                task.getId());
        return selectById(updatedId);
    }

    @Override
    public void delete(Long id) {
        execute(DELETE_TASK, id);

    }

    private Long execute(final String sql, final Object... values) {
        long id = 0;
        try (Connection connection = JDBCConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("No ID obtained.");
                }
            }
            return id;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Task> query(final String sql, final Object... values) {
        try (Connection connection = JDBCConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
            ResultSet resultSet = statement.executeQuery();
            List<Task> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(map(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private Task map(final ResultSet resultSet) throws SQLException {
        return Task.builder()
                .id(resultSet.getLong("id"))
                .title(resultSet.getString("title"))
                .description(resultSet.getString("description"))
                .department(Department.builder()
                        .id(resultSet.getLong("d_id"))
                        .name(resultSet.getString("d_name"))
                        .build())
                .build();
    }
}
