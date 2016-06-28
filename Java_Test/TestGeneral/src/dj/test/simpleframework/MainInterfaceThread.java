
package dj.test.simpleframework;

/**
 * Main interface thread.
 * <p>
 * Update log: (date / author : comments)
 * <ul>
 * <li>2008-03-26 / Du Jiang : Creation
 * </ul>
 * 
 * @author Du Jiang
 * @version 1.0.0.0
 */
public class MainInterfaceThread implements Runnable
{
	private MainInterface mi = null;

	/**
	 * @param mi
	 *        Main interface.
	 */
	public MainInterfaceThread(MainInterface mi){
		this.mi = mi;
	}

	/**
	 * Run.
	 */
	public void run(){
		mi.fnSetupInterface();
	}
}
