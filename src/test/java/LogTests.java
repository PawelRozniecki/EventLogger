import com.creditsuisse.pawelrozniecki.LogReader;
import com.creditsuisse.pawelrozniecki.Runner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;


public class LogTests {

    private static final String filePath = "../CreditSuisseAssignment/src/test/resources/logfile.txt";
    private static final String emptyFile = "../CreditSuisseAssignment/src/test/resources/emptyfile.txt";
    private static final String missingFieldsPath = "../CreditSuisseAssignment/src/test/resources/missingFields.txt";


    // empty file was removed from github repo because github doesn't allow for empty files
    @Test
    public void testEmptyFile() throws Exception {
        LogReader reader = new LogReader(emptyFile);
        reader.setPath(emptyFile);
        reader.readFile(new File(emptyFile));
        assertTrue(reader.getFlaggedEvents().isEmpty());

    }

    @Test
    public void testFlagEvents() {

        LogReader reader = new LogReader(filePath);
        reader.setPath(filePath);
        reader.readFile(new File(filePath));
        //checking the number of events passed to the file
        assertEquals(3, reader.getFlaggedEvents().size());
        // checking if ID's are parsed correctly in order
        assertEquals("scsmbstgra", reader.getFlaggedEvents().get(0).getId());
        assertEquals("scsmbstgrb", reader.getFlaggedEvents().get(1).getId());
        assertEquals("scsmbstgrc", reader.getFlaggedEvents().get(2).getId());

        // Events without host or type should have null value
        assertNull(reader.getFlaggedEvents().get(1).getHost());
        assertNull(reader.getFlaggedEvents().get(1).getType());

        // Events with host or type cannot be null
        assertEquals("APPLICATION_LOG", reader.getFlaggedEvents().get(0).getType());
        assertEquals("12345", reader.getFlaggedEvents().get(0).getHost());

        assertTrue(reader.getFlaggedEvents().get(0).isAlert());
        assertFalse(reader.getFlaggedEvents().get(1).isAlert());
    }

}
