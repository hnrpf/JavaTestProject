import java.text.SimpleDateFormat;
import java.util.Calendar;


public class CalendarCrossYearTest {
	
	public static void main(String args[]){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		System.out.println(format.format(c.getTime()));
		
		c.add(Calendar.DAY_OF_YEAR, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		System.out.println(format.format(c.getTime()));
	}

}
