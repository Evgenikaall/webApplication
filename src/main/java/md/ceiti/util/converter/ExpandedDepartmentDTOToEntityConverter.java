package md.ceiti.util.converter;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import md.ceiti.model.dto.ExpandedDepartmentDTO;
import md.ceiti.model.entity.Department;
import md.ceiti.model.entity.Employee;
import md.ceiti.service.EmployeeService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExpandedDepartmentDTOToEntityConverter implements Converter<ExpandedDepartmentDTO, Department> {

    private final EmployeeService employeeService;

    @Override
    @SneakyThrows
    public Department convert(ExpandedDepartmentDTO departmentDTO) {
        Employee manager = null;
        if (nonNull(departmentDTO.getEmail())) {
            employeeService.findExpandedEmployeeDTOByEmail(departmentDTO.getEmail());
        }

        return Department.builder()
                .name(departmentDTO.getName())
                .manager(manager)
                .build();
    }
}
