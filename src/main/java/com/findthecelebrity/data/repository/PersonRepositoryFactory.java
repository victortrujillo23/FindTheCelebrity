package com.findthecelebrity.data.repository;

import com.findthecelebrity.exception.DataSourceProblemException;

public class PersonRepositoryFactory {
   
   public static PersonRepository getPersonExcelRepository(final String filepath) {
      return new PersonRepositoryExcelImpl(filepath);
   }
   
   public static PersonRepository getPersonInMemoryRepository(){
      return new PersonInMemoryDatabaseImpl();
   }
}
