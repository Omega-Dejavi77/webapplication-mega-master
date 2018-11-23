package cat.tecnocampus.omega.webControllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;


@RestController
@RequestMapping("api/")
public class WeatherTest {

    private String owmApiKey = "742bf5820f2530a3a241e7fde16148fe";
    private String city = "London";
    
    @GetMapping(value = "index", produces = MediaType.APPLICATION_JSON_VALUE)
    public String get() throws IOException, JSONException {
        JSONObject json = readJsonFromURL("http://api.openweathermap.org/data/2.5/weather?q=zuerich&mode=json&units=metric&cnt=7&appid=");
        return null;
    }

    private JSONObject readJsonFromURL(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
        }
        finally {
            is.close();
        }
        return null;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
