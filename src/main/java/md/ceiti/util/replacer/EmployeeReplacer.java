package md.ceiti.util.replacer;

import md.ceiti.model.dto.ExpandedEmployeeDTO;
import md.ceiti.model.entity.Department;
import md.ceiti.model.entity.Employee;
import md.ceiti.service.DepartmentService;
import md.ceiti.util.exception.DepartmentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class EmployeeReplacer implements Replacer<Employee, ExpandedEmployeeDTO> {

    @Lazy
    @Autowired
    private DepartmentService departmentService;

    @Override
    public void replaceValues(Employee replaceEntity, ExpandedEmployeeDTO dataForReplace) throws DepartmentNotFoundException {
        replaceEntity.setFirstName(dataForReplace.getFirstName());
        replaceEntity.setLastName(dataForReplace.getLastName());
        replaceEntity.setSalary(dataForReplace.getSalary());
        replaceEntity.setHireDate(dataForReplace.getHireDate());

        Department workingIn = null;

        if(nonNull(dataForReplace.getExpandedDepartmentDTO())){
            workingIn = departmentService.findDepartmentByName(dataForReplace.getExpandedDepartmentDTO().getName());
        }
        replaceEntity.setDepartment(workingIn);
    }
}
