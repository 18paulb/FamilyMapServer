package Data;

import DataAccess.DataAccessException;
import Model.Event;
import Model.Person;
import Model.User;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Generation {

    //TODO FOR TESTING
    private List<Person> persons = new ArrayList<>();
    private List<Event> events = new ArrayList<>();


    public static void main(String args[]) throws SQLException, IOException, DataAccessException {
        Generation test = new Generation();

        User tmpUser = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1234");

        test.generateFamily(2, tmpUser, tmpUser.getGender());

        int tee = 4;
    }


    public Person generateFamily(int generations, User user, String gender) throws SQLException, DataAccessException, IOException {
        Person mother = new Person();
        Person father = new Person();

        if (generations > 1) {
            mother = generateFamily(generations - 1, user, "f");
            father = generateFamily(generations - 1, user, "m");

            //Set mother's and father's spouse IDs
            mother.setSpouseID(father.getPersonID());
            father.setSpouseID(mother.getPersonID());

            //Add marriage events to mother and father (make sure to match)
            //For Mother
            Event marriage = new Event();
            marriage.generateRandomEventID();
            marriage.setAssociatedUsername(user.getUsername());
            marriage.setPersonID(mother.getPersonID());
            Location loc = DataChooser.chooseLocation();
            marriage.setLatitude(loc.getLatitude());
            marriage.setLongitude(loc.getLongitude());
            marriage.setCountry(loc.getCountry());
            marriage.setCity(loc.getCity());
            marriage.setEventType("Marriage");
            marriage.setYear(1999);

            events.add(marriage);

            //Generates new ID for the spouse Event
            //For Father
            marriage.generateRandomEventID();
            events.add(marriage);
        }

        Person person = new Person();
        //Set properties
        person.generateRandomPersonID();
        person.setAssociatedUsername(user.getUsername());
        person.setFirstName(DataChooser.chooseFirstName(gender));
        person.setLastName(DataChooser.chooseLastName());
        person.setGender(gender);
        person.setMotherID(mother.getPersonID());
        person.setFatherID(father.getPersonID());
        //No need to set spouse
        persons.add(person);

        //Generate events for person (except marriage)
        Event event = new Event();
        events.add(event);







        //Save person in database
        /*
        try {
            this.createPerson(person);
        } catch (DataAccessException e) {
            System.out.println(e);
            e.printStackTrace();
        */

        return person;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public List<Event> getEvents() {
        return events;
    }
}
