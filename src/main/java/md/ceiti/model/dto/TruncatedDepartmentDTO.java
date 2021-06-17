package md.ceiti.model.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class TruncatedDepartmentDTO implements DepartmentDTO{

    @NotNull
    @NotBlank
    private String name;
}
