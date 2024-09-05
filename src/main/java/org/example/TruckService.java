package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TruckService {
    public void addTruck(Truck truck) throws SQLException {
     //id not needed becz we have used auto_increment
        String sql = "insert into truck (name, model, capacity, driver_name) values(?,?,?,?)";
        try {
            Connection connection = ConnectionDetails.getconnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,truck.getName());
            preparedStatement.setString(2,truck.getModel());
            preparedStatement.setInt(3,truck.getCapacity());
            preparedStatement.setString(4,truck.getDriver_name());

            int update = preparedStatement.executeUpdate();
            System.out.println("Row inserted :"+update);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public Truck getTruckById(int id)
    {
        String sql = "select * from truck where id = ?";
        Truck truck = new Truck();
        try {
            Connection connection =  ConnectionDetails.getconnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                truck.setId(resultSet.getInt("id"));
                truck.setName(resultSet.getString("name"));
                truck.setModel(resultSet.getString("model"));
                truck.setCapacity(resultSet.getInt("capacity"));
                truck.setDriver_name(resultSet.getString("driver_name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return truck;
    }
    public void updateTruck(Truck truck)
    {
        String sql = "update truck set name = ?, model = ?, capacity = ?, driver_name = ? where id = ?";
        try {
            Connection connection = ConnectionDetails.getconnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,truck.getName());
            preparedStatement.setString(2, truck.getModel());
            preparedStatement.setInt(3,truck.getCapacity());
            preparedStatement.setString(4,truck.getDriver_name());
            preparedStatement.setInt(5,truck.getId());

            int update = preparedStatement.executeUpdate();
            System.out.println("Row updated :"+update);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public List<Truck> getAllTrucks() throws SQLException {

        String sql = "select * from truck";
        List<Truck> trucks = new ArrayList<Truck>();
        try {
            Connection connection = ConnectionDetails.getconnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next())
            {
                Truck truck = new Truck();
                truck.setId(resultSet.getInt("id"));
                truck.setName(resultSet.getString("name"));
                truck.setModel(resultSet.getString("model"));
                truck.setCapacity(resultSet.getInt("capacity"));
                truck.setDriver_name(resultSet.getString("driver_name"));
                trucks.add(truck);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return trucks;
    }
    public void deleteTruck(int id)
    {
        String deleteQuery = "delete from truck where id = ?";
        try {
            Connection connection = ConnectionDetails.getconnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1,id);
            int update = preparedStatement.executeUpdate();
            System.out.println("Row Deleted :"+update);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
