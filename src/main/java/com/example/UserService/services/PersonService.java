package com.example.UserService.services;

import com.example.UserService.constants.RoleType;
import com.example.UserService.dto.PersonDTO;
import com.example.UserService.dto.UpdatePersonRequest;
import com.example.UserService.dto.UpdateRoleRequest;
import com.example.UserService.exceptions.PersonException;
import com.example.UserService.mappers.PersonMapper;
import com.example.UserService.models.Person;
import com.example.UserService.repositories.PersonRepository;
import com.example.UserService.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService implements PersonServiceInterface{
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final RoleRepository roleRepository;

    @Override
    public PersonDTO createPerson(PersonDTO personDTO) {
        return personMapper.toPersonDTO(personRepository.save(personMapper.toPerson(personDTO)));
    }

    @Override
    public void deletePerson(Long userId) {
        personRepository.deleteById(userId);
    }

    @Override
    public PersonDTO editPerson(UpdatePersonRequest updatePersonRequest, Long id) {
        Person foundPerson = personRepository.findById(id)
                .orElseThrow(()-> new PersonException("Error! Person is not found!"));
        foundPerson.setBirthDay(updatePersonRequest.getBirthDay());
        foundPerson.setEmail(updatePersonRequest.getEmail());
        foundPerson.setLastName(updatePersonRequest.getLastName());
        foundPerson.setFirstName(updatePersonRequest.getFirstName());
        return personMapper.toPersonDTO(personRepository.save(foundPerson));
    }

    @Override
    public List<PersonDTO> findAllPersons() {
        return personRepository.findAll()
                .stream()
                .map(personMapper::toPersonDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDTO getPersonById(Long id) {
        return personRepository.findById(id)
                .map(personMapper::toPersonDTO)
                .orElseThrow(() -> new PersonException("Error! Person is not found!"));
    }

    @Override
    public PersonDTO updateRole(Long id, UpdateRoleRequest updateRoleRequest) {
        Person foundPerson = personRepository.findById(id)
                .orElseThrow(() -> new PersonException("Error! Person is not found!"));
        updatePersonRole(foundPerson, updateRoleRequest.getRole());
        return personMapper.toPersonDTO(foundPerson);
    }

    @Override
    public PersonDTO getPersonByEmail(String email) {
        return personMapper.toPersonDTO(personRepository.findByEmail(email)
                .orElseThrow(() -> new PersonException("Error! Person is not found!")));
    }

    private void updatePersonRole(Person person,String role){
        switch (role){
            case "admin": {
                person.getRoles().clear();
                person.getRoles().add(roleRepository.findByRole(RoleType.ROLE_ADMIN).get());
            }

            case "user": {
                person.getRoles().clear();
                person.getRoles().add(roleRepository.findByRole(RoleType.ROLE_USER).get());
            }
        }
    }

}
