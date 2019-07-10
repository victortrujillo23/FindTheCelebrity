package com.findthecelebrity.data.datamanager;

import java.util.List;

import com.findthecelebrity.dto.PersonDto;
import com.findthecelebrity.exception.DataSourceProblemException;

public interface PersonDataManager {
   
   public List<PersonDto> getPersons() throws DataSourceProblemException;
}
