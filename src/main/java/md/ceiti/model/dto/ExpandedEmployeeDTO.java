package md.ceiti.model.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@Builder
public class ExpandedEmployeeDTO implements EmployeeDTO{

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotBlank
    private String job;

    @Email
    @NotNull
    @NotBlank
    private String email;

    @Min(1)
    private Double salary;

    private Timestamp hireDate;

    private ExpandedDepartmentDTO expandedDepartmentDTO;
}
