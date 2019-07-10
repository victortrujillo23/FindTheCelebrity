package com.findthecelebrity.data.repository;

import java.util.List;

import com.findthecelebrity.data.entity.PersonEntity;
import com.findthecelebrity.exception.DataSourceProblemException;

public interface PersonRepository {
   
   public List<PersonEntity>  getPersons() throws DataSourceProblemException;
   
   public List<PersonEntity> getPersonRelation(final Integer id) throws DataSourceProblemException;

}
