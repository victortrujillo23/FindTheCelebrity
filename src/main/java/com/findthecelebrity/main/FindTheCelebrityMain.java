package com.findthecelebrity.main;

import java.util.HashMap;
import java.util.Map;

import com.findthecelebrity.dto.PersonDto;
import com.findthecelebrity.service.PersonService;
import com.findthecelebrity.service.PersonServiceFactory;
import com.findthecelebrity.util.Constants;
import com.findthecelebrity.util.DatasourceType;

public class FindTheCelebrityMain {

   public static void main(String[] args) throws Exception {
      DatasourceType datasourceType = getTypeForString(args[0]);
      Map<String, String> mapsParams = getParamsMapByType(datasourceType, args);
      PersonService personService = PersonServiceFactory.getPersonService(datasourceType, mapsParams);
      PersonDto celebrity = personService.findTheCelebrity();
      if (celebrity != null) {
         System.out.println("The celebrity is:" +celebrity.getId()+" - "+celebrity.getName());
      } else {
         System.out.println("The celebrity is not found");
      }
   }

   private static Map<String, String> getParamsMapByType(DatasourceType datasourceType, String[] args) {
      Map<String, String> mapsParams = new HashMap<String, String>();
      switch (datasourceType) {
      case EXCEL:
         mapsParams.put(Constants.FILE_PATH_PARAM, args[1]);
         break;
      case MEMORY:
         break;
      }
      return mapsParams;
   }

   private static DatasourceType getTypeForString(final String dataType) throws Exception {
      for (DatasourceType datasourceType : DatasourceType.values()) {
         if (datasourceType.toString().equals(dataType)) {
            return datasourceType;
         }
      }
      throw new Exception("The type of source is not found");
   }

}
