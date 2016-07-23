package com.example.spring.repository;

import com.example.spring.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //@Query("select e from #{#entityName} e where e.name = ?1 and definedColumn = 1")
    //@Query("select e from #{#entityName} e where e.name = ?1 and e.definedColumn = 1")
    // This will fail: Query validation
    //@Query("select e from #{#entityName} e where e.name = ?1 and e.defined_column = 1")
    @Query("select e from #{#entityName} e where e.name = ?1 and defined_column = 1")

    // Both work, "e." is just for clarifying the owner of the property.
    //@Query("select e from #{#entityName} e where e.name = ?1")
    //@Query("select e from #{#entityName} e where name = ?1")

    // This doesn't work but it passes query validation
    //@Query("select e from #{#entityName} e where foo = ?1")

    // This will cause org.hibernate.QueryException: could not resolve property: foo of: com.example.spring.domain.Employee [select e from com.example.spring.domain.Employee e where e.foo = ?1]
    //@Query("select e from #{#entityName} e where e.foo = ?1")
    Employee findByFoo(String foo);
}
