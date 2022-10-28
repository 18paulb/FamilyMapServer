
package Data;
import com.google.gson.Gson;

import java.io.*;

public class DataChooser {

    private static Gson gson = new Gson();

    public static void main(String args[]) throws IOException {
        chooseFirstName("m");
    }

    public static String chooseFirstName(String gender) throws IOException {

        try {

            FileReader reader;

            if (gender.equals("f")) {
                reader = new FileReader("json" + File.separator + "fnames.json");
            } else {
                reader = new FileReader("json" + File.separator + "mnames.json");
            }

            NameData data = gson.fromJson(reader, NameData.class);

            int randomIndex = (int) Math.floor(Math.random() * data.getData().length);

            String name = data.getData()[randomIndex];

            return name;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String chooseLastName() throws IOException {

        try {

            FileReader reader = new FileReader("json" + File.separator + "snames.json");

            NameData data = gson.fromJson(reader, NameData.class);

            int randomIndex = (int) Math.floor(Math.random() * data.getData().length);

            String name = data.getData()[randomIndex];

            return name;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Location chooseLocation() throws IOException {
        try {

            FileReader reader = new FileReader("json" + File.separator + "locations.json");

            LocationData data = gson.fromJson(reader, LocationData.class);

            int randomIndex = (int) Math.floor(Math.random() * data.getData().length);

            Location loc = data.getData()[randomIndex];

            return loc;

            //Make Name object with String[] data; use GSON to convert JSON to object
            //LocationArr with Location Objects
            //Location object with 4 data members that match

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
