package Service;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.json.JSONException;
import org.junit.Rule;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.time.ZonedDateTime;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;

public class PublicHolidayServiceTest {
    ZonedDateTime startDate = ZonedDateTime.parse("2020-01-01T00:00:00.000+00:00[UTC]");
    ZonedDateTime endDate = ZonedDateTime.parse("2020-01-03T00:00:00.000+00:00[UTC]");



    @Rule
    public WireMockRule wireMockRule = new WireMockRule(80);

    PublicHolidayService service = new PublicHolidayService("http://localhost");


    @Test(expected = JSONException.class)
    public void throws_exception_on_invalid_JSON() throws Exception {
        stubFor(any(anyUrl())
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>Some content</response>")));


        //when
        Integer result = service.getNumberOfPublicHolidaysOnWorkingDays(startDate, endDate);

        verify(getRequestedFor(urlEqualTo("/2020/EE")));


    }

    @Test
    public void processes_api_normal_response() throws Exception {
        stubFor(any(anyUrl())
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("[{\"date\":\"2020-01-01\",\"localName\":\"uusaasta\",\"name\":\"New Year's Day\",\"countryCode\":\"EE\",\"fixed\":true,\"global\":true,\"counties\":null,\"launchYear\":null,\"type\":\"Public\"}]")));


        //when
        Integer result = service.getNumberOfPublicHolidaysOnWorkingDays(startDate, endDate);
        Integer expected = 1;

        //then
        assertEquals(expected, result);

        verify(getRequestedFor(urlEqualTo("/2020/EE")));
    }

    @Test(expected = JSONException.class)
    public void processes_api_response_without_date_field() throws Exception {
        stubFor(any(anyUrl())
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("[{\"localName\":\"uusaasta\",\"name\":\"New Year's Day\",\"countryCode\":\"EE\",\"fixed\":true,\"global\":true,\"counties\":null,\"launchYear\":null,\"type\":\"Public\"}]")));


        //when
        Integer result = service.getNumberOfPublicHolidaysOnWorkingDays(startDate, endDate);

        verify(getRequestedFor(urlEqualTo("/2020/EE")));
    }

    @Test
    public void processes_api_response_with_blank_array() throws Exception {
        stubFor(any(anyUrl())
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("[]")));


        //when
        Integer result = service.getNumberOfPublicHolidaysOnWorkingDays(startDate, endDate);
        Integer expected = 0;

        //then
        assertEquals(expected, result);

        verify(getRequestedFor(urlEqualTo("/2020/EE")));
    }

    @Test(expected = JSONException.class)
    public void processes_api_response_with_blank_JSON_object() throws Exception {
        stubFor(any(anyUrl())
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("[{}]")));



        //when
        Integer result = service.getNumberOfPublicHolidaysOnWorkingDays(startDate, endDate);
//        Integer expected = 0;
//
//        //then
//        assertEquals(expected, result);

        verify(getRequestedFor(urlEqualTo("/2020/EE")));
    }

    @Test(expected = FileNotFoundException.class)
    public void processes_api_response_with_bad_response_status() throws Exception {
        stubFor(any(anyUrl())
                .willReturn(aResponse()
                        .withStatus(404)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("[{\"date\":\"2020-01-01\",\"localName\":\"uusaasta\",\"name\":\"New Year's Day\",\"countryCode\":\"EE\",\"fixed\":true,\"global\":true,\"counties\":null,\"launchYear\":null,\"type\":\"Public\"}]")));




        //when
        Integer result = service.getNumberOfPublicHolidaysOnWorkingDays(startDate, endDate);

        verify(getRequestedFor(urlEqualTo("/2020/EE")));
    }



}