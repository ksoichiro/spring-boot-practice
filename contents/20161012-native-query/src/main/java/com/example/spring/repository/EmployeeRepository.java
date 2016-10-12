package com.example.spring.repository;

import com.example.spring.domain.Employee;
import com.example.spring.entity.ExtendedEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.stream.Collectors;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    /**
     * <ul>
     *     <li>If we use Object[] for returning value, any columns can be projected
     *     without modifying (or adding) Entity class.</li>
     *     <li>The alias "dname" is not so important in this case.
     *     It is just for avoiding conflicts.</li>
     * </ul>
     */
    @Query(value = "select e.id, e.name, d.name as dname "
        + "from employee e "
        + "left join department d on d.id = e.department_id "
        + "where d.name = ?1 "
        + "order by e.id ",
        nativeQuery = true)
    List<Object[]> findByDepartmentNameRaw(String departmentName);

    default List<ExtendedEmployee> findByDepartmentName(String departmentName) {
        return findByDepartmentNameRaw(departmentName)
            .stream()
            .map(ExtendedEmployee::new)
            .collect(Collectors.toList());
    }
}
