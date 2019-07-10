package com.findthecelebrity.service;

import java.util.List;
import java.util.Optional;

import com.findthecelebrity.data.datamanager.PersonDataManager;
import com.findthecelebrity.dto.PersonDto;
import com.findthecelebrity.exception.DataSourceProblemException;

public class PersonServiceImpl implements PersonService {
   private PersonDataManager personDataManager;

   public PersonServiceImpl(final PersonDataManager personDataManager) {
      this.personDataManager = personDataManager;
   }

   @Override
   public PersonDto findTheCelebrity() throws DataSourceProblemException {
      PersonDto celebrity = null;
      List<PersonDto> persons = personDataManager.getPersons();
      Optional<PersonDto> personThatNoKnowsEveryBody = persons.stream().parallel()
            .filter(person -> person.getContacts().isEmpty() == true).findAny();
      boolean isCelebrity = true;
      if (personThatNoKnowsEveryBody != null && personThatNoKnowsEveryBody.get() != null) {
         for (PersonDto person : persons) {
            if (!person.getContacts().contains(personThatNoKnowsEveryBody.get())
                  && !personThatNoKnowsEveryBody.get().equals(person)) {
               isCelebrity = false;
            }
         }
         if (isCelebrity) {
            celebrity = personThatNoKnowsEveryBody.get();
         }
      }
      return celebrity;
   }

}
