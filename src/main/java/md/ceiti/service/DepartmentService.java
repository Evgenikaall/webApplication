package md.ceiti.service;

import lombok.RequiredArgsConstructor;
import md.ceiti.model.dto.DepartmentDTO;
import md.ceiti.model.dto.ExpandedDepartmentDTO;
import md.ceiti.model.dto.TruncatedDepartmentDTO;
import md.ceiti.model.entity.Department;
import md.ceiti.repository.DepartmentsRepository;
import md.ceiti.util.exception.DepartmentNotFoundException;
import md.ceiti.util.exception.EmployeeNotFoundException;
import md.ceiti.util.replacer.Replacer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentsRepository repository;
    private final Converter<Department, ExpandedDepartmentDTO> toExpandedDTO;
    private final Converter<Department, TruncatedDepartmentDTO> toTruncatedDTO;
    private final Converter<ExpandedDepartmentDTO, Department> toEntity;
    private final Replacer<Department, ExpandedDepartmentDTO> replacer;


    public List<DepartmentDTO> findAllExpandedDepartments() {
        return repository.findAll().stream().map(toExpandedDTO::convert).collect(toList());
    }

    public List<DepartmentDTO> findAllTruncatedDepartments() {
        return repository.findAll().stream().map(toTruncatedDTO::convert).collect(toList());
    }

    public DepartmentDTO findTruncatedDepartmentByDepartmentName(final String name) throws DepartmentNotFoundException {
        return toTruncatedDTO.convert(findDepartmentByName(name));
    }

    public DepartmentDTO findExpandedDepartmentByDepartmentName(final String name) throws DepartmentNotFoundException {
        return toExpandedDTO.convert(findDepartmentByName(name));
    }


    public DepartmentDTO saveDepartment(final ExpandedDepartmentDTO departmentDTO) {
        if (isNull(departmentDTO)) throw new IllegalArgumentException("Is empty request body");
        return toExpandedDTO.convert(repository.save(requireNonNull(toEntity.convert(departmentDTO))));
    }

    public DepartmentDTO updateDepartment(final ExpandedDepartmentDTO departmentDTO) throws DepartmentNotFoundException, EmployeeNotFoundException {
        final Department departmentByName = findDepartmentByName(departmentDTO.getName());
        replacer.replaceValues(departmentByName, departmentDTO);
        repository.save(departmentByName);
        return toExpandedDTO.convert(repository.save(departmentByName));
    }

    public void deleteDepartmentByName(final String name) throws DepartmentNotFoundException {
        final Department departmentByName = findDepartmentByName(name);
        repository.delete(departmentByName);
    }

    public Department findDepartmentByName(final String name) throws DepartmentNotFoundException {
        return repository
                .findDepartmentByName(name)
                .orElseThrow(() -> new DepartmentNotFoundException("Department with this name is not found"));
    }

    public Department findDepartmentByManagersEmail(final String email) throws DepartmentNotFoundException {
        return repository
                .findDepartmentByManagerEmail(email)
                .orElseThrow(() -> new DepartmentNotFoundException("Department with this manager's email is not found"));
    }
}
