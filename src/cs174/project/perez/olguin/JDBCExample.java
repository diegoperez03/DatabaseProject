package cs174.project.perez.olguin;

import oracle.sql.TIMESTAMP;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class JDBCExample {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@cloud-34-133.eci.ucsb.edu:1521:XE";

    //  Database credentials
    static final String USERNAME = "diego03";
    static final String PASSWORD = "9624610";

    public static void main(String[] args) {

    }//end main

    public static void addCustomer(Customer custom ,Double deposit, String accountType){
        Connection conn = null;
        Statement stmt = null;
        try{

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = String.format("INSERT INTO Customers " +
                    "VALUES ('%s','%s','%s','%s') ",custom.taxId,custom.name,custom.address,custom.pin);
            System.out.println(sql);
            stmt.executeUpdate(sql);

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");

    }

    public static void addBanker(Banker bank){
        Connection conn = null;
        Statement stmt = null;
        try{

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = String.format("INSERT INTO Bankers " +
                    "VALUES ('%s','%s') ",bank.bankId,bank.branchName);
            System.out.println(sql);
            stmt.executeUpdate(sql);

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");

    }

    public static String getNameFromPin(String Pin){
        Connection conn = null;
        Statement stmt = null;
        try{

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = String.format("SELECT name FROM Customers WHERE pin = %s ",Pin);
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String name = rs.getString("name");
                return name;
            }


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static String getNameFromBankerId(String bankerId){
        Connection conn = null;
        Statement stmt = null;
        try{

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = String.format("SELECT bankername FROM Bankers WHERE bankerid = %s ",bankerId);
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String name = rs.getString("bankername");
                return name;
            }


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static String getUniqueTransactionId(){
        Connection conn = null;
        Statement stmt = null;
        try{

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = "SELECT transactionid FROM CustomerTransaction ";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<String> ids = new ArrayList<>();
            while(rs.next()){
                ids.add(rs.getString("transactionid"));
            }
            Random rand = new Random();
            Integer randomId = rand.nextInt(999999999) + 1;
            while(ids.contains(Integer.toString(randomId))){
                randomId = rand.nextInt(999999999) + 1;
            }

            return Integer.toString(randomId);


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static String getIdFromPin(String pin){
        Connection conn = null;
        Statement stmt = null;
        try{

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = String.format("SELECT taxid FROM Customers WHERE pin = %s ",pin);
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String id = rs.getString("taxid");
                return id;
            }


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static HashMap<String,String> getAvailableAccounts(String id){
        Connection conn = null;
        Statement stmt = null;
        try{

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database

            System.out.println(id);
            String sql = String.format("SELECT accounttype,accountid FROM Account WHERE taxid = %s",id);
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            HashMap<String,String> list = new HashMap<>();
            while(rs.next()) {
                String s = rs.getString("accounttype");
                String x = rs.getString("accountid");
                list.put(s,x);

            }
            return list;

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static String CustomerDepositTransaction(String accountId, String taxId, Double Amount,
                                                    String TransactionType){
        Connection conn = null;
        Statement stmt = null;
        try{

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String transactionId = getUniqueTransactionId();
            String sql = String.format("INSERT INTO CustomerTransaction " +
                    "VALUES ('%s','%s','%s','%s',CURRENT_TIMESTAMP,'%s' ) ",accountId,taxId,Amount,TransactionType,transactionId);
            System.out.println(sql);
            stmt.executeUpdate(sql);
            Double total = getBalance(accountId) + Amount;

            String sql2 = String.format("UPDATE Account "+
                    "SET balance = %f WHERE accountid = %s",total,taxId);
            System.out.println(sql);
            stmt.executeUpdate(sql2);

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static Double getBalance(String accountId){
        Connection conn = null;
        Statement stmt = null;
        try{

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = String.format("SELECT balance FROM Account WHERE accountid = %s ",accountId);
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Double amount = rs.getDouble("balance");
                return amount;
            }


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }



}
