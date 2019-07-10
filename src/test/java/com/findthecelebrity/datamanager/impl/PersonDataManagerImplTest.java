package com.findthecelebrity.datamanager.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.findthecelebrity.data.datamanager.PersonDataManagerImpl;
import com.findthecelebrity.data.entity.PersonEntity;
import com.findthecelebrity.data.repository.PersonRepository;
import com.findthecelebrity.dto.PersonDto;
import com.findthecelebrity.exception.DataSourceProblemException;

public class PersonDataManagerImplTest {
   private static final int SIZE_OF_PERSON_LIST=4;
   private static final int SIZE_OF_CARLOS_CONTACTS_LIST=0;
   private static final int SIZE_OF_ANDRES_CONTACTS_LIST=3;
   
   private PersonRepository personRepository;
   
   private PersonDataManagerImpl personDataManagerImpl; 
   
   public void whenDontHaveTroubles() throws DataSourceProblemException {
      personRepository=mock(PersonRepository.class);
      when(personRepository.getPersons()).thenReturn(getPersons());
      when(personRepository.getPersonRelation(1)).thenReturn(getNotCarlosRelation());
      when(personRepository.getPersonRelation(2)).thenReturn(getNotCarlosRelation());
      when(personRepository.getPersonRelation(3)).thenReturn(getNotCarlosRelation());
      when(personRepository.getPersonRelation(4)).thenReturn(getCarlosRelation());
      personDataManagerImpl=new PersonDataManagerImpl(personRepository);
   }
   
   public void whenHaveTroubles() throws DataSourceProblemException {
      personRepository=mock(PersonRepository.class);
      when(personRepository.getPersons()).thenThrow(new DataSourceProblemException(new Exception("Bad Data")));
      personDataManagerImpl=new PersonDataManagerImpl(personRepository);
   }
   
   
   
   @Test
   public void shouldThePersonsAre4() throws DataSourceProblemException {
      whenDontHaveTroubles();
      List<PersonDto> result=personDataManagerImpl.getPersons();
      assertEquals(SIZE_OF_PERSON_LIST, result.size());
   }
   
   
   @Test
   public void shouldCarlosContactsAre0() throws DataSourceProblemException {
      whenDontHaveTroubles();
      PersonDto carlos=personDataManagerImpl.getPersons().get(3);
      assertEquals(SIZE_OF_CARLOS_CONTACTS_LIST, carlos.getContacts().size());
   }
   
   @Test
   public void shouldAndresContactsAre3() throws DataSourceProblemException {
      whenDontHaveTroubles();
      PersonDto andres=personDataManagerImpl.getPersons().get(1);
      assertEquals(SIZE_OF_ANDRES_CONTACTS_LIST, andres.getContacts().size());
   }
   
   @Test(expected=DataSourceProblemException.class)
   public void shouldThrowException() throws DataSourceProblemException {
      whenHaveTroubles();
      PersonDto andres=personDataManagerImpl.getPersons().get(1);
      assertEquals(SIZE_OF_ANDRES_CONTACTS_LIST, andres.getContacts().size());
   }
   
   
   private List<PersonEntity>  getPersons(){
      List<PersonEntity> persons=new ArrayList<PersonEntity>();
      persons.add(new PersonEntity(1,"Andres"));
      persons.add(new PersonEntity(2,"Mike"));
      persons.add(new PersonEntity(3,"Rene"));
      persons.add(new PersonEntity(4,"Carlos"));
      return persons;
   }
   
   private List<PersonEntity> getNotCarlosRelation(){
      List<PersonEntity> contacts=new ArrayList<PersonEntity>();
      contacts.add(new PersonEntity(1,"Andres"));
      contacts.add(new PersonEntity(2,"Mike"));
      contacts.add(new PersonEntity(3,"Rene"));
      return contacts;
   }
   private List<PersonEntity> getCarlosRelation(){
      List<PersonEntity> contacts=new ArrayList<PersonEntity>();
      return contacts;
   }
   
}
