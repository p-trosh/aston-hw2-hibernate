package ru.trosh.astontrainee.dao;

import org.springframework.stereotype.Component;
import ru.trosh.astontrainee.config.JDBCConnectionManager;
import ru.trosh.astontrainee.domain.Department;
import ru.trosh.astontrainee.domain.Speciality;
import ru.trosh.astontrainee.domain.Task;
import ru.trosh.astontrainee.domain.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TaskDAOImpl implements TaskDAO{
    private static final String CREATE_TASK =
            "INSERT INTO task (title, description, department) VALUES ( ? , ? , ? );";
    private static final String SELECT_TASK =
            "SELECT" +
            " task.id, title, description, department.id as d_id, department.name as d_name, worker.id as w_id, " +
            " worker.first_name as w_first_name, worker.last_name as w_last_name " +
            " FROM task" +
            " LEFT JOIN department ON department.id = task.department" +
            " LEFT JOIN worker_task ON worker_task.task_id = task.id" +
            " LEFT JOIN worker ON worker.id = worker_task.worker_id" +
            " WHERE task.id = ? ;";
    private static final String UPDATE_TASK =
            "UPDATE task SET title = ?, description = ?, department = ? WHERE id = ? ;";
    private static final String DELETE_TASK = "DELETE FROM task WHERE id = ? ;";
    private static final String SELECT_ALL_TASKS =
            "SELECT task.id, title, description, department.id as d_id, department.name as d_name" +
                    " FROM task" +
            " LEFT JOIN department ON department.id = task.department;";
    private static final String ADD_TASK_FROM_WORKER = "" +
            "INSERT INTO worker_task (task_id, worker_id) VALUES ( ? , ? );";
    private static final String DELETE_TASK_FROM_WORKER =
            "DELETE FROM worker_task WHERE task_id = ? AND worker_id = ? ;";

    @Override
    public Task create(Task task) {
        Long createdId = execute(CREATE_TASK, task.getTitle(), task.getDescription(), task.getDepartment().getId());
        return selectById(createdId);
    }

    @Override
    public Task selectById(Long id) {
        try (Connection connection = JDBCConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_TASK)) {
            statement.setObject(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return mapFullTask(resultSet);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Task> selectAll() {
        try (Connection connection = JDBCConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_TASKS)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Task> result = new ArrayList<>();
                while (resultSet.next()) {
                    result.add(mapShortTask(resultSet));
                }
                return result;
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
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

    @Override
    public void addTaskToWorker(Long taskId, Long workerId) {
        execute(ADD_TASK_FROM_WORKER, taskId, workerId);
    }

    @Override
    public void deleteTaskFromWorker(Long taskId, Long workerId) {
        execute(DELETE_TASK_FROM_WORKER, taskId, workerId);
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

    private Task mapShortTask(final ResultSet resultSet) throws SQLException {
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

    private Task mapFullTask(final ResultSet resultSet) throws SQLException {
        Task task = null;
        if (resultSet.next()) {
            Set<Worker> workerSet = new HashSet<>();
            task = Task.builder()
                    .id(resultSet.getLong("id"))
                    .title(resultSet.getString("title"))
                    .description(resultSet.getString("description"))
                    .department(Department.builder()
                            .id(resultSet.getLong("d_id"))
                            .name(resultSet.getString("d_name"))
                            .build())
                    .build();
            do {
                if (resultSet.getLong("w_id") != 0) {
                    workerSet.add(Worker.builder()
                            .id(resultSet.getLong("w_id"))
                            .firstName(resultSet.getString("w_first_name"))
                            .lastName(resultSet.getString("w_last_name"))
                            .speciality(new Speciality())
                            .department(new Department())
                            .build());
                }
            } while (resultSet.next());
            task.setWorkers(new ArrayList<>(workerSet));
        }
        return task;
    }
}
