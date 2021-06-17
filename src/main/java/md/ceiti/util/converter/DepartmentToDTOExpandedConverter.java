package md.ceiti.util.converter;

import md.ceiti.model.dto.ExpandedDepartmentDTO;
import md.ceiti.model.entity.Department;
import md.ceiti.model.entity.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import static java.util.Objects.nonNull;

@Component
public class DepartmentToDTOExpandedConverter implements Converter<Department, ExpandedDepartmentDTO> {

    @Override
    public ExpandedDepartmentDTO convert(Department department) {

        final Employee manager = department.getManager();
        final String email = nonNull(manager) ? manager.getEmail() : null;
        return ExpandedDepartmentDTO.builder()
                .name(department.getName())
                .email(email)
                .build();
    }
}
