
public class TextDisplay {

	public static void showText(String theText) throws InterruptedException {

		char[] chars = theText.toCharArray();

		// Print a char from the array, then sleep for 1/10 second
		for (char c : chars) {
			System.out.print(c);
			Thread.sleep(50);
		}
		System.out.println(" ");
	}

}
