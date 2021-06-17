package md.ceiti.util.replacer;

import lombok.RequiredArgsConstructor;
import md.ceiti.model.dto.ExpandedDepartmentDTO;
import md.ceiti.model.entity.Department;
import md.ceiti.model.entity.Employee;
import md.ceiti.service.EmployeeService;
import md.ceiti.util.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class DepartmentReplacer implements Replacer<Department, ExpandedDepartmentDTO> {

    private final EmployeeService service;

    @Override
    public void replaceValues(Department entityForReplace, ExpandedDepartmentDTO dataForReplace) throws EmployeeNotFoundException {
        Employee manager = null;

        if(nonNull(dataForReplace.getEmail()))
            manager = service.findEmployeeByEmail(dataForReplace.getEmail());


        manager.setDepartment(entityForReplace);
        entityForReplace.setManager(manager);
    }
}
