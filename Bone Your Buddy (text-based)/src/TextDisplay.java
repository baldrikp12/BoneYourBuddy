
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

	public static void thinkingText() throws InterruptedException {

		int i = 0;
		while (i < 5) {
			try {
				System.out.print(".");
				Thread.currentThread().sleep(100);
				System.out.print("\r .");
				Thread.currentThread().sleep(100);
				System.out.print("\r  .");
				Thread.currentThread().sleep(100);
				System.out.print("\r   .");
				Thread.currentThread().sleep(100);
				System.out.print("\r    .");
				System.out.print("\r");
			} catch (Exception ex) {
			}
			i++;
		}
	}

}
