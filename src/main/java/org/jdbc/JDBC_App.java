package org.jdbc;

import org.jdbc.dao.CityDao;
import org.jdbc.dao.CityDaoJDBC;
import org.jdbc.model.City;

public class JDBC_App {
    public static void main(String[] args) {

        CityDao cityDao = new CityDaoJDBC();
        /*City Id = cityDao.findById(100);
        System.out.println(Id);

        List<City> byCode = cityDao.findByCode("AFG");
        System.out.println(byCode);

        List<City> byName = cityDao.findByName("Breda");
        System.out.println(byName);

        List<City> listOfCities = cityDao.findAll();
        System.out.println(listOfCities);*/

        City karlstad = new City("Kashmir", "IND", "Maharashtra", 3456223 );
        cityDao.add(karlstad);

       /* City addCity = new City("Mörrum", "SWE", "Blekinge",4123);
        //City addedCity = cityDao.add(addCity);*/

        //int deleteCity = cityDao.delete(addCity);

    }
}
