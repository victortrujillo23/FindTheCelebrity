package com.findthecelebrity.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.findthecelebrity.data.datamanager.PersonDataManager;
import com.findthecelebrity.dto.PersonDto;
import com.findthecelebrity.exception.DataSourceProblemException;
import com.findthecelebrity.service.PersonServiceImpl;

public class PersonServiceImplTest {
   private final static int ID_OF_CELEBRITY=5;
   
   private PersonDataManager personDataManager;
   private PersonServiceImpl personServiceImpl;
   
   
   public void initTestWithCelebrity() throws DataSourceProblemException {
      personDataManager=mock(PersonDataManager.class);
      when(personDataManager.getPersons()).thenReturn(getPersonsWithCelebrity());
      personServiceImpl=new PersonServiceImpl(personDataManager);
   }
   
   public void initTestWithoutCelebrity() throws DataSourceProblemException {
      personDataManager=mock(PersonDataManager.class);
      when(personDataManager.getPersons()).thenReturn(getPersonsWithouthCelebrity());
      personServiceImpl=new PersonServiceImpl(personDataManager);
   }
   
   
   @Test
   public void shouldFindTheCelebrity() throws DataSourceProblemException {
      initTestWithCelebrity();
      PersonDto personDto=personServiceImpl.findTheCelebrity();
      assertEquals(ID_OF_CELEBRITY,personDto.getId());
   }
   
   @Test
   public void shouldNotFindTheCelebrity() throws DataSourceProblemException {
      initTestWithoutCelebrity();
      PersonDto personDto=personServiceImpl.findTheCelebrity();
      assertNull(personDto);
   }
   
   
   
   private List<PersonDto> getPersonsWithCelebrity(){
      List<PersonDto> persons=new ArrayList<PersonDto>();
      PersonDto carlos=new PersonDto(1,"carlos");
      PersonDto andres=new PersonDto(2,"andres");
      PersonDto mike=new PersonDto(3,"mike");
      PersonDto rene=new PersonDto(4,"rene");
      PersonDto miguel=new PersonDto(5,"miguel");
      
      carlos.addContact(andres);
      carlos.addContact(miguel);
      
      andres.addContact(carlos);
      andres.addContact(mike);
      andres.addContact(rene);
      andres.addContact(miguel);
      
      mike.addContact(miguel);
      
      rene.addContact(andres);
      rene.addContact(miguel);
      
      persons.add(carlos);
      persons.add(andres);
      persons.add(mike);
      persons.add(rene);
      persons.add(miguel);
      
      return persons;
   }
   
   private List<PersonDto> getPersonsWithouthCelebrity(){
      List<PersonDto> persons=new ArrayList<PersonDto>();
      PersonDto carlos=new PersonDto(1,"carlos");
      PersonDto andres=new PersonDto(2,"andres");
      PersonDto mike=new PersonDto(3,"mike");
      PersonDto rene=new PersonDto(4,"rene");
      PersonDto miguel=new PersonDto(5,"miguel");
      
      carlos.addContact(andres);
      carlos.addContact(miguel);
      
      andres.addContact(carlos);
      andres.addContact(mike);
      andres.addContact(rene);
      andres.addContact(miguel);
      
      mike.addContact(miguel);
      
      rene.addContact(andres);
      
      persons.add(carlos);
      persons.add(andres);
      persons.add(mike);
      persons.add(rene);
      persons.add(miguel);
      
      return persons;
   }
}
