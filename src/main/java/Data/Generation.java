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

    private List<Person> persons = new ArrayList<>();
    private List<Event> events = new ArrayList<>();

    public static void main(String args[]) throws SQLException, IOException, DataAccessException {
        Generation test = new Generation();

        User tmpUser = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1234");


        int numGenerations = 4;
        Person userPerson = new Person(tmpUser.getPersonID(), tmpUser.getUsername(), tmpUser.getFirstName(), tmpUser.getLastName(), tmpUser.getGender());

        int birthYear = 2000;
        //Generate Birth Event - User
        Location userLoc1 = DataChooser.chooseLocation();
        Event userBirth = Event.createEvent("Birth", tmpUser.getUsername(), userPerson, userLoc1, birthYear);
        test.getEvents().add(userBirth);

        if (numGenerations > 0) {
            String fatherID = Person.makePersonID();
            String motherID = Person.makePersonID();

            userPerson.setFatherID(fatherID);
            userPerson.setMotherID(motherID);
            //Parents are 40 Years Older, They got married 20 years before birth, they died at 80
            test.generateFamily(numGenerations, 0, fatherID, motherID, tmpUser, birthYear - 40, birthYear - 20, birthYear + 40);
        }

        test.getPersons().add(userPerson);
    }

    /* Params currGen
    Root

    if (currGen <= geenraions)
        mother = new Person()
        father = newPersn()
        gendrateFamily(mother)
        generateFamily(Father)

     else
        create Person
     */

    public void generateFamily(int numGenerations, User user) throws IOException, SQLException, DataAccessException {

        Person userPerson = new Person(user.getPersonID(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getGender());

        int birthYear = 2000;
        //Generate Birth Event - User
        Location userLoc1 = DataChooser.chooseLocation();
        Event userBirth = Event.createEvent("Birth", user.getUsername(), userPerson, userLoc1, birthYear);
        events.add(userBirth);

        try {
            if (numGenerations > 0) {
                String fatherID = Person.makePersonID();
                String motherID = Person.makePersonID();

                userPerson.setFatherID(fatherID);
                userPerson.setMotherID(motherID);
                //Parents are 40 Years Older, They got married 20 years before birth, they died at 80
                this.generateFamily(numGenerations, 0, fatherID, motherID, user, birthYear - 40, birthYear - 20, birthYear + 40);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        persons.add(userPerson);
    }

    //Maybe add currPerson parameter
    public void generateFamily(int generations, int currGen, String personID1, String personID2, User user, int birthYear, int marriageYear, int deathYear) throws SQLException, DataAccessException, IOException {

        if (generations == 0) {
            return;
        }
        if (currGen == generations) {
            return;
        }

        currGen++;

        String maleFatherID = null;
        String maleMotherID = null;
        String femaleFatherID = null;
        String femaleMotherID = null;


        Person father = new Person();
        father.setPersonID(personID1);
        father.setAssociatedUsername(user.getUsername());
        father.setFirstName(DataChooser.chooseFirstName("m"));
        father.setLastName(DataChooser.chooseLastName());
        father.setGender("m");

        if (currGen < generations) {
            maleFatherID = Person.makePersonID();
            maleMotherID = Person.makePersonID();
        }
        father.setFatherID(maleFatherID);
        father.setMotherID(maleMotherID);

        /////////////DO SAME FOR THE MOTHER//////////////

        Person mother = new Person();
        mother.setPersonID(personID2);
        mother.setAssociatedUsername(user.getUsername());
        mother.setFirstName(DataChooser.chooseFirstName("f"));
        mother.setLastName(DataChooser.chooseLastName());
        mother.setGender("f");

        if (currGen < generations) {
            femaleFatherID = Person.makePersonID();
            femaleMotherID = Person.makePersonID();
        }

        mother.setFatherID(femaleFatherID);
        mother.setMotherID(femaleMotherID);

        mother.setSpouseID(father.getPersonID());
        father.setSpouseID(mother.getPersonID());

        persons.add(mother);
        persons.add(father);

        ///////Generating Events////////////

        //Generate Birth Event - Father
        Location fatherLoc1 = DataChooser.chooseLocation();
        Event fatherBirth = Event.createEvent("Birth", user.getUsername(), father, fatherLoc1, birthYear);
        events.add(fatherBirth);

        //Generate Death Event - Father
        Location fatherLoc2 = DataChooser.chooseLocation();
        Event fatherDeath = Event.createEvent("Death", user.getUsername(), father, fatherLoc2, deathYear);
        events.add(fatherDeath);

        //Generate Birth Event - Mother
        Location motherLoc1 = DataChooser.chooseLocation();
        Event motherBirth = Event.createEvent("Birth", user.getUsername(), mother, motherLoc1, birthYear);
        events.add(motherBirth);

        //Generate Death Event - Mother
        Location motherLoc2 = DataChooser.chooseLocation();
        Event motherDeath = Event.createEvent("Death", user.getUsername(), mother, motherLoc2, deathYear);
        events.add(motherDeath);

        //Generate Marriage Event
        Location marriageLoc = DataChooser.chooseLocation();
        Event fatherMarriage = Event.createEvent("Marriage", user.getUsername(), father, marriageLoc, marriageYear);
        Event motherMarriage = Event.createEvent("Marriage", user.getUsername(), mother, marriageLoc, marriageYear);
        events.add(fatherMarriage);
        events.add(motherMarriage);

        //Recursive call for any future parents
        if (currGen < generations) {
            //Parents are 40 Years Older, They got married 20 years before birth, they died at 80
            generateFamily(generations, currGen, maleFatherID, maleMotherID, user, birthYear - 40, birthYear - 20, birthYear + 40);
            generateFamily(generations, currGen, femaleFatherID, femaleMotherID, user, birthYear - 40, birthYear - 20, birthYear + 40);

        }

    }


    /*
    public Person generateFamily(int generations, User user, String gender, int birthYear, int marriageYear, int deathYear) throws SQLException, DataAccessException, IOException {
        Person mother = null;
        Person father = null;

        //If generations == 1 it will just generate person, if > it will generate person and parents
        if (generations > 0) {
            mother = generateFamily(generations - 1, user, "f", birthYear - 20, (birthYear - 20) + 15, birthYear + 40);
            father = generateFamily(generations - 1, user, "m", birthYear - 20, (birthYear - 20) + 15, birthYear + 40);

            //Set mother's and father's spouse IDs
            mother.setSpouseID(father.getPersonID());

            father.setSpouseID(mother.getPersonID());

            //Add marriage events to mother and father (make sure to match)
            //For Mother
            Location loc = DataChooser.chooseLocation();
            Event marriage1 = Event.createEvent("Marriage", user.getUsername(), mother, loc, marriageYear);

            events.add(marriage1);

            //Generates new ID for the spouse Event
            //For Father
            Event marriage2 = Event.createEvent("Marriage", user.getUsername(), father, loc, marriageYear);
            events.add(marriage2);
        }

        //This will just generate Person
        Person person = new Person();
        //Set properties
        person.generateRandomPersonID();
        person.setAssociatedUsername(user.getUsername());

        person.setFirstName(DataChooser.chooseFirstName(gender));
        person.setLastName(DataChooser.chooseLastName());
        person.setGender(gender);

        //Could break here
        if (mother != null && father != null) {
            person.setMotherID(mother.getPersonID());
            person.setFatherID(father.getPersonID());
            person.setLastName(father.getLastName());
        }

        //No need to set spouse
        persons.add(person);

        //Generate Birth Event
        Location loc1 = DataChooser.chooseLocation();
        Event birth = Event.createEvent("Birth", user.getUsername(), person, loc1, birthYear);
        events.add(birth);

        //Generate Death Event
        Location loc2 = DataChooser.chooseLocation();
        Event death = Event.createEvent("Death", user.getUsername(), person, loc2, deathYear);
        events.add(death);

        return person;
    }

     */

    public List<Person> getPersons() {
        return persons;
    }

    public List<Event> getEvents() {
        return events;
    }
}
