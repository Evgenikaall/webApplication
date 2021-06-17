package md.ceiti.util.converter;

import lombok.SneakyThrows;
import md.ceiti.model.dto.EmployeeDTO;
import md.ceiti.model.dto.ExpandedDepartmentDTO;
import md.ceiti.model.dto.ExpandedEmployeeDTO;
import md.ceiti.model.entity.Department;
import md.ceiti.model.entity.Employee;
import md.ceiti.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class ExpandedEmployeeDTOToEntityConverter implements Converter<ExpandedEmployeeDTO, Employee> {

    @Lazy
    @Autowired
    private DepartmentService service;

    @Override
    @SneakyThrows
    public Employee convert(ExpandedEmployeeDTO employeeDTO) {

        Employee employee = Employee.builder()
                .firstName(employeeDTO.getFirstName())
                .lastName(employeeDTO.getLastName())
                .email(employeeDTO.getEmail())
                .job(employeeDTO.getJob())
                .salary(employeeDTO.getSalary())
                .hireDate(employeeDTO.getHireDate())
                .build();

        Department department = null;

        if (nonNull(employeeDTO.getExpandedDepartmentDTO())) department =
                department = service.findDepartmentByName(employeeDTO.getExpandedDepartmentDTO().getName());

        employee.setDepartment(department);

        return employee;
    }
}
