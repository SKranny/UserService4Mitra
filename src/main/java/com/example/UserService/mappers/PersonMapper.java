package com.example.UserService.mappers;

import com.example.UserService.dto.PersonDTO;
import com.example.UserService.models.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",
        uses = {RoleSetMapper.class},
        imports = LocalDateTime.class)
public interface PersonMapper {
    PersonDTO toPersonDTO(Person person);

    Person toPerson(PersonDTO dto);
}
