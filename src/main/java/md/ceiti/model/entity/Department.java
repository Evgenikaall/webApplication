package md.ceiti.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "department")
public class Department {

    @Id
    @SequenceGenerator(name = "dep_seq", sequenceName = "dep_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = AUTO, generator = "dep_seq")
    @Column(name = "department_id")
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "department_name", unique = true)
    private String name;

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "employee_id")
    private Employee manager;

    @PreRemove
    private void removeEmployeesFromCurrentDepartments(){
        employees.forEach(employee -> employee.setDepartment(null));
    }

}
