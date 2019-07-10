package com.findthecelebrity.data.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.findthecelebrity.data.entity.PersonEntity;

public class PersonInMemoryDatabaseImpl implements PersonRepository{
   private static final List<PersonEntity> persons=new ArrayList<PersonEntity>();
   private static final Map<Integer,List<PersonEntity>> contacts=new HashMap<Integer,List<PersonEntity>>();
   static {
      persons.add(new PersonEntity(1, "Memory 1"));
      persons.add(new PersonEntity(2, "Memory 2"));
      persons.add(new PersonEntity(3, "Memory 3"));
      persons.add(new PersonEntity(4, "Memory 4"));
      persons.add(new PersonEntity(5, "Memory 5"));
      
      List<PersonEntity> contactsMemory1=new ArrayList<PersonEntity>();
      contactsMemory1.add(new PersonEntity(1, "Memory 1"));
      contactsMemory1.add(new PersonEntity(2, "Memory 2"));
      contactsMemory1.add(new PersonEntity(3, "Memory 3"));
      contactsMemory1.add(new PersonEntity(4, "Memory 4"));
      contactsMemory1.add(new PersonEntity(5, "Memory 5"));
      contacts.put(1, contactsMemory1);
      List<PersonEntity> contactsMemory2=new ArrayList<PersonEntity>();
      contactsMemory2.add(new PersonEntity(1, "Memory 1"));
      contactsMemory2.add(new PersonEntity(3, "Memory 3"));
      contactsMemory2.add(new PersonEntity(5, "Memory 5"));
      contacts.put(2, contactsMemory2);
      List<PersonEntity> contactsMemory3=new ArrayList<PersonEntity>();
      contacts.put(3, contactsMemory3);
      List<PersonEntity> contactsMemory4=new ArrayList<PersonEntity>();
      contactsMemory4.add(new PersonEntity(1, "Memory 1"));
      contactsMemory4.add(new PersonEntity(3, "Memory 3"));
      contacts.put(4, contactsMemory4);
      List<PersonEntity> contactsMemory5=new ArrayList<PersonEntity>();
      contactsMemory5.add(new PersonEntity(3, "Memory 3"));
      contacts.put(5, contactsMemory5);
   }
   
   
   @Override
   public List<PersonEntity> getPersons() {
      return persons;
   }

   @Override
   public List<PersonEntity> getPersonRelation(Integer id) {
      return contacts.get(id);
   }
   
   
   
   
   
   
}
