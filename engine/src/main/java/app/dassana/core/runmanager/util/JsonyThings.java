package app.dassana.core.runmanager.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.google.gson.Gson;
import io.micronaut.context.annotation.Factory;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

@Factory
public class JsonyThings {

  public static final ObjectMapper MAPPER = new ObjectMapper();

  public static final String MESSAGE = "Sorry, Dassana Engine can only process JSON objects";


  @Singleton
  Gson getGson() {
    return new Gson();
  }

  @Singleton
  public Yaml getYaml() {
    return new Yaml();
  }

  public static void throwExceptionIfNotValidJsonObj(String strToValidate) {

    try {
      JSONObject jsonObject = new JSONObject(strToValidate);
    } catch (JSONException e) {
      throw new IllegalArgumentException(MESSAGE);

    }

  }


  public static String getYamlFromJson(String json) throws JsonProcessingException {
    return new YAMLMapper().writeValueAsString(MAPPER.readTree(json));
  }


}