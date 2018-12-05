package cs174.project.perez.olguin;

import oracle.sql.TIMESTAMP;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

public class JDBCExample {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@cloud-34-133.eci.ucsb.edu:1521:XE";

    //  Database credentials
    static final String USERNAME = "diego03";
    static final String PASSWORD = "9624610";

    public static String day = "6";
    public static String month = "Dec";
    public static String year = "18";

    public static void main(String[] args) {

    }//end main

    public static void addCustomer(Customer custom) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = String.format("INSERT INTO Customers " +
                    "VALUES ('%s','%s','%s','%s') ", custom.taxId, custom.name, custom.address, custom.pin);
            System.out.println(sql);
            stmt.executeUpdate(sql);
            System.out.println("success");


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");

    }

    public static void addBanker(Banker bank) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = String.format("INSERT INTO Bankers " +
                    "VALUES ('%s','%s') ", bank.bankId, bank.branchName);
            System.out.println(sql);
            stmt.executeUpdate(sql);

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");

    }

    public static void addAccount(String accountId, String name, Double deposit, String accounttype, String Bankname, Double interest) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String taxid = getIdFromName(name);
            String sql = String.format("INSERT INTO Account " +
                            "VALUES ('%s','%s','%s','%s','%f','%s','%f') ", accountId,
                    accounttype, taxid, Bankname, interest, "f", deposit);
            System.out.println(sql);
            stmt.executeUpdate(sql);


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");

    }

    public static String getIdFromName(String name) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = String.format("SELECT taxid FROM Customers WHERE name = '%s' ", name);
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString("taxid");
                System.out.println(id);
                return id;
            }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static String getNameFromId(String taxId) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = String.format("SELECT name FROM Customers WHERE taxid = %s ", taxId);
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                return name;
            }


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static String getAddressFromId(String taxId) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = String.format("SELECT address FROM Customers WHERE taxid = %s ", taxId);
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("address");
                return name;
            }


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static String getNameFromBankerId(String bankerId) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = String.format("SELECT bankername FROM Bankers WHERE bankerid = %s ", bankerId);
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("bankername");
                return name;
            }


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static String getUniqueAccountId() {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = "SELECT accountid FROM Account ";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<String> ids = new ArrayList<>();
            while (rs.next()) {
                ids.add(rs.getString("accountid"));
            }
            Random rand = new Random();
            Integer randomId = rand.nextInt(999999999) + 1;
            while (ids.contains(Integer.toString(randomId))) {
                randomId = rand.nextInt(999999999) + 1;
            }

            return Integer.toString(randomId);


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static String getUniqueTransactionId() {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = "SELECT transcid FROM CustomerTransaction ";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<String> ids = new ArrayList<>();
            while (rs.next()) {
                ids.add(rs.getString("transcid"));
            }
            Random rand = new Random();
            Integer randomId = rand.nextInt(999999999) + 1;
            while (ids.contains(Integer.toString(randomId))) {
                randomId = rand.nextInt(999999999) + 1;
            }

            return Integer.toString(randomId);


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static String getUniqueBankerTransactionId() {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = "SELECT transcid FROM BankerTransaction ";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<String> ids = new ArrayList<>();
            while (rs.next()) {
                ids.add(rs.getString("transcid"));
            }
            Random rand = new Random();
            Integer randomId = rand.nextInt(999999999) + 1;
            while (ids.contains(Integer.toString(randomId))) {
                randomId = rand.nextInt(999999999) + 1;
            }

            return Integer.toString(randomId);


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static String verifyPin(String pin) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = String.format("SELECT taxid FROM Customers WHERE pin = %s ", pin);
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString("taxid");
                return id;
            }


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static String SetPin(String OldPIN, String NewPIN) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = String.format("UPDATE Customers " +
                    "SET pin = %s WHERE pin = %s", NewPIN, OldPIN);
            System.out.println(sql);
            stmt.executeUpdate(sql);


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static HashMap<String, String> getCheckingSavingAccounts(String id) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database

            System.out.println(id);
            String sql = String.format("SELECT accounttype,accountid FROM Account WHERE taxid = %s AND " +
                    "accounttype != 'Pocket'", id);
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            HashMap<String, String> list = new HashMap<>();
            while (rs.next()) {
                String s = rs.getString("accounttype");
                String x = rs.getString("accountid");
                list.put(s, x);

            }
            return list;

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static HashMap<String, String> getAllAccounts(String id) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database

            System.out.println(id);
            String sql = String.format("SELECT accounttype,accountid FROM Account WHERE taxid = %s", id);
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            HashMap<String, String> list = new HashMap<>();
            while (rs.next()) {
                String s = rs.getString("accounttype");
                String x = rs.getString("accountid");
                list.put(s, x);

            }
            return list;

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static String CustomerDepositTransaction(String accountId, String taxId, Double Amount,
                                                    String TransactionType) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String transactionId = getUniqueTransactionId();
            Double total = getBalance(accountId) + Amount;

            String sql = String.format("INSERT INTO CustomerTransaction " +
                    "VALUES ('%s','%s','%s','%s',CURRENT_TIMESTAMP,'%s' ) ", accountId, taxId, total, TransactionType, transactionId);
            System.out.println(sql);
            stmt.executeUpdate(sql);

            String sql2 = String.format("UPDATE Account " +
                    "SET balance = %f WHERE accountid = %s", total, accountId);
            System.out.println(sql2);
            stmt.executeUpdate(sql2);

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static String CustomerWithdrawalTransaction(String accountId, String taxId, Double Amount,
                                                       String TransactionType) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String transactionId = getUniqueTransactionId();
            Double total = getBalance(accountId) - Amount;
            String sql = String.format("INSERT INTO CustomerTransaction " +
                    "VALUES ('%s','%s','%s','%s',CURRENT_TIMESTAMP,'%s' ) ", accountId, taxId, total, TransactionType, transactionId);
            System.out.println(sql);
            stmt.executeUpdate(sql);


            String sql2 = String.format("UPDATE Account " +
                    "SET balance = %f WHERE accountid = %s", total, accountId);
            System.out.println(sql2);
            stmt.executeUpdate(sql2);

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static String CustomerWireTransaction(String FromAccountId, String taxId, Double Amount, String ToAccountID) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String transactionId = getUniqueTransactionId();
            Double total = getBalance(FromAccountId) - (Amount * 1.02);

            String sql = String.format("INSERT INTO CustomerTransaction " +
                    "VALUES ('%s','%s','%s','%s',CURRENT_TIMESTAMP,'%s' ) ", FromAccountId, taxId, total, "Wire", transactionId);
            System.out.println(sql);
            stmt.executeUpdate(sql);

            String sql2 = String.format("INSERT INTO CustomerTransaction " +
                    "VALUES ('%s','%s','%s','%s',CURRENT_TIMESTAMP,'%s' ) ", ToAccountID, taxId, total, "Wire", transactionId);
            System.out.println(sql2);
            stmt.executeUpdate(sql2);

            String sql4 = String.format("UPDATE Account " +
                    "SET balance = %f WHERE accountid = %s", total, FromAccountId);
            System.out.println(sql4);
            stmt.executeUpdate(sql4);


            Double total2 = getBalance(ToAccountID) + Amount;
            String sql3 = String.format("UPDATE Account " +
                    "SET balance = %f WHERE accountid = %s", total2, ToAccountID);
            System.out.println(sql3);
            stmt.executeUpdate(sql3);

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static Double getBalance(String accountId) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = String.format("SELECT balance FROM Account WHERE accountid = %s ", accountId);
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Double amount = rs.getDouble("balance");
                return amount;
            }


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static ArrayList<String> getPocketAccountIds(String taxid) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = String.format("SELECT accountid FROM Account WHERE accounttype Like 'Pocket' AND taxid = %s ", taxid);
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<String> accountids = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("accountid");
                accountids.add(id);
            }
            System.out.println(accountids.size());
            return accountids;


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static String CustomerTopUpTransaction(String FromAccountId, String taxId, String pocketId, Double Amount) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String transactionId = getUniqueTransactionId();
            Double total = getBalance(FromAccountId) - (Amount);
            Double total2 = getBalance(pocketId) + Amount;


            String sql = String.format("INSERT INTO CustomerTransaction " +
                    "VALUES ('%s','%s','%s','%s',CURRENT_TIMESTAMP,'%s' ) ", FromAccountId, taxId, total, "Top-Up", transactionId);
            String sql2 = String.format("INSERT INTO CustomerTransaction " +
                    "VALUES ('%s','%s','%s','%s',CURRENT_TIMESTAMP,'%s' ) ", pocketId, taxId, total2, "Top-Up", transactionId


            );
            System.out.println(sql);
            stmt.executeUpdate(sql);
            System.out.println(sql2);
            stmt.executeUpdate(sql2);

            String sql4 = String.format("UPDATE Account " +
                    "SET balance = %f WHERE accountid = %s", total, FromAccountId);
            System.out.println(sql4);
            stmt.executeUpdate(sql4);


            String sql3 = String.format("UPDATE Account " +
                    "SET balance = %f WHERE accountid = %s", total2, pocketId);
            System.out.println(sql3);
            stmt.executeUpdate(sql3);

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static String CustomerPayFriendTransaction(String taxid, String friendId, String pocketId, Double Amount) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String transactionId = getUniqueTransactionId();
            String sql = String.format("INSERT INTO CustomerTransaction " +
                    "VALUES ('%s','%s','%s','%s',CURRENT_TIMESTAMP,'%s' ) ", pocketId, taxid, Amount, "Pay-Friend", transactionId);

            System.out.println(sql);
            stmt.executeUpdate(sql);

            String sql2 = String.format("INSERT INTO CustomerTransaction " +
                    "VALUES ('%s','%s','%s','%s',CURRENT_TIMESTAMP,'%s' ) ", friendId, taxid, Amount, "Pay-Friend", transactionId);
            System.out.println(sql2);
            stmt.executeUpdate(sql2);
            Double total = getBalance(pocketId) - (Amount);

            String sql4 = String.format("UPDATE Account " +
                    "SET balance = %f WHERE accountid = %s", total, pocketId);
            System.out.println(sql4);
            stmt.executeUpdate(sql4);


            Double total2 = getBalance(friendId) + Amount;
            String sql3 = String.format("UPDATE Account " +
                    "SET balance = %f WHERE accountid = %s", total2, friendId);
            System.out.println(sql3);
            stmt.executeUpdate(sql3);

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static String CustomerPurchaseTransaction(String taxid, String pocketId, Double Amount) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String transactionId = getUniqueTransactionId();
            String sql = String.format("INSERT INTO CustomerTransaction " +
                    "VALUES ('%s','%s','%s','%s',CURRENT_TIMESTAMP,'%s' ) ", pocketId, taxid, Amount, "Purchase", transactionId);

            System.out.println(sql);
            stmt.executeUpdate(sql);


            Double total = getBalance(pocketId) - (Amount);

            String sql4 = String.format("UPDATE Account " +
                    "SET balance = %f WHERE accountid = %s", total, pocketId);
            System.out.println(sql4);
            stmt.executeUpdate(sql4);


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static String CustomerCollectTransaction(String taxid, String pocketId, String accountid, Double Amount) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String transactionId = getUniqueTransactionId();
            String sql = String.format("INSERT INTO CustomerTransaction " +
                    "VALUES ('%s','%s','%s','%s',CURRENT_TIMESTAMP,'%s' ) ", pocketId, taxid, Amount, "Collect", transactionId);

            System.out.println(sql);
            stmt.executeUpdate(sql);

            String sql2 = String.format("INSERT INTO CustomerTransaction " +
                    "VALUES ('%s','%s','%s','%s',CURRENT_TIMESTAMP,'%s' ) ", accountid, taxid, Amount, "Collect", transactionId);

            System.out.println(sql2);
            stmt.executeUpdate(sql2);

            Double total = getBalance(pocketId) - (Amount * 1.03);

            String sql4 = String.format("UPDATE Account " +
                    "SET balance = %f WHERE accountid = %s", total, pocketId);
            System.out.println(sql4);
            stmt.executeUpdate(sql4);

            total = getBalance(accountid) + (Amount);

            String sql3 = String.format("UPDATE Account " +
                    "SET balance = %f WHERE accountid = %s", total, accountid);
            System.out.println(sql3);
            stmt.executeUpdate(sql3);


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static String CustomerWriteCheckTransaction(String taxid, String accountid, Double Amount) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database

            String transactionId = getUniqueTransactionId();
            String sql = String.format("INSERT INTO CustomerTransaction " +
                    "VALUES ('%s','%s','%s','%s',CURRENT_TIMESTAMP,'%s' ) ", accountid, taxid, Amount, "Write-Check", transactionId);

            System.out.println(sql);
            stmt.executeUpdate(sql);

            Double total = getBalance(accountid) - (Amount);

            String sql4 = String.format("UPDATE Account " +
                    "SET balance = %f WHERE accountid = %s", total, accountid);
            System.out.println(sql4);
            stmt.executeUpdate(sql4);


            return transactionId;


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static String CustomerQuickCashTransaction(String taxid, String accountid, Double Amount) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String transactionId = getUniqueTransactionId();
            Double total = getBalance(accountid) - (Amount);
            String sql = String.format("INSERT INTO CustomerTransaction " +
                    "VALUES ('%s','%s','%s','%s',CURRENT_TIMESTAMP,'%s' ) ", accountid, taxid, total, "Quick Cash", transactionId);

            System.out.println(sql);
            stmt.executeUpdate(sql);


            String sql4 = String.format("UPDATE Account " +
                    "SET balance = %f WHERE accountid = %s", total, accountid);
            System.out.println(sql4);
            stmt.executeUpdate(sql4);


            return transactionId;


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }


    public static String CustomerQuickRefillTransaction(String taxid, String accountid, Double Amount, String pocketid) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String transactionId = getUniqueTransactionId();
            Double total = getBalance(accountid) - (Amount);
            String sql = String.format("INSERT INTO CustomerTransaction " +
                    "VALUES ('%s','%s','%s','%s',CURRENT_TIMESTAMP,'%s' ) ", accountid, taxid, total, "Quick Cash", transactionId);

            System.out.println(sql);
            stmt.executeUpdate(sql);
            total = getBalance(pocketid) + (Amount);
            String sql2 = String.format("INSERT INTO CustomerTransaction " +
                    "VALUES ('%s','%s','%s','%s',CURRENT_TIMESTAMP,'%s' ) ", pocketid, taxid, total, "Quick Cash", transactionId);

            System.out.println(sql2);
            stmt.executeUpdate(sql2);


            String sql4 = String.format("UPDATE Account " +
                    "SET balance = %f WHERE accountid = %s", total, accountid);
            System.out.println(sql4);
            stmt.executeUpdate(sql4);

            String sql3 = String.format("UPDATE Account " +
                    "SET balance = %f WHERE accountid = %s", total, pocketid);
            System.out.println(sql3);
            stmt.executeUpdate(sql3);


            return transactionId;


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }


    public static String CustomerTransferTransaction(String FromAccountId, String taxId, Double Amount, String ToAccountID) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String transactionId = getUniqueTransactionId();
            String sql = String.format("INSERT INTO CustomerTransaction " +
                    "VALUES ('%s','%s','%s','%s',CURRENT_TIMESTAMP,'%s' ) ", FromAccountId, taxId, Amount, "Transfer", transactionId);
            System.out.println(sql);
            stmt.executeUpdate(sql);

            String sql4 = String.format("INSERT INTO CustomerTransaction " +
                    "VALUES ('%s','%s','%s','%s',CURRENT_TIMESTAMP,'%s' ) ", ToAccountID, taxId, Amount, "Transfer", transactionId);
            System.out.println(sql4);
            stmt.executeUpdate(sql4);

            Double total = getBalance(FromAccountId) - (Amount);

            String sql2 = String.format("UPDATE Account " +
                    "SET balance = %f WHERE accountid = %s", total, FromAccountId);
            System.out.println(sql2);
            stmt.executeUpdate(sql2);


            Double total2 = getBalance(ToAccountID) + Amount;
            String sql3 = String.format("UPDATE Account " +
                    "SET balance = %f WHERE accountid = %s", total2, ToAccountID);
            System.out.println(sql3);
            stmt.executeUpdate(sql3);

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static Double getInterest(String accountId) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = String.format("SELECT interest FROM Account WHERE accountid = %s ", accountId);
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Double interest = rs.getDouble("interest");
                return interest;
            }


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static Double AccrueInterest(String accountId, String taxid) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = String.format("SELECT time,balanceafter FROM CustomerTransaction WHERE accountid = %s and time > sysdate-30 ", accountId);
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            Double interest = getInterest(accountId);
            Timestamp previousstamp;
            Double previousBalance;
            int counter = 0;
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_YEAR, -30);
            long fiveDaysAgo = cal.getTimeInMillis();
            rs.next();
            counter++;
            Timestamp thirtyDaysAgo = new Timestamp(fiveDaysAgo);
            previousBalance = rs.getDouble("balanceafter");
            previousstamp = rs.getTimestamp("time");
            System.out.println(thirtyDaysAgo);
            System.out.println(previousstamp);
            double accrued = 0;
            while (rs.next()) {

                Timestamp currentStamp = rs.getTimestamp("time");
                Double currentBalance = rs.getDouble("balanceafter");
                //check if there on the same date
                //if not on the same date then add the number of days between stamps * the balance
                //if on the same date then don't add
                if (previousstamp.toString().substring(0, 10).equals(currentStamp.toString().substring(0, 10))) {
                    previousstamp = currentStamp;
                    previousBalance = currentBalance;
                    System.out.println("option1");
                    continue;
                } else {
                    if (counter == 1) {
                        long differenceNanos = previousstamp.getTime() - thirtyDaysAgo.getTime();
                        double differenceDays = Math.ceil(differenceNanos / ((1000) * 60 * 60 * 24));
                        accrued += differenceDays * previousBalance;
                        System.out.println(differenceDays + " : " + accrued + " : " + counter);


                        differenceNanos = currentStamp.getTime() - previousstamp.getTime();
                        differenceDays = Math.ceil(differenceNanos / ((1000) * 60 * 60 * 24));

                        previousstamp = currentStamp;
                        previousBalance = currentBalance;
                        accrued += differenceDays * previousBalance;
                        System.out.println(differenceDays + " : " + accrued + " : " + counter);

                        counter++;
                        System.out.println("option2");

                    } else {
                        long differenceNanos = currentStamp.getTime() - previousstamp.getTime();
                        double differenceDays = Math.ceil(differenceNanos / ((1000) * 60 * 60 * 24));
                        previousstamp = currentStamp;
                        previousBalance = currentBalance;
                        accrued += differenceDays * previousBalance;
                        System.out.println(differenceDays + " : " + accrued + " : " + counter);
                        counter++;
                        System.out.println("option3");


                    }
                }
            }

            long differenceNanos = System.currentTimeMillis() - previousstamp.getTime();
            double differenceDays = Math.ceil(differenceNanos / ((1000) * 60 * 60 * 24));
            if (differenceDays < 1) {
                differenceDays++;
            }
            accrued += differenceDays * previousBalance;
            System.out.println(differenceDays + " : " + accrued + " : " + counter);
            accrued = (accrued / 30) * interest * .01;


            System.out.println(accrued + " you accrued this much");


            return accrued;


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }


    public static String CustomerAccruedInterestTransaction(String taxId, String accountID, Double Amount) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String transactionId = getUniqueTransactionId();
            Amount += getBalance(accountID);
            String sql = String.format("INSERT INTO CustomerTransaction " +
                    "VALUES ('%s','%s','%s','%s',CURRENT_TIMESTAMP,'%s' ) ", accountID, taxId, Amount, "Transfer", transactionId);
            System.out.println(sql);
            stmt.executeUpdate(sql);


            String sql2 = String.format("UPDATE Account " +
                    "SET balance = %f WHERE accountid = %s", Amount, accountID);
            System.out.println(sql2);
            stmt.executeUpdate(sql2);


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }


    public static String BankerEnterCheckTransaction(String accountId, String taxId, Double amount, String TransactionType, String bankerid) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String transactionId = getUniqueBankerTransactionId();
            String sql = String.format("INSERT INTO BankerTransaction " +
                    "VALUES ('%s','%s', CURRENT_TIMESTAMP,'%s' ) ", bankerid, "Wire", transactionId);
            System.out.println(sql);
            stmt.executeUpdate(sql);
            Double total = getBalance(accountId) + amount;

            String sql2 = String.format("UPDATE Account " +
                    "SET balance = %f WHERE accountid = %s", total, accountId);
            System.out.println(sql2);
            stmt.executeUpdate(sql2);

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");

        return null;
    }

    public static HashMap<Timestamp, String> getTransactionsFromAccount(String accountId) {
        Connection conn = null;
        Statement stmt = null;
        HashMap<Timestamp, String> hashMap = new HashMap<>();
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = String.format("SELECT transactiontype, time FROM CustomerTransaction WHERE accountid = %s and time > sysdate-30 ", accountId);
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String ttype = rs.getString("transactiontype");
                Timestamp timestamp = rs.getTimestamp("time");
                hashMap.put(timestamp, ttype);
            }
            System.out.println(hashMap.size());
            return hashMap;

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;

    }

    public static ArrayList<String> getAIDfromIncreaseTransactions(String bankerid) {
        Connection conn = null;
        Statement stmt = null;
        ArrayList<String> aidList = new ArrayList<>();
        ArrayList<Double> balances = new ArrayList<>();
        Double totalBalance = 0.0;
        ArrayList<String> names = new ArrayList<>();
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = String.format("SELECT * FROM CustomerTransaction WHERE transactiontype = 'Deposit' OR transactiontype = 'Wire' OR transactiontype = 'Transfer' AND time > sysdate-30 ");
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String aid = rs.getString("accountid");
                if (!aidList.contains(aid)) {
                    aidList.add(aid);
                }
            }
            for (int i = 0; i < aidList.size(); i++) {
                String sql2 = String.format("SELECT * FROM CustomerTransaction WHERE transactiontype = 'Deposit' OR transactiontype = 'Wire' OR transactiontype = 'Transfer' AND time > sysdate-30 AND accountid = %s ", aidList.get(i));
                ResultSet rs2 = stmt.executeQuery(sql2);
                while (rs2.next()) {
                    String acc = rs2.getString("accountid");
                    Double value = rs2.getDouble("balanceafter");
                    if (acc.equals(aidList.get(i))) {
                        balances.add(value);
                    }
                }
                for (int j = 0; j < balances.size(); j++) {
                    totalBalance += balances.get(j);
                }
                if (totalBalance >= 10000) {
                    names.add(aidList.get(i));
                    totalBalance = 0.0;
                    balances.clear();
                }

            }
            return names;
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;

    }

    public static ArrayList<Double> getFinalInitialBalance(String accountId) {
        Connection conn = null;
        Statement stmt = null;
        ArrayList<Double> arrayList = new ArrayList<>();
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = String.format("SELECT balanceafter FROM CustomerTransaction WHERE accountid = %s and time > sysdate-30 ", accountId);
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            boolean first = true;
            boolean temp = false;
            Double initial = 0.0;
            Double last = 0.0;
            while (rs.next()) {
                temp = true;
                //String ttype = rs.getString("transactiontype");
                if (first) {
                    initial = rs.getDouble("balanceafter");
                    first = false;
                }
                last = rs.getDouble("balanceafter");
            }

            Double balance = 0.0;
            if (temp = false) {
                balance = getBalance(accountId);
                initial = balance;
                last = balance;
            }

            arrayList.add(initial);
            arrayList.add(last);

            return arrayList;

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;

    }

    public static HashMap<String, String> BankerListClosedAccounts(String bankerid) {
        Connection conn = null;
        Statement stmt = null;
        HashMap<String, String> hashMap = new HashMap<>();
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String f = "false";
            String sql = String.format("SELECT accountid, accounttype FROM Account WHERE closed = 't' ");
            System.out.println(sql);
            stmt.executeUpdate(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String accountid = rs.getString("accountid");
                String accounttype = rs.getString("accounttype");
                hashMap.put(accountid, accounttype);
            }
            System.out.println(hashMap.size());

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String transactionId = getUniqueBankerTransactionId();
            String sql2 = String.format("INSERT INTO BankerTransaction " +
                    "VALUES ('%s','%s', CURRENT_TIMESTAMP,'%s' ) ", bankerid, "ListClosedAccounts", transactionId);
            System.out.println(sql2);
            stmt.execute(sql2);

            return hashMap;

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static ArrayList<ArrayList<String>> BankerCustomerReport(String bankerid, String taxId) {
        Connection conn = null;
        Statement stmt = null;
        ArrayList<String> values = new ArrayList<>();
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        HashMap<String, String> hashMap = new HashMap<>();
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = String.format("SELECT accountid, accounttype, closed FROM Account WHERE taxid = %s ", taxId);
            System.out.println(sql);
            stmt.executeUpdate(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String accountid = rs.getString("accountid");
                String accounttype = rs.getString("accounttype");
                String closed = rs.getString("closed");
                System.out.println(accountid + " " + accounttype + " " + closed + '\n');
                values.add(accountid);
                values.add(accounttype);
                values.add(closed);
                result.add(new ArrayList<>(values));
                values.clear();
            }

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String transactionId = getUniqueBankerTransactionId();
            String sql2 = String.format("INSERT INTO BankerTransaction " +
                    "VALUES ('%s','%s', CURRENT_TIMESTAMP,'%s' ) ", bankerid, "BankerCustomerReport", transactionId);
            System.out.println(sql2);
            stmt.execute(sql2);

            return result;

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static String BankerAddInterest(String bankerid) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String transactionId = getUniqueBankerTransactionId();
            String sql = String.format("INSERT INTO BankerTransaction " +
                    "VALUES ('%s','%s', CURRENT_TIMESTAMP,'%s' ) ", bankerid, "AddInterest", transactionId);
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static String BankerDeleteTransactions(String bankerid) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = String.format("DELETE FROM CustomerTransaction");
            System.out.println(sql);
            stmt.executeUpdate(sql);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String transactionId = getUniqueBankerTransactionId();
            String sql2 = String.format("INSERT INTO BankerTransaction " +
                    "VALUES ('%s','%s', CURRENT_TIMESTAMP,'%s' ) ", bankerid, "DeleteTransactions", transactionId);
            System.out.println(sql2);
            stmt.execute(sql2);

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static String BankerDeleteClosedAccounts(String bankerid) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = String.format("DELETE FROM Account WHERE closed = 't' ");
            System.out.println(sql);
            stmt.executeUpdate(sql);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql2 = "DELETE FROM Customers WHERE taxid = All(SELECT c.taxid FROM Customers c where c.taxid != All(SELECT taxid FROM Account))";
            System.out.println(sql2);
            stmt.executeUpdate(sql2);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String transactionId = getUniqueBankerTransactionId();
            String sql3 = String.format("INSERT INTO BankerTransaction " +
                    "VALUES ('%s','%s', CURRENT_TIMESTAMP,'%s' ) ", bankerid, "DeleteClosedAccounts", transactionId);
            System.out.println(sql3);
            stmt.execute(sql3);

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public static void ChangeInterestRate(String atype, Double intrate) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //Insert into database
            String sql = String.format("UPDATE Account " +
                    "SET interest = %f WHERE accounttype = %s", intrate, atype);
            System.out.println(sql);
            stmt.executeUpdate(sql);

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }

    public static void SetDate(String dy, String mnth, String yr){
        day = dy;
        month = mnth;
        year = yr;
    }

    public static StringBuffer ChangeDate(Timestamp timestamp){
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy");
        StringBuffer buffer = new StringBuffer(format.format(timestamp));
        buffer.replace(7,9, year);
        buffer.replace(0,2,day );
        buffer.replace(3,6,month);
        return buffer;
    }

}
