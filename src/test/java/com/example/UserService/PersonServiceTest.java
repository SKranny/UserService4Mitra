package com.example.UserService;

import com.example.UserService.dto.PersonDTO;
import com.example.UserService.mappers.PersonMapper;
import com.example.UserService.models.Person;
import com.example.UserService.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.ProviderException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PersonServiceTest {


    @Mock
    private PersonRepository personRepository;

    private PersonMapper personMapper;
    private Person person;

    @Autowired
    public void setPersonMapper(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    @BeforeEach
    public void init() {
        person = Person.builder()
                .id(1L)
                .email("some@mail.com")
                .firstName("Evgeny")
                .lastName("Smirnov")
                .birthDay(LocalDate.of(1995, 12, 19))
                .build();
    }

    @Test
    public void findByEmailTest() {
        when(personRepository.findByEmail(person.getEmail())).thenReturn(Optional.of(person));
        person = personRepository.findByEmail("some@mail.com").orElseThrow(
                () -> new ProviderException("Error! User with this email not found!"));
        PersonDTO personDTO = personMapper.toPersonDTO(person);

        assertNotNull(personDTO);
        assertEquals(1L, personDTO.getId());
        assertEquals("some@mail.com", personDTO.getEmail());
        assertEquals("Evgeny", personDTO.getFirstName());
        assertEquals("Smirnov", personDTO.getLastName());
        assertEquals(LocalDate.of(1995, 12, 19), personDTO.getBirthDay());

    }


}
