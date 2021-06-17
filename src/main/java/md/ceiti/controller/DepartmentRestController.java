package md.ceiti.controller;

import lombok.RequiredArgsConstructor;
import md.ceiti.model.dto.DepartmentDTO;
import md.ceiti.model.dto.ExpandedDepartmentDTO;
import md.ceiti.service.DepartmentService;
import md.ceiti.util.exception.DepartmentNotFoundException;
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
@RequestMapping(value = "api/departments")
public class DepartmentRestController {

    private final DepartmentService service;

    @GetMapping
    @ResponseStatus(OK)
    public List<DepartmentDTO> findAll(@RequestHeader(value = "expand", defaultValue = "true") final Boolean expand) {
        return expand ? service.findAllExpandedDepartments() : service.findAllTruncatedDepartments();
    }

    @GetMapping("/{name}")
    @ResponseStatus(OK)
    public DepartmentDTO findByName(
            @PathVariable("name") String name,
            @RequestHeader(value = "expand", defaultValue = "true") final Boolean expand
    ) throws Exception {
        return expand ? service.findExpandedDepartmentByDepartmentName(name) : service.findTruncatedDepartmentByDepartmentName(name);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public DepartmentDTO save(@Valid @RequestBody ExpandedDepartmentDTO departmentDTO) {
        return service.saveDepartment(departmentDTO);
    }

    @PutMapping
    @ResponseStatus(OK)
    public DepartmentDTO update(@Valid @RequestBody ExpandedDepartmentDTO departmentDTO) throws Exception {
        return service.updateDepartment(departmentDTO);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(NO_CONTENT)
    public void deleteByName(@PathVariable("name") String name) throws DepartmentNotFoundException {
        service.deleteDepartmentByName(name);
    }
}
