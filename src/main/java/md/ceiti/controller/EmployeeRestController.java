package md.ceiti.controller;

import lombok.RequiredArgsConstructor;
import md.ceiti.model.dto.EmployeeDTO;
import md.ceiti.model.dto.ExpandedEmployeeDTO;
import md.ceiti.service.EmployeeService;
import md.ceiti.util.exception.EmployeeNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/employee")
public class EmployeeRestController {
    private final EmployeeService service;

    @GetMapping
    @ResponseStatus(OK)
    public List<EmployeeDTO> findAllEmployees(@RequestHeader(value = "expand", defaultValue = "true") final Boolean expand) {
        return expand ? service.findAllExpandedEmployees() : service.findAllTruncatedEmployees();
    }

    @GetMapping("/{email}")
    @ResponseStatus(OK)
    public EmployeeDTO findEmployeeByEmail(
            @PathVariable("email") String email,
            @RequestHeader(value = "expand", defaultValue = "true") final Boolean expand
    ) throws EmployeeNotFoundException {
        return expand ? service.findExpandedEmployeeDTOByEmail(email) : service.findTruncatedEmployeeDTOByEmail(email);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public EmployeeDTO save(@Valid @RequestBody ExpandedEmployeeDTO employeeDTO) {
        return service.save(employeeDTO);
    }

    @PutMapping
    @ResponseStatus(OK)
    public EmployeeDTO update(@Valid @RequestBody ExpandedEmployeeDTO employeeDTO) throws Exception {
        return service.update(employeeDTO);
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(NO_CONTENT)
    public void deleteEmployee(@PathVariable("email") String email) throws EmployeeNotFoundException {
        service.deleteEmployeeByEmail(email);
    }
}
