package com.codexmind.establishment.controller;

import com.codexmind.establishment.converters.EstablishmentConverter;
import com.codexmind.establishment.dto.EstablishmentDTO;
import com.codexmind.establishment.usecases.employee.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.codexmind.establishment.converters.EmployeeConverter;
import com.codexmind.establishment.dto.EmployeeDTO;
import com.codexmind.establishment.dto.saveUpdate.SaveEmployeeDTO;
import com.codexmind.establishment.dto.saveUpdate.UpdateEmployeeDTO;

@RestController
@RequestMapping("api/v1/employee/")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearer-key")
public class EmployeeController {

    private final SaveEmployee saveEmployee;
    private final UpdateEmployee updateEmployee;
    private final DetailEmployee detailEmployee;
    private final DeleteEmployee deleteEmployee;

    private final SaveImageEmployee saveImageEmployee;

    private final GetImageEmployee getImageEmployee;

    public EmployeeController(SaveEmployee saveEmployee, UpdateEmployee updateEmployee, DetailEmployee detailEmployee, DeleteEmployee deleteEmployee, SaveImageEmployee saveImageEmployee, GetImageEmployee getImageEmployee) {
        this.saveEmployee = saveEmployee;
        this.updateEmployee = updateEmployee;
        this.detailEmployee = detailEmployee;
        this.deleteEmployee = deleteEmployee;

        this.saveImageEmployee = saveImageEmployee;
        this.getImageEmployee = getImageEmployee;
    }

    @PostMapping("/save")
    public ResponseEntity<EmployeeDTO> save(SaveEmployeeDTO employeeDTO, UriComponentsBuilder uriBuilder){
        var employee = saveEmployee.execute(employeeDTO);
        var uri = uriBuilder.path("/save/{id}").buildAndExpand(employee.getId()).toUri();
        return  ResponseEntity.created(uri).body(EmployeeConverter.toDTO(employee));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> update  (
            @PathVariable Integer id,
            @RequestBody UpdateEmployeeDTO employeeDTO){

        var employee = updateEmployee.execute(employeeDTO, id);
        return  ResponseEntity.ok().body(EmployeeConverter.toDTO(employee));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable
                                                            Integer id){
        var employee = detailEmployee.execute(id);
        return  ResponseEntity.ok().body(EmployeeConverter.toDTO(employee));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteEstablishment(@PathVariable Integer id){
        deleteEmployee.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/image/{id}")
    public ResponseEntity<Void> updateImage(@RequestParam("file") MultipartFile file,
                                                   @PathVariable Integer id){
        var uri = saveImageEmployee.execute(file, id);
        return ResponseEntity.created(uri).build();

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<EmployeeDTO> getUserEmployeeById(@PathVariable Integer id){
        var employee = getImageEmployee.execute(id).get();
        return ResponseEntity.ok().body(EmployeeConverter.toDTOResponse(employee));
    }

}
