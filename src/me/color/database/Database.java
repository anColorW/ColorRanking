package me.color.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {


    Connection con;
    {
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ranking","root","");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//for (int i = 0; i < 5 && rs.next(); i++) {
//
//    itemList.add(rs.getString("idName"));
//
//}

    public void printDataArray(String query, ArrayList<String> array){
        try{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            for (int i = 0; i < 5 && rs.next(); i++) {
                array.add(rs.getString("PlayerName"));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public String printData(String query, int column){
        try{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(query);

            while (rs.next()) {
                return rs.getObject(column).toString();
            }
        }
        catch(Exception e){
            System.out.println(e);
        }

        return "";
    }

    public void database(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){ System.out.println(e);}
    }


    public void insertQuery(String query) {
        try{
            if(!con.isClosed()) {
                Statement stmt = con.createStatement();
                stmt.executeUpdate(query);

            }
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    public boolean exist(String query) {
        try {
            if(!con.isClosed()){
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery(query);
                if(rs.next())
                    return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void executeQuery(String query) throws SQLException {
        if(!con.isClosed()){
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));

        }
    }

}
