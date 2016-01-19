import java.util.ArrayList;
import java.util.List;


public class SubListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		System.out.println("before size: " + list.size());
		List<String> temp = list.subList(0, 2);
		temp.clear();
		System.out.println("after size: " + list.size());
		
		
		Integer firstinmoney = new Integer(8);
		System.out.println(firstinmoney>=8);
	}
	

}
