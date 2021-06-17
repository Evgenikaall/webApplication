package md.ceiti.repository;

import md.ceiti.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentsRepository extends JpaRepository<Department, Long> {
    Optional<Department> findDepartmentByName(String name);
    Optional<Department> findDepartmentByManagerEmail(String email);
}
