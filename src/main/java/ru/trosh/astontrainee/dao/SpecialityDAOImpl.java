package ru.trosh.astontrainee.dao;

import org.springframework.stereotype.Component;
import ru.trosh.astontrainee.config.JDBCConnectionManager;
import ru.trosh.astontrainee.domain.Speciality;
import ru.trosh.astontrainee.domain.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SpecialityDAOImpl implements SpecialityDAO {
    private static final String CREATE_SPECIALITY = "INSERT INTO speciality (name) VALUES ( ? );";
    private static final String SELECT_SPECIALITY =
            "SELECT" +
            "  speciality.id AS s_id," +
            "  speciality.name AS s_name," +
            "  worker.id AS w_id," +
            "  worker.first_name AS w_first_name," +
            "  worker.last_name AS w_last_name" +
            " FROM speciality " +
            " LEFT JOIN worker on worker.speciality = speciality.id  " +
            " WHERE speciality.id = ? ;";
    private static final String UPDATE_SPECIALITY = "UPDATE speciality SET name = ? WHERE id = ? ;";
    private static final String DELETE_SPECIALITY = "DELETE FROM speciality WHERE id = ? ;";
    private static final String SELECT_ALL_SPECIALITIES = "SELECT * FROM speciality;";

    @Override
    public Speciality create(Speciality entity) {
        Long createdId = execute(CREATE_SPECIALITY, entity.getName());
        return selectById(createdId);
    }

    @Override
    public Speciality selectById(Long id) {
        try (Connection connection = JDBCConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_SPECIALITY)) {
            statement.setObject(1,id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return map(resultSet);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public List<Speciality> selectAll() {
        try (Connection connection = JDBCConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SPECIALITIES);
             ResultSet resultSet = statement.executeQuery()) {
            List<Speciality> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(Speciality.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .build());
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Speciality update(Speciality speciality) {
        Long updatedId = execute(UPDATE_SPECIALITY, speciality.getName(), speciality.getId());
        return selectById(updatedId);
    }

    @Override
    public void delete(Long id) {
        execute(DELETE_SPECIALITY, id);
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

    private Speciality map(final ResultSet resultSet) throws SQLException {
        Speciality speciality = null;
        if (resultSet.next()) {
            Set<Worker> workerSet = new HashSet<>();
            speciality = Speciality.builder()
                    .id(resultSet.getLong("s_id"))
                    .name(resultSet.getString("s_name"))
                    .build();
            do {
                Long workerId = resultSet.getLong("w_id");
                if (workerId != 0) {
                    workerSet.add(Worker.builder()
                            .id(workerId)
                            .firstName(resultSet.getString("w_first_name"))
                            .lastName(resultSet.getString("w_last_name"))
                            .build());
                }
            } while (resultSet.next());
            speciality.setWorkers(new ArrayList<>(workerSet));
        }
        return speciality;
    }
}
