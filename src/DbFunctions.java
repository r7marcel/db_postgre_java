import java.sql.*;
import java.util.ArrayList;


public class DbFunctions {
    public Connection connect_to_db(String dbname, String user, String pass) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
            if (conn != null) {
                System.out.println("Connection successful");
            } else {
                System.out.println("Connection failed!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    // CREATE FUNCTIONS


    public void createTable(Connection conn, String table_name) {
        Statement statement;

        try {
            String query = "CREATE TABLE " + table_name + "(empid SERIAL, name varchar(200), address varchar(200), PRIMARY KEY(empid));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void createEmptyTable(Connection conn, String table_name) {
        Statement statement;

        try {
            String query = String.format("CREATE TABLE %s ();",table_name);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created");
        } catch (Exception e) {
            System.out.println(e);
        }

    }



    public void addColumn(Connection conn, String table_name, String column_name, String column_type) {
        Statement statement;

        try {
            String query = String.format("AlTER TABLE %s ADD COLUMN %s %s;", table_name, column_name, column_type);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Column added successfully");
        } catch (Exception e) {
            System.out.println(e);
        }

    }



    //insert value
    public void insert_row(Connection conn, String table_name, String name, String address) {
        Statement statement;

        try {

            String query = String.format("insert into %s(name, address) values ('%s', '%s');", table_name, name, address);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Inserted.");


        } catch (Exception e) {
            System.out.println(e);
        }
    }


    //READ DATA FUNCTIONS

    public void read_data(Connection conn, String table_name) {
        Statement statement;
        ResultSet rs = null;
        try {

            String query = String.format("Select * from %s", table_name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);

            while (rs.next()) {
                System.out.print(rs.getString("empid") + "   ");
                System.out.print(rs.getString("name") + "    ");
                System.out.println(rs.getString("address") + " ");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void read_by_value(Connection conn, String table_name, String column_name, String value) {
        Statement statement;
        ResultSet rs = null;

        try {
            String query = String.format("Select * from %s where %s='%s'", table_name, column_name, value);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);

            while (rs.next()) {

                System.out.print(rs.getString("empid") + "      ");
                System.out.print(rs.getString("name") + "       ");
                System.out.println(rs.getString("address") + " ");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }


    public void read_by_id(Connection conn, String table_name, int empid) {
        Statement statement;
        ResultSet rs = null;

        try {
            String query = String.format("Select * from %s where empid=%d", table_name, empid);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);

            while (rs.next()) {

                System.out.print("empid" + "      ");
                System.out.print("name" + "       ");
                System.out.println("address" + " ");
                System.out.print(rs.getString("empid") + "      ");
                System.out.print(rs.getString("name") + "       ");
                System.out.println(rs.getString("address") + " ");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }


    public void read_by_column(Connection conn, String table_name, String column_name, String params) {
        Statement statement;
        ResultSet rs = null;
        ResultSet rsColumns = null;

        try {
            String query = String.format("Select * from %s where %s='%s'", table_name, column_name, params);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);

            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();

            ArrayList<String> columns = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metadata.getColumnName(i);
                columns.add(columnName);
            }

            while (rs.next()) {
                for (String columnName : columns) {
                    String value = rs.getString(columnName);
                    System.out.println(columnName + " = " + value);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    public void read_from_any_table(Connection conn, String table_name) {
        Statement statement;
        ResultSet rs = null;
        ResultSet rsColumns = null;

        try {
            String query = String.format("Select * from %s", table_name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);

            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();

            ArrayList<String> columns = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metadata.getColumnName(i);
                columns.add(columnName);
            }

            if (columns.isEmpty()) System.out.println("Table is empty");

            while (rs.next()) {
                for (String columnName : columns) {
                    String value = rs.getString(columnName);
                    System.out.println(value+"; ");
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }



    // UPDATE FUNCTIONS


    public void update(Connection conn, String table_name, String column_name, String old_name, String new_name) {

        Statement statement;
        try {
            String query = String.format("UPDATE %s SET %s='%s' WHERE %s='%s';", table_name, column_name, new_name, column_name, old_name);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Record updated successfully");
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    public void update_name(Connection conn, String table_name, String old_name, String new_name) {

        Statement statement;
        try {
            String query = String.format("UPDATE %s SET name='%s' where name='%s';", table_name, new_name, old_name);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Record updated successfully");

        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public void update_address(Connection conn, String table_name, String old_address, String new_address) {

        Statement statement;
        try {
            String query = String.format("UPDATE %s SET address='%s' where address='%s';", table_name, new_address, old_address);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Record updated successfully");

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    // DELETE FUNCTIONS

    public void delete_record(Connection conn, String table_name, String column_name, String params) {
        Statement statement;

        try {
            String query = String.format("DELETE FROM %s where %s=%s", table_name, column_name, params);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Record deleted successfully");


        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public void delete_table(Connection conn, String table_name) {
        Statement statement;

        try{
            String query = String.format("DROP TABLE %s", table_name);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table is deleted successfully.");

        } catch (Exception e){
            System.out.println(e);
        }

    }


    public void addPrimaryKey(Connection conn, String table_name, String column_name) {
        Statement statement;

        try{
            String query = String.format("Alter table %s add primary key (%s)", table_name, column_name);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Primary key added successfully.");

        } catch (Exception e){
            System.out.println(e);
        }

    }
}