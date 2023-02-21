package ru.trosh.astontrainee.dao;

import org.springframework.stereotype.Component;
import ru.trosh.astontrainee.config.JDBCConnectionManager;
import ru.trosh.astontrainee.model.Department;
import ru.trosh.astontrainee.model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void create(Task task) {
        execute(CREATE_TASK, task.getTitle(), task.getDescription(), task.getDepartment().getId());
    }

    public Task selectById(Long id) {
        return query(SELECT_TASK, id).get(0);
    }

    public List<Task> selectAll() {
        return query(SELECT_ALL_TASKS);
    }

    public void update(Task task) {
        execute(UPDATE_TASK, task.getTitle(), task.getDescription(), task.getDepartment().getId(), task.getId());
    }

    public void delete(Long id) {
        execute(DELETE_TASK, id);

    }

    private int execute(final String sql, final Object... values) {
        try (Connection connection = JDBCConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
            return statement.executeUpdate();
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
