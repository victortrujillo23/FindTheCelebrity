package com.findthecelebrity.service;

import java.util.Map;

import com.findthecelebrity.data.datamanager.PersonDataManagerImpl;
import com.findthecelebrity.data.repository.PersonRepository;
import com.findthecelebrity.data.repository.PersonRepositoryFactory;
import com.findthecelebrity.exception.DataSourceProblemException;
import com.findthecelebrity.util.Constants;
import com.findthecelebrity.util.DatasourceType;

public class PersonServiceFactory {

   public static PersonService getPersonService(DatasourceType type, Map<String, String> paramsMap) {
      PersonRepository personRepository = null;
      switch (type) {
      case EXCEL:
         personRepository = PersonRepositoryFactory.getPersonExcelRepository(paramsMap.get(Constants.FILE_PATH_PARAM));
         break;
      case MEMORY:
         personRepository = PersonRepositoryFactory.getPersonInMemoryRepository();
         break;
      }
      return new PersonServiceImpl(new PersonDataManagerImpl(personRepository));
   }
}
