package serial;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONVesta {
    public static void main(String[] args) {
        JSONObject jsonDashboard = new JSONObject("{\"buttons\":\"14\", \"analogDisplay\":\"true\"}");

        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonColors = new JSONArray(list);

        final CarModel car = new CarModel(true, 4, "Lada", new Dashboard(17, false), new String[] {"white", "blue"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isLeftSteeringWheel", car.isLeftSteeringWheel());
        jsonObject.put("wheels", car.getWheels());
        jsonObject.put("name", car.getName());
        jsonObject.put("dashboard", jsonDashboard);
        jsonObject.put("colors", jsonColors);

        System.out.println(jsonObject);

        System.out.println(new JSONObject(car));

    }
}
