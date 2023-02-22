package ru.trosh.astontrainee.dao;

import org.springframework.stereotype.Component;
import ru.trosh.astontrainee.config.JDBCConnectionManager;
import ru.trosh.astontrainee.domain.Speciality;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SpecialityDAOImpl implements SpecialityDAO {
    private static final String CREATE_SPECIALITY = "INSERT INTO speciality (name) VALUES ( ? );";
    private static final String SELECT_SPECIALITY = "SELECT * FROM speciality WHERE id = ? ;";
    private static final String UPDATE_SPECIALITY = "UPDATE speciality SET name = ? WHERE id = ? ;";
    private static final String DELETE_SPECIALITY = "DELETE FROM speciality WHERE id = ? ;";
    private static final String SELECT_ALL_SPECIALITIES = "SELECT * FROM speciality;";

    public void create(Speciality entity) {
        execute(CREATE_SPECIALITY, entity.getName());
    }

    public Speciality selectById(Long id) {
        return query(SELECT_SPECIALITY, id).get(0);
    }

    public List<Speciality> selectAll() {
        return query(SELECT_ALL_SPECIALITIES);
    }

    public void update(Speciality speciality) {
        execute(UPDATE_SPECIALITY, speciality.getName(), speciality.getId());
    }

    public void delete(Long id) {
        execute(DELETE_SPECIALITY, id);

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

    private List<Speciality> query(final String sql, final Object... values) {
        try (Connection connection = JDBCConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
            ResultSet resultSet = statement.executeQuery();
            List<Speciality> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(map(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private Speciality map(final ResultSet resultSet) throws SQLException {
        return Speciality.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .build();
    }
}
