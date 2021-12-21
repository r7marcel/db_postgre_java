import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        DbFunctions db = new DbFunctions();
        Connection conn = db.connect_to_db("test", "postgres", "asdfg");

        // db.createTable(conn, "employee");

        //CREATE
        //db.insert_row(conn, "employee", "Grzegorz", "Wrocław");
        //db.createEmptyTable(conn, "Manchester");
        //db.addColumn(conn, "Manchester", "position", "varchar(200)");

        // db.addPrimaryKey(conn, "Manchester", "manid");
        //db.read_by_column(conn, "employee", "name", "Robert");
        //db.read_from_any_table(conn, "Manchester");


        //READ
        //db.read_data(conn,"Employee");
        //db.read_by_id(conn, "Employee", 2);
        //db.read_by_value(conn, "employee", "name", "Robert");

        //UPDATE
        //db.update(conn, "Employee", "name", "Maciek", "Maciej");
        //db.update_name(conn, "Employee", "Eric Cantona", "Wasyl");
        //db.update_address(conn, "Employee", "985 Boundary Street Jacksonville, FL, 32207", "Miami");

        //DELETE
        //db.delete_record(conn, "Employee", "empid", "1");
        //db.delete_table(conn, "Manchester");

    }
}
