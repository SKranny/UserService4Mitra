package com.example.UserService.controllers;

import com.example.UserService.dto.PersonDTO;
import com.example.UserService.dto.UpdatePersonRequest;
import com.example.UserService.dto.UpdateRoleRequest;
import com.example.UserService.services.PersonServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class PersonController {
    private final PersonServiceInterface personServiceInterface;

    @Operation(summary = "Создание пользователя")
    @PostMapping("/editor/create")
    public PersonDTO createPerson(@Valid @RequestBody PersonDTO personDTO) {
        return personServiceInterface.createPerson(personDTO);
    }

    @Operation(summary = "Удаление пользователя")
    @DeleteMapping("/editor/delete/{Id}")
    public void deleteMyAccount(@PathVariable(name = "id") Long Id) {
        personServiceInterface.deletePerson(Id);
    }

    @Operation(summary = "Редактирование пользователя")
    @PutMapping("/editor/update")
    public PersonDTO editMyAccount(@Valid @RequestBody UpdatePersonRequest updatePersonRequest, Long id) {
        return personServiceInterface.editPerson(updatePersonRequest, id);
    }

    @Operation(summary = "Получить всех пользователей")
    @GetMapping
    public List<PersonDTO> getAllPersonsDTO() {
        return personServiceInterface.findAllPersons();
    }

    @Operation(summary = "Получение пользователя по id")
    @GetMapping("/editor/{id}")
    public PersonDTO getPersonById(@PathVariable(name = "id") Long id) {
        return personServiceInterface.getPersonById(id);
    }
    @Operation(summary = "Смена роли пользователя по id")
    @PutMapping("/editor/{id}")
    public PersonDTO updatePersonRole(@Valid @RequestBody Long id, UpdateRoleRequest updateRoleRequest){
        return personServiceInterface.updateRole(id, updateRoleRequest);
    }

    @Operation(summary = "Получение пользователя по email")
    @GetMapping("/{email}")
    public PersonDTO getPersonByEmail(@PathVariable(name = "email") String email) {
        return personServiceInterface.getPersonByEmail(email);
    }


}
