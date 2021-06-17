package md.ceiti.model.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class ExpandedDepartmentDTO implements DepartmentDTO{

    @NotNull
    @NotBlank
    private String name;

    @Email
    private String email;
}
