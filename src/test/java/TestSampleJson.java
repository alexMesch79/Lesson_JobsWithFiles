import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

public class TestSampleJson {

    ClassLoader classLoader = TestSampleJson.class.getClassLoader();

    @Test
    void jsonTest() {

        InputStream inputStream = classLoader.getResourceAsStream("Learner.json");
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(new InputStreamReader(inputStream), JsonObject.class);
        assertThat(jsonObject.get("name").getAsString()).isEqualTo("Alex");
        assertThat(jsonObject.get("secondName").getAsString()).isEqualTo("Meshcherjakov");
        assertThat(jsonObject.get("age").getAsInt()).isEqualTo(40);
        assertThat(jsonObject.get("passport").getAsJsonObject().get("series").getAsInt()).isEqualTo(5602);
        assertThat(jsonObject.get("passport").getAsJsonObject().get("number").getAsInt()).isEqualTo(415200);
    }

}
