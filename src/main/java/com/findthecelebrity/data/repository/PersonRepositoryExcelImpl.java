package com.findthecelebrity.data.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.findthecelebrity.data.entity.PersonEntity;
import com.findthecelebrity.exception.DataSourceProblemException;

public class PersonRepositoryExcelImpl implements PersonRepository {
   private final static String MARK = "X";
   private String filePath;

   PersonRepositoryExcelImpl(final String filePath) {
      this.filePath = filePath;
   }

   @Override
   public List<PersonEntity> getPersons() throws DataSourceProblemException {
      return getPersonsOfCVSFile();
   }

   @Override
   public List<PersonEntity> getPersonRelation(final Integer id) throws DataSourceProblemException {
      return getPersonContacsOfCVSFile(id);
   }

   private List<PersonEntity> getPersonsOfCVSFile() throws DataSourceProblemException {
      List<PersonEntity> response = new ArrayList<PersonEntity>();
      try {
         Workbook workbook = WorkbookFactory.create(new File(filePath));
         Sheet sheet = workbook.getSheetAt(0);
         Iterator<Row> rowIterator = sheet.rowIterator();
         rowIterator.next();
         while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            final int id = new Double(row.getCell(0).getNumericCellValue()).intValue();
            final String name = row.getCell(1).getStringCellValue();
            response.add(new PersonEntity(new Integer(id), name));
         }
      } catch (InvalidFormatException e) {
         throw new DataSourceProblemException(e);
      } catch (IOException e) {
         throw new DataSourceProblemException(e);
      }
      return response;
   }

   private List<PersonEntity> getPersonContacsOfCVSFile(final Integer id) throws DataSourceProblemException {
      List<PersonEntity> response = new ArrayList<PersonEntity>();
      try {
         Workbook workbook = WorkbookFactory.create(new File(filePath));
         Sheet sheet = workbook.getSheetAt(0);
         Iterator<Row> rowIterator = sheet.rowIterator();
         rowIterator.next();
         while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            final int idpers = new Double(row.getCell(0).getNumericCellValue()).intValue();
            if (idpers == id) {
               Iterator<Cell> cellIterator = row.cellIterator();
               cellIterator.next();
               cellIterator.next();
               int i = 2;
               while (cellIterator.hasNext()) {
                  Cell cell = cellIterator.next();
                  if (cell.getStringCellValue() != null && MARK.equals(cell.getStringCellValue().toUpperCase())) {
                     int idcontact = new Double(sheet.getRow(0).getCell(i).getNumericCellValue()).intValue();
                     if (idcontact != id) {
                        PersonEntity contact = findPersonById(idcontact);
                        if (contact != null) {
                           response.add(contact);
                        } else {
                           throw new DataSourceProblemException(new Exception("The id of the contact doesn't exist"));
                        }
                     }
                  }
                  i++;
               }
               break;
            }
         }
      } catch (InvalidFormatException e) {
         throw new DataSourceProblemException(e);
      } catch (IOException e) {
         throw new DataSourceProblemException(e);
      }
      return response;
   }

   private PersonEntity findPersonById(final int id) throws DataSourceProblemException {
      try {
         Workbook workbook = WorkbookFactory.create(new File(filePath));
         Sheet sheet = workbook.getSheetAt(0);
         Iterator<Row> rowIterator = sheet.rowIterator();
         rowIterator.next();
         while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            final int idfound = new Double(row.getCell(0).getNumericCellValue()).intValue();
            final String name = row.getCell(1).getStringCellValue();
            if (idfound == id) {
               return new PersonEntity(new Integer(id), name);
            }
         }
      } catch (InvalidFormatException e) {
         throw new DataSourceProblemException(e);
      } catch (IOException e) {
         throw new DataSourceProblemException(e);
      }
      return null;
   }

}
