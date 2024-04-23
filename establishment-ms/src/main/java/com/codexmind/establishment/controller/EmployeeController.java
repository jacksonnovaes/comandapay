package com.codexmind.establishment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.codexmind.establishment.converters.EmployeeConverter;
import com.codexmind.establishment.dto.EmployeeDTO;
import com.codexmind.establishment.dto.saveUpdate.SaveEmployeeDTO;
import com.codexmind.establishment.dto.saveUpdate.UpdateEmployeeDTO;
import com.codexmind.establishment.usecases.employee.DeleteEmployee;
import com.codexmind.establishment.usecases.employee.DetailEmployee;
import com.codexmind.establishment.usecases.employee.SaveEmployee;
import com.codexmind.establishment.usecases.employee.UpdateEmployee;

@RestController
@RequestMapping("api/v1/employee/")
public class EmployeeController {

    private final SaveEmployee saveEmployee;
    private final UpdateEmployee updateEmployee;
    private final DetailEmployee detailEmployee;
    private final DeleteEmployee deleteEmployee;

    public EmployeeController(SaveEmployee saveEmployee, UpdateEmployee updateEmployee, DetailEmployee detailEmployee, DeleteEmployee deleteEmployee) {
        this.saveEmployee = saveEmployee;
        this.updateEmployee = updateEmployee;
        this.detailEmployee = detailEmployee;
        this.deleteEmployee = deleteEmployee;
    }

    @PostMapping("/save")
    public ResponseEntity<EmployeeDTO> save(SaveEmployeeDTO employeeDTO, UriComponentsBuilder uriBuilder){
        var employee = saveEmployee.execute(employeeDTO);
        var uri = uriBuilder.path("/save/{id}").buildAndExpand(employee.getId()).toUri();
        return  ResponseEntity.created(uri).body(EmployeeConverter.toDTO(employee));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> update  (
            @PathVariable Long id,
            @RequestBody UpdateEmployeeDTO employeeDTO){

        var employee = updateEmployee.execute(employeeDTO, id);
        return  ResponseEntity.ok().body(EmployeeConverter.toDTO(employee));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> getEstablishment(@PathVariable
                                                             Long id){
        var employee = detailEmployee.execute(id);
        return  ResponseEntity.ok().body(EmployeeConverter.toDTO(employee));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteEstablishment(@PathVariable Long id){
        deleteEmployee.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
