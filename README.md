# EventLogger
This program takes JSON file as an input and logs events to database flagging any event that takes more than 4ms 

## Task Description 
Custom-build server logs different events to a file named logfile.txt. Every event has 2 entries in the file - one
entry when the event was started and another when the event was finished. The entries in the file have no specific
order (a finish event could occur before a start event for a given id)
Every line in the file is a JSON object containing the following event data: 
* id - the unique event identifier
* state - whether the event was started or finished (can have values "STARTED" or "FINISHED"
* timestamp - the timestamp of the event in milliseconds
Application Server logs also have the following additional attributes:
* type - type of log
* host - hostname

## Example input file

```
{"id":"scsmbstgra", "state":"STARTED", "type":"APPLICATION_LOG", "host":"12345", "timestamp":1491377495212}
{"id":"scsmbstgrb", "state":"STARTED", "timestamp":1491377495213}
{"id":"scsmbstgrc", "state":"FINISHED", "timestamp":1491377495218}
{"id":"scsmbstgra", "state":"FINISHED", "type":"APPLICATION_LOG", "host":"12345", "timestamp":1491377495217}
{"id":"scsmbstgrc", "state":"STARTED", "timestamp":1491377495210}
{"id":"scsmbstgrb", "state":"FINISHED", "timestamp":1491377495216}
```

Program should:

* Take the path to logfile.txt as an input argument
* Parse the contents of logfile.txt
* Flag any long events that take longer than 4ms • Write the found event details to file-based HSQLDB (http://hsqldb.org/) in the working folder

The application should create a new table if necessary and store the following values:
*  Event id
* Event duration
* Type and Host if applicable
* Alert (true if the event took longer than 4ms, otherwise false)

## Requirments 
- Java 8
- Junit 4
- HSQLDB
- Gradle
- Jackson (JSON processing) 


## How to

1. Download the project from this repo
2. Navigate to project folder and build the project
3. To build the project use command
```
gradle build
```
or
```
./gradlew build
```
4. Run the application using command below. This will use the default logfile.txt stored in resources folder
```
gradle run
```

5. In case of using a custom file provide a absolute path to a file 
```
gradle run --args <path/to/file> 
```

## Output 

You can see the output logs in the console after running the app.  It tells that DB connection was established, whetever the table was created and  prints the the table values

### The sample log output

```
21:05:37.674 [main] INFO com.creditsuisse.pawelrozniecki.db.DatabaseConnection - Connection with DB established
21:05:37.699 [main] INFO com.creditsuisse.pawelrozniecki.db.DatabaseConnection - TABLE events created
21:05:37.702 [main] INFO com.creditsuisse.pawelrozniecki.db.DatabaseConnection - Row inserted
21:05:37.702 [main] INFO com.creditsuisse.pawelrozniecki.db.DatabaseConnection - Row inserted
21:05:37.702 [main] INFO com.creditsuisse.pawelrozniecki.db.DatabaseConnection - Row inserted
21:05:37.702 [main] INFO com.creditsuisse.pawelrozniecki.db.DatabaseConnection - 3 rows inserted
21:05:37.703 [main] DEBUG com.creditsuisse.pawelrozniecki.db.DatabaseConnection - scsmbstgra | 5 | APPLICATION_LOG | 12345 | true
21:05:37.703 [main] DEBUG com.creditsuisse.pawelrozniecki.db.DatabaseConnection - scsmbstgrb | 3 | null | null | false
21:05:37.703 [main] DEBUG com.creditsuisse.pawelrozniecki.db.DatabaseConnection - scsmbstgrc | 8 | null | null | true

```

The database files will be created and stored in db folder found in the root of the project



## Design

### Builder design pattern 
Builder Design pattern was used for creation of AlertEvent objects because it adds design flexibility and results in more readable code.

### Optional vs String 
At first the idea was to use Optional wrapper for host and type values to increase readability of code and enhance null safty but it creates unecessary object allocation and can lead to NullPointerExceptions in some cases. Therefore String value was used, so whetever there's no host or type values provided , null value will be stored in database

### Enums
Enum was used for storing event states

### Database

The program creates HSQLDB table for storing Flagged Events. CREATE TABLE IF NOT EXISTS query was used so that the table can only be created if it doesn't exist yet. Following files will be generated and stored in db folder found in root project: 
- testdb.lck
- testdb.log
- testdb.script
- testdb.properties 


