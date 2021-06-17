package md.ceiti.service;

import lombok.RequiredArgsConstructor;
import md.ceiti.model.dto.EmployeeDTO;
import md.ceiti.model.dto.ExpandedEmployeeDTO;
import md.ceiti.model.dto.TruncatedEmployeeDTO;
import md.ceiti.model.entity.Employee;
import md.ceiti.repository.EmployeeRepository;
import md.ceiti.util.exception.DepartmentNotFoundException;
import md.ceiti.util.exception.EmployeeNotFoundException;
import md.ceiti.util.replacer.Replacer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;
    private final Converter<Employee, ExpandedEmployeeDTO> toExpanded;
    private final Converter<Employee, TruncatedEmployeeDTO> toTruncated;
    private final Converter<ExpandedEmployeeDTO, Employee> toEntity;
    private final Replacer<Employee, ExpandedEmployeeDTO> replacer;


    public List<EmployeeDTO> findAllExpandedEmployees() {
        return repository.findAll().stream().map(toExpanded::convert).collect(toList());
    }

    public List<EmployeeDTO> findAllTruncatedEmployees() {
        return repository.findAll().stream().map(toTruncated::convert).collect(toList());
    }

    public EmployeeDTO save(final ExpandedEmployeeDTO employeeDTO) {
        return toExpanded.convert(repository.save(requireNonNull(requireNonNull(toEntity.convert(employeeDTO)))));
    }

    public EmployeeDTO update(final ExpandedEmployeeDTO employeeDTO) throws Exception {
        Employee employee = findEmployeeByEmail(employeeDTO.getEmail());
        replacer.replaceValues(employee, employeeDTO);
        return toExpanded.convert(repository.save(employee));
    }

    public void deleteEmployeeByEmail(final String email) throws EmployeeNotFoundException {
        final Employee employeeByEmail = findEmployeeByEmail(email);
        repository.delete(employeeByEmail);
    }

    public EmployeeDTO findExpandedEmployeeDTOByEmail(final String email) throws EmployeeNotFoundException {
        return toExpanded.convert(findEmployeeByEmail(email));
    }

    public EmployeeDTO findTruncatedEmployeeDTOByEmail(final String email) throws EmployeeNotFoundException {
        return toTruncated.convert(findEmployeeByEmail(email));
    }

    public Employee findEmployeeByEmail(String email) throws EmployeeNotFoundException {
        return repository
                .findEmployeeByEmail(email)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with this email not exist"));
    }


}
