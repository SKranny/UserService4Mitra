package com.example.UserService.services;

import com.example.UserService.dto.PersonDTO;
import com.example.UserService.dto.UpdatePersonRequest;
import com.example.UserService.dto.UpdateRoleRequest;

import java.util.List;

public interface PersonServiceInterface {
    PersonDTO createPerson(PersonDTO personDTO);

    void deletePerson(Long userId);

    PersonDTO editPerson(UpdatePersonRequest updatePersonRequest, Long id);

    List<PersonDTO> findAllPersons();

    PersonDTO getPersonById(Long id);

    PersonDTO updateRole(Long id, UpdateRoleRequest updateRoleRequest);

    PersonDTO getPersonByEmail(String email);
}
