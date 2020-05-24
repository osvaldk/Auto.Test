package Service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class  PublicHolidayService {

    private static final String COUNTRY_CODE = "EE";
    private static final String API_URL = "https://date.nager.at/api/v2/PublicHolidays";

    private String baseUrl;

    public PublicHolidayService() {
        this.baseUrl = API_URL;
    }

    public PublicHolidayService(String url) {
        this.baseUrl = url;
    }

    public Integer getNumberOfPublicHolidaysOnWorkingDays(ZonedDateTime startDate, ZonedDateTime endDate) throws IOException, JSONException{
        return getNumberOfPublicHolidaysOnWorkingDays(startDate, endDate, baseUrl);
    }

    public Integer getNumberOfPublicHolidaysOnWorkingDays(ZonedDateTime startDate, ZonedDateTime endDate, String apiUrl)throws IOException, JSONException {
        List<ZonedDateTime> result = new ArrayList<>();
        String composedUrl = apiUrl + "/" + String.valueOf(endDate.getYear()) + "/" + COUNTRY_CODE;


        URL url = new URL(composedUrl);
        URLConnection yc = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;
        String dateString;

        while ((inputLine = in.readLine()) != null) {
            JSONArray arr = new JSONArray(inputLine);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject jsonObject = arr.getJSONObject(i);
                dateString = jsonObject.getString("date");
                LocalDate date = LocalDate.parse(dateString);
                ZonedDateTime zonedResult = date.atStartOfDay(ZoneId.of("UTC"));
                result.add(zonedResult);
            }

        }
        in.close();

        Integer holidayCount = 0;


        for (int i = 0; i < result.size(); i++) {
            ZonedDateTime zonedDateTime = result.get(i);
            if (zonedDateTime.isAfter(startDate) || zonedDateTime.isEqual(startDate) && zonedDateTime.isBefore(endDate)) {
                if (zonedDateTime.getDayOfWeek().getValue() != 6 && zonedDateTime.getDayOfWeek().getValue() != 7) {
                    holidayCount++;
                }
            }
        }

        return holidayCount;


    }

}