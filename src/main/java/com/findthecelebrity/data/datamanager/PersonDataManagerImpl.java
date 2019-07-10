package com.findthecelebrity.data.datamanager;

import java.util.ArrayList;
import java.util.List;

import com.findthecelebrity.data.entity.PersonEntity;
import com.findthecelebrity.data.repository.PersonRepository;
import com.findthecelebrity.dto.PersonDto;
import com.findthecelebrity.exception.DataSourceProblemException;

public class PersonDataManagerImpl implements PersonDataManager{
   
   private PersonRepository personRepository;
   
   public PersonDataManagerImpl(final PersonRepository personRepository) {
      this.personRepository=personRepository;
   }
   
  
   public List<PersonDto> getPersons() throws DataSourceProblemException{
      List<PersonDto> result=new ArrayList<>();
      try {
         for(PersonEntity personEntity:this.personRepository.getPersons()) {
            PersonDto personDto=new PersonDto(personEntity.getId(),personEntity.getName());
            for(PersonEntity contact:this.personRepository.getPersonRelation(personDto.getId())) {
               personDto.addContact(new PersonDto(contact.getId(), personDto.getName()));
            }
            result.add(personDto);
         }
      } catch (DataSourceProblemException e) {
         throw e;
      }
      return result;
   }
   
   
}
