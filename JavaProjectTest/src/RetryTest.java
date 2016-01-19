
public class RetryTest {
		public static void main(String args[]) {
			int i = 0;
			retry:   // 1
				if(i==5){
					return;
				}
			for( i = 0;i < 10;i++) {
				{
					if(i==5){
//						continue retry;
					}
				}
					System.out.print(i + " ");
			}
		}

}
