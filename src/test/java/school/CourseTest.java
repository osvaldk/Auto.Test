package school;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import Service.PublicHolidayService;

import java.time.ZonedDateTime;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CourseTest {

    ZonedDateTime startDate = ZonedDateTime.parse("2020-01-01T00:00:00.000+00:00[UTC]");
    ZonedDateTime endDate = ZonedDateTime.parse("2020-01-03T00:00:00.000+00:00[UTC]");

    @Mock
    private PublicHolidayService service = new PublicHolidayService();

    @InjectMocks
    private Course course = new Course(startDate, endDate);

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getLength() {
        //given
        ZonedDateTime startDate = ZonedDateTime.parse("2020-01-01T00:00:00.000+00:00[UTC]");
        ZonedDateTime endDate = ZonedDateTime.parse("2020-02-01T00:00:00.000+00:00[UTC]");

        Long expectedResult = 32L;

        Course course = new Course(startDate, endDate);

        //when

        Long result = course.getLength();

        //then
        assertEquals(expectedResult, result);

    }

    @Test
    public void getLengthIfWrongOrder() {
        //given
        ZonedDateTime startDate = ZonedDateTime.parse("2020-02-01T00:00:00.000+00:00[UTC]");
        ZonedDateTime endDate = ZonedDateTime.parse("2020-01-01T00:00:00.000+00:00[UTC]");

        Long expectedResult = -30L;

        Course course = new Course(startDate, endDate);

        //when

        Long result = course.getLength();

        //then
        assertEquals(expectedResult, result);

    }

    @Test(expected = NullPointerException.class)
    public void getLengthIfNoData() {
        Course course = new Course();
        Long result = course.getLength();

    }

    @Test(expected = IllegalArgumentException.class)
    public void getWorkingDaysWhileServiceReturnMinusOne() throws Exception {
//        given
        when(service.getNumberOfPublicHolidaysOnWorkingDays(startDate, endDate)).thenReturn(-1);

//        when
        course.getWorkingDays();

    }

    @Test
    public void getWorkingDaysWhileServiceReturnNormalNumber()throws Exception{
//        given
        when(service.getNumberOfPublicHolidaysOnWorkingDays(startDate, endDate)).thenReturn(1);

//        when
        Integer workingDays = course.getWorkingDays();
        Integer expected = 2;

//        then
        assertEquals(expected, workingDays);

    }

    @Test(expected = NullPointerException.class)
    public void getWorkingDaysWhileServiceReturnNullStatement() throws Exception{
//        given
        when(service.getNumberOfPublicHolidaysOnWorkingDays(startDate, endDate)).thenReturn(null);

//        when
        course.getWorkingDays();

    }

    @Test(expected = IllegalArgumentException.class)
    public void getWorkingDaysWhileServiceReturnZero() throws Exception{
//        given
        when(service.getNumberOfPublicHolidaysOnWorkingDays(startDate, endDate)).thenReturn(0);

//        when
        course.getWorkingDays();

    }

    @Test(expected = IllegalArgumentException.class)
    public void getWorkingDaysWhileServiceReturnException() throws Exception{
//        given
        when(service.getNumberOfPublicHolidaysOnWorkingDays(startDate, endDate)).thenThrow(IllegalArgumentException.class);

//        when
        course.getWorkingDays();

    }


}