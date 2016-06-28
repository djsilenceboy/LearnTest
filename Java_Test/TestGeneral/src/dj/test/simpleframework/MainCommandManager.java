
package dj.test.simpleframework;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Main command manager.
 * <p>
 * Update log: (date / author : comments)
 * <ul>
 * <li>2008-03-25 / Du Jiang : Creation
 * <li>2008-03-26 / Du Jiang : Thread
 * </ul>
 * 
 * @author Du Jiang
 * @version 1.0.0.0
 */
public class MainCommandManager extends Thread
{
	private LocalCommandLine lcl = null;

	/**
	 * @param lcl
	 *        LocalCommandLine.
	 */
	public MainCommandManager(LocalCommandLine lcl){
		this.lcl = lcl;
	}

	/**
	 * Do help.
	 */
	public boolean fnDoHelp(){
		lcl.fnUsage();

		return true;
	}

	/**
	 * Do quiet.
	 */
	public boolean fnDoQuiet(){
		System.setOut(null);
		System.setErr(null);

		return true;
	}

	/**
	 * Do log.
	 * <p>
	 * Use log file name from command line.
	 * <p>
	 */
	public boolean fnDoLog(){
		boolean bRet = false;
		String szLogFileName = null;
		FileOutputStream fos = null;
		PrintStream ps = null;

		try {
			szLogFileName = lcl.commandLine.getOptionValue(lcl.optLog.getOpt());

			fos = new FileOutputStream(szLogFileName);
			ps = new PrintStream(fos);

			System.setOut(ps);
			System.setErr(ps);

			//

			bRet = true;
		} catch (Exception e) {
			bRet = false;

			e.printStackTrace();
		}

		return bRet;
	}

	/**
	 * Do java log.
	 * <p>
	 * Use log file name from command line.
	 * <p>
	 */
	public boolean fnDoJLog(){
		boolean bRet = false;
		String szLogFileName = null;
		Logger log = null;
		FileHandler fhr = null;
		PrintStream ps = null;

		try {
			szLogFileName = lcl.commandLine.getOptionValue(lcl.optJLog.getOpt());

			log = Logger.getLogger("");

			fhr = new FileHandler(szLogFileName);
			fhr.setFormatter(new SimpleFormatter());

			log.addHandler(fhr);
			log.setLevel(Level.ALL);

			ps = new PrintStream(new LoggingOutputStream(log));

			System.setOut(ps);
			System.setErr(ps);

			//

			bRet = true;
		} catch (Exception e) {
			bRet = false;

			e.printStackTrace();
		}

		return bRet;
	}

	/**
	 * Do check.
	 * 
	 * @param szConfigFileName
	 *        Config file name.
	 */
	public boolean fnDoCheck(String szConfigFileName){
		boolean bRet = false;

		return bRet;
	}

	/**
	 * Do check.
	 * <p>
	 * Use config file name from command line.
	 * <p>
	 */
	public boolean fnDoCheck(){
		return fnDoCheck(lcl.commandLine.getOptionValue(lcl.optCheck.getOpt()));
	}

	/**
	 * Do process.
	 * 
	 * @param szConfigFileName
	 *        Config file name.
	 */
	public boolean fnDoProcess(String szConfigFileName){
		boolean bRet = false;

		return bRet;
	}

	/**
	 * Do process.
	 * <p>
	 * Use config file name from command line.
	 * <p>
	 */
	public boolean fnDoProcess(){
		return fnDoProcess(lcl.commandLine.getOptionValue(lcl.optProcess.getOpt()));
	}

	////////////////////////////////////////////////////////////
	/*
	 * For thread functions.
	 */

	private MainInterface mi = null;
	public final static int COMMAND_TYPE_NONE = -1;
	public final static int COMMAND_TYPE_CHECK = 0;
	public final static int COMMAND_TYPE_PROCESS = 1;
	private int iCommandType = COMMAND_TYPE_NONE;
	private String szFileName = null;

	/**
	 * Prepare parameters for thread.
	 * 
	 * @param mi
	 *        Main interface handle.
	 * @param iCommandType
	 *        Command type.
	 * @param szFileName
	 *        File name, maybe null.
	 */
	public void fnPrepareThread(MainInterface mi, int iCommandType, String szFileName){
		this.mi = mi;
		this.iCommandType = iCommandType;
		this.szFileName = szFileName;
	}

	/**
	 * Run.
	 */
	@Override
	public void run(){
		mi.fnDoingSomething(true);

		if (iCommandType == COMMAND_TYPE_CHECK) {
			fnDoCheck(szFileName);
		} else if (iCommandType == COMMAND_TYPE_PROCESS) {
			fnDoProcess(szFileName);
		}

		iCommandType = COMMAND_TYPE_NONE;
		szFileName = null;

		mi.fnDoingSomething(false);
	}
}
