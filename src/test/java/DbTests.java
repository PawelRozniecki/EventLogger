import com.creditsuisse.pawelrozniecki.LogReader;
import com.creditsuisse.pawelrozniecki.db.DatabaseConnection;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;


public class DbTests {

    private static final String filePath = "../CreditSuisseAssignment/src/main/resources/logfile.txt";

    @Test
    public void testDBconnection(){
        DatabaseConnection db = new DatabaseConnection();
        assertNotNull(db.setupConnection());
    }
    @Test
    public void checkServerRunning(){
        DatabaseConnection db = new DatabaseConnection();
        db.startServer();
        // getState() returns 1 if ONLINE, 16 if SHUTDOWN
        assertEquals(1, db.getHsqlServer().getState());
        db.getHsqlServer().shutdown();
        assertEquals(16, db.getHsqlServer().getState());

    }

    @Test
    public void testInsertIntoTable(){

        LogReader reader = new LogReader(filePath);
        reader.readFile(new File(filePath));

        DatabaseConnection db = new DatabaseConnection();
        db.setupConnection();
        db.createTable();
        assertTrue(db.save(reader.getFlaggedEvents()));
    }



}
