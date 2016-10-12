package com.example.spring.repository;

import com.example.spring.entity.ExtendedEmployee2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExtendedEmployeeRepository extends JpaRepository<ExtendedEmployee2, Integer> {
    /**
     * <ul>
     *     <li>The alias "department_name" corresponds to ExtendedEmployee2.departmentName.</li>
     *     <li>Returning value "ExtendedEmployee2" must match the first type parameter of JpaRepository,
     *     or it is returned as Object[].</li>
     * </ul>
     */
    @Query(value = "select e.id as id, e.name as name, d.name as department_name "
        + "from employee e "
        + "left join department d on d.id = e.department_id "
        + "where d.name = ?1 "
        + "order by e.id ",
        nativeQuery = true)
    List<ExtendedEmployee2> findByDepartmentName(String departmentName);
}
