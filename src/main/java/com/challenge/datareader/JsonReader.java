package com.challenge.datareader;

import com.challenge.utils.logs.LogsManager;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonReader {
    private final String TEST_DATA_PATH = "src/test/resources/Test_Data/";
    String jsonReader;
    String jsonFileName;
    public JsonReader(String jsonFileName) {
        this.jsonFileName = jsonFileName;
        try {
            JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(TEST_DATA_PATH + jsonFileName + ".json"));
            jsonReader = data.toJSONString();
        } catch (Exception e) {
            LogsManager.error("Error reading json file:", jsonFileName, e.getMessage());
            jsonReader = "{}";
        }
    }
    public String getJsonData(String jsonPath) {
        try {
            return JsonPath.read(jsonReader, jsonPath);
        } catch (Exception e) {
            LogsManager.error("Error reading json file for path:", jsonPath, e.getMessage());
            return "";
        }
    }
}
