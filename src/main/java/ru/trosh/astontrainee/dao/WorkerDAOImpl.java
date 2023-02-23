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
public class WorkerDAOImpl implements WorkerDAO{
    private static final String SELECT_ALL_WORKERS =
            "SELECT" +
                    " worker.id, first_name, last_name, department.id as d_id, department.name as d_name," +
                    " speciality.id as s_id, speciality.name as s_name " +
            " FROM worker" +
            " LEFT JOIN department ON department.id = worker.department" +
            " LEFT JOIN speciality ON speciality.id = worker.speciality";
    private static final String SELECT_WORKER =
            "SELECT " +
                    " worker.id, first_name, last_name, department.id as d_id, department.name as d_name, " +
                    " speciality.id as s_id, speciality.name as s_name, task.id as t_id, task.title as t_title" +
                    " FROM worker" +
                    " LEFT JOIN department ON department.id = worker.department" +
                    " LEFT JOIN speciality ON speciality.id = worker.speciality" +
                    " LEFT JOIN worker_task ON worker_task.worker_id = worker.id" +
                    " LEFT JOIN task ON task.id = worker_task.task_id" +
                    " WHERE worker.id = ? ;";
    private static final String DELETE_WORKER = "DELETE FROM worker WHERE id = ? ;";
    private static final String CREATE_WORKER =
            "INSERT INTO worker (first_name, last_name, speciality, department) VALUES ( ? , ? , ? , ? );";
    private static final String UPDATE_WORKER =
            "UPDATE worker SET first_name = ?, last_name = ?, speciality = ?, department = ? WHERE id = ? ;";

    public Worker create(Worker worker) {
        Long createId = execute(CREATE_WORKER,
                worker.getFirstName(),
                worker.getLastName(),
                worker.getSpeciality().getId(),
                worker.getDepartment().getId());
        return selectById(createId);
    }

    public List<Worker> selectAll() {
        try (Connection connection = JDBCConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_WORKERS);
             ResultSet resultSet = statement.executeQuery()) {
            List<Worker> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(mapShortWorker(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Worker selectById(Long id) {
        try (Connection connection = JDBCConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_WORKER)) {
            statement.setObject(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return mapFullWorker(resultSet);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void delete(Long id) {
        execute(DELETE_WORKER, id);
    }

    public Worker update(Worker worker) {
        Long updatedId = execute(
                UPDATE_WORKER,
                worker.getFirstName(),
                worker.getLastName(),
                worker.getSpeciality().getId(),
                worker.getDepartment().getId(),
                worker.getId());
        return selectById(updatedId);
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

    private Worker mapShortWorker(final ResultSet resultSet) throws SQLException {
        return Worker.builder()
                .id(resultSet.getLong("id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .speciality(Speciality.builder()
                        .id(resultSet.getLong("s_id"))
                        .name(resultSet.getString("s_name"))
                        .build())
                .department(Department.builder()
                        .id(resultSet.getLong("d_id"))
                        .name(resultSet.getString("d_name"))
                        .build())
                .build();
    }

    private Worker mapFullWorker(final ResultSet resultSet) throws SQLException {
        Worker worker = null;
        if (resultSet.next()) {
            Set<Task> taskSet = new HashSet<>();
            worker = Worker.builder()
                    .id(resultSet.getLong("id"))
                    .firstName(resultSet.getString("first_name"))
                    .lastName(resultSet.getString("last_name"))
                    .speciality(Speciality.builder()
                            .id(resultSet.getLong("s_id"))
                            .name(resultSet.getString("s_name"))
                            .build())
                    .department(Department.builder()
                            .id(resultSet.getLong("d_id"))
                            .name(resultSet.getString("d_name"))
                            .build())
                    .build();
            do {
                if (resultSet.getLong("t_id") != 0) {
                    taskSet.add(Task.builder()
                            .id(resultSet.getLong("t_id"))
                            .title(resultSet.getString("t_title"))
                            .build());
                }
            } while (resultSet.next());
            worker.setTasks(new ArrayList<>(taskSet));
        }

        return worker;
    }


}
