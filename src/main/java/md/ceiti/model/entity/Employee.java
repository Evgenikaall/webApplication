package md.ceiti.model.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {

    @Id
    @SequenceGenerator(name = "emp_seq", sequenceName = "emp_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = AUTO, generator = "emp_seq")
    @Column(name = "employee_id")
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "employee_first_name")
    private String firstName;

    @NotNull
    @NotBlank
    @Column(name = "employee_last_name")
    private String lastName;

    @NotBlank
    @Column(name = "employee_job")
    private String job;

    @Email
    @NotNull
    @Column(name = "employee_email", unique = true)
    private String email;

    @Min(1)
    @Column(name = "employee_salary")
    private Double salary;

    @Column(name = "employee_hire_date")
    private Timestamp hireDate;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne(mappedBy = "manager")
    private Department managedDepartment;

}
