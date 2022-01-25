package serial;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Vesta {
    public static void main(String[] args) {
        final CarModel model = new CarModel(
                true, 4, "Lada", new Dashboard(17, false), new String[] {"white", "blue"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(model));

        final String modelJson =
                "{"
                        + "\"isLeftSteeringWheel\":false,"
                        + "\"wheels\":4,"
                        + "\"name\":\"NIVA\","
                        + "\"dashboard\":"
                        + "{"
                        + "\"buttons\":2,"
                        + "\"analogDisplay\":true"
                        + "},"
                        + "\"colors\":"
                        + "[\"Black\",\"RED\"]"
                        + "}";
        final CarModel carModelFromJSON = gson.fromJson(modelJson, CarModel.class);
        System.out.println(carModelFromJSON);
    }
}
