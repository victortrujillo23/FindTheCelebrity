package com.findthecelebrity.data.entity;

public class PersonEntity {
   
   private String name;
   private int id;
   
   public PersonEntity(final int id,final String name) {
      this.id=id;
      this.name=name;
   }
   
   public String getName() {
      return this.name;
   }
   
   public int getId() {
      return this.id;
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
      PersonEntity other = (PersonEntity) obj;
      if (id != other.id)
         return false;
      return true;
   }
   
    

}
