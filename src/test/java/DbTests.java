import com.creditsuisse.pawelrozniecki.LogReader;
import com.creditsuisse.pawelrozniecki.db.DatabaseConnection;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;


public class DbTests {

    private static final String filePath = "../CreditSuisseAssignment/src/main/resources/logfile.txt";

    @Test
    public void testDBconnection() {
        DatabaseConnection db = new DatabaseConnection();
        assertNotNull(db.setupConnection());
    }

    @Test
    public void checkServerRunning() {
        DatabaseConnection db = new DatabaseConnection();
        db.startServer();
        // getState() returns 1 if ONLINE, 16 if SHUTDOWN
        assertEquals(1, db.getHsqlServer().getState());

    }

    @Test
    public void checkStopServer() throws InterruptedException {
        DatabaseConnection db = new DatabaseConnection();
        db.startServer();
        db.setupConnection();
        db.stopServer();
        assertNotNull(db.getConnection());
        //16 is shutdown, 8 is stopping. Thread sleep was added because it takes some time for server to stop completely
        Thread.sleep(500);
        assertEquals(16, db.getHsqlServer().getState());
    }

    @Test
    public void testInsertIntoTable() {

        LogReader reader = new LogReader(filePath);
        reader.readFile(new File(filePath));

        DatabaseConnection db = new DatabaseConnection();
        db.setupConnection();
        db.createTable();
        assertTrue(db.save(reader.getFlaggedEvents()));
    }


}
