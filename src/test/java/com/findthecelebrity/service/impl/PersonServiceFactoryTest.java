package com.findthecelebrity.service.impl;

import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;

import com.findthecelebrity.exception.DataSourceProblemException;
import com.findthecelebrity.service.PersonService;
import com.findthecelebrity.service.PersonServiceFactory;
import com.findthecelebrity.util.Constants;
import com.findthecelebrity.util.DatasourceType;

public class PersonServiceFactoryTest {
   private static final Map<String,String> params=new HashMap<String,String>();
   
   @Before
   public void init() {
      params.put(Constants.FILE_PATH_PARAM, "Path");
   }
   
   public void shouldReturnInstaceWhenIsExcel() throws DataSourceProblemException {
      PersonService personService=PersonServiceFactory.getPersonService(DatasourceType.EXCEL,params);
      assertNull(personService);
   }
   
   public void shouldReturnInstaceWhenIsMemory() throws DataSourceProblemException {
      PersonService personService=PersonServiceFactory.getPersonService(DatasourceType.MEMORY,params);
      assertNull(personService);
   }

}
