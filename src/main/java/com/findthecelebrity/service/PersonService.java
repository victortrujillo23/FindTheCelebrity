package com.findthecelebrity.service;

import com.findthecelebrity.dto.PersonDto;
import com.findthecelebrity.exception.DataSourceProblemException;

public interface PersonService {
   public PersonDto findTheCelebrity() throws DataSourceProblemException;
}
