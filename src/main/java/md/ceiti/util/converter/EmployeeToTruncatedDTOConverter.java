package md.ceiti.util.converter;

import md.ceiti.model.dto.TruncatedEmployeeDTO;
import md.ceiti.model.entity.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmployeeToTruncatedDTOConverter implements Converter<Employee, TruncatedEmployeeDTO> {
    @Override
    public TruncatedEmployeeDTO convert(Employee employee) {
        return TruncatedEmployeeDTO.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .build();
    }
}
