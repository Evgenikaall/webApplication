package md.ceiti.util.converter;

import md.ceiti.model.dto.TruncatedDepartmentDTO;
import md.ceiti.model.entity.Department;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DepartmentToTruncatedDTOConverter implements Converter<Department, TruncatedDepartmentDTO> {
    @Override
    public TruncatedDepartmentDTO convert(Department department) {
        return TruncatedDepartmentDTO.builder()
                .name(department.getName())
                .build();
    }
}
