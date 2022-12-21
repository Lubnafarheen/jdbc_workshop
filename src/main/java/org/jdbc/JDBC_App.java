package org.jdbc;

public class JDBC_App {
    public static void main(String[] args) {

        CityDao cityDao = new CityDaoJDBC();
        City Id = cityDao.findById(1);
        System.out.println(Id);
    }
}
