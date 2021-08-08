package multilocksusingsynchronizedcodeblock;

// 153 1, 5, 3 153==sum
	//2 ussu 3 ==> power Math.pow(2,3) double 2*2*2
public class App {

	public static void main(String[] args) {
	
		System.out.println(isArmstrong(153));
		
	}
public static boolean isArmstrong(int number) {
	int sum=0;
	int temp=number;//153,153
	
	while(number>0) {
		int firstDigit= number%10;//3
		number=number/10;//15
		sum=(int) (sum+Math.pow(firstDigit, 3));
	}
	System.out.println(sum);
	System.out.println(number);
	System.out.println(temp);
	return sum==temp;//0
	
}
}
