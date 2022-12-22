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

        City oslo = new City("Karlstad", "KRL", "Värmland", 34562 );
        City addedCity = cityDao.add(oslo);
        System.out.println(addedCity);



    }
}
