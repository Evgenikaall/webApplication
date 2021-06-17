package md.ceiti.util.converter;

import lombok.RequiredArgsConstructor;
import md.ceiti.model.dto.DepartmentDTO;
import md.ceiti.model.dto.EmployeeDTO;
import md.ceiti.model.dto.ExpandedDepartmentDTO;
import md.ceiti.model.dto.ExpandedEmployeeDTO;
import md.ceiti.model.entity.Department;
import md.ceiti.model.entity.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class EmployeeToExpandedDTOConverter implements Converter<Employee, ExpandedEmployeeDTO> {

    private final Converter<Department, ExpandedDepartmentDTO> toDTO;

    @Override
    public ExpandedEmployeeDTO convert(Employee employee) {
        ExpandedEmployeeDTO employeeDTO = ExpandedEmployeeDTO.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .job(employee.getJob())
                .salary(employee.getSalary())
                .hireDate(employee.getHireDate())
                .build();

        if (nonNull(employee.getDepartment()))
            employeeDTO.setExpandedDepartmentDTO((ExpandedDepartmentDTO) toDTO.convert(employee.getDepartment()));

        return employeeDTO;
    }
}
