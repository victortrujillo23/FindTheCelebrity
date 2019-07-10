package com.findthecelebrity.dto;

import java.util.HashSet;
import java.util.Set;

public class PersonDto {
   
   private String name;
   private int id;
   private Set<PersonDto> contacts;
   
   public PersonDto(final int id,final String name) {
      this.id=id;
      this.name=name;
      this.contacts=new HashSet<PersonDto>();
   }
   
   
   
   public String getName() {
      return this.name;
   }
   
   public int getId() {
      return this.id;
   }
   
   public Set<PersonDto> getContacts(){
      return this.contacts;
   }
   
   public void addContact(final PersonDto personDto) {
      this.contacts.add(personDto);
   }

   @Override
   public int hashCode() {
      return id;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      PersonDto other = (PersonDto) obj;
      if (id != other.id)
         return false;
      return true;
   }
   
   
   
   
    

}
