package ru.trosh.astontrainee.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.trosh.astontrainee.config.HibernateUtil;
import ru.trosh.astontrainee.domain.Department;

import java.util.List;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO{

    public Session openSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public Department create(Department entity) {
        try (Session session = openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return entity;
        }
    }

    @Override
    public Department selectById(Long id) {
        try (Session session = openSession()) {
            session.beginTransaction();
            Department department = session.load(Department.class, id);
            department.getWorkers().size();
            department.getTasks().size();
            session.update(department);
            session.getTransaction().commit();
            return department;
        }
    }

    @Override
    public List<Department> selectAll() {
        try (Session session = openSession()) {
            return session.createQuery("SELECT d FROM Department d", Department.class).getResultList();
        }
    }

    @Override
    public Department update(Department entity) {
        try (Session session = openSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            return entity;
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = openSession()) {
            session.beginTransaction();
            Department department = new Department();
            department.setId(id);
            session.delete(department);
            session.getTransaction().commit();
        }
    }
}
