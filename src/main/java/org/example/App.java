package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) throws SQLException {
        TruckService truckService = new TruckService();
        Truck tata = new Truck("Tata","2019",1000,"Rajesh");
        Truck volvo = new Truck("volvo","2021",1000,"Akash");
        Truck AshokLay = new Truck("Ashok","2019",1000,"Harshit");

        //adding data into dataBase
        truckService.addTruck(tata);
        truckService.addTruck(volvo);
        truckService.addTruck(AshokLay);

        //Fetch
        Truck truck = truckService.getTruckById(1);
        System.out.println("Truck data :"+truck);

        //updating truck data
        truck.setDriver_name("Ramesh");
        truckService.updateTruck(truck);
        System.out.println("Updated truck data "+truckService.getTruckById(1));

        //Get all truck data
        List<Truck> allTrucks = truckService.getAllTrucks();
        for(Truck truck1 : allTrucks)
        {
            System.out.println(truck1);
        }

        //delete truck data
        truckService.deleteTruck(2);
        System.out.println("Data deleted by id "+2);

        allTrucks = truckService.getAllTrucks();
        System.out.println("All trucks after all operations ");


    }
}
