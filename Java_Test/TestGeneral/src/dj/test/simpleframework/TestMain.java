
package dj.test.simpleframework;

public class TestMain
{
	/**
	 * @param args
	 */
	public static void main(String[] args){
		boolean bRet = false;
		LocalCommandLine lcl = null;
		MainInterface mi = null;
		MainCommandManager mcm = null;

		try {
			lcl = new LocalCommandLine((new TestMain()).getClass().getName(), "1.0", "(C) 2008, SingTel Mobile", "Consumer Mobile / SND / ITD : Du Jiang");
			lcl.fnPrepare();

			bRet = lcl.fnParse(args);
			if (!bRet || (lcl.commandLine.getOptions().length == 0)) {
				lcl.fnUsage();

				return;
			}

			if (lcl.commandLine.hasOption(lcl.optFrame.getOpt())) {
				mi = new MainInterface(lcl);

				MainInterfaceThread mit = new MainInterfaceThread(mi);

				javax.swing.SwingUtilities.invokeLater(mit);
			} else {
				mcm = new MainCommandManager(lcl);

				if (lcl.commandLine.hasOption(lcl.optQuiet.getOpt())) {
					bRet = mcm.fnDoQuiet();
					if (!bRet) {
						System.err.println("Error: fnDoQuiet() failed.");
						throw new Exception();
					}
				} else if (lcl.commandLine.hasOption(lcl.optJLog.getOpt())) {
					bRet = mcm.fnDoJLog();
					if (!bRet) {
						System.err.println("Error: fnDoJLog() failed.");
						throw new Exception();
					}
				} else if (lcl.commandLine.hasOption(lcl.optLog.getOpt())) {
					bRet = mcm.fnDoLog();
					if (!bRet) {
						System.err.println("Error: fnDoLog() failed.");
						throw new Exception();
					}
				}

				if (lcl.commandLine.hasOption(lcl.optHelp.getOpt())) {
					mcm.fnDoHelp();

					return;
				} else {
					lcl.fnParsedOptions();
				}

				if (lcl.commandLine.hasOption(lcl.optCheck.getOpt())) {
					bRet = mcm.fnDoCheck();
					if (!bRet) {
						System.err.println("Error: fnDoCheck() failed.");
						throw new Exception();
					}
				} else if (lcl.commandLine.hasOption(lcl.optProcess.getOpt())) {
					bRet = mcm.fnDoProcess();
					if (!bRet) {
						System.err.println("Error: fnDoProcess() failed.");
						throw new Exception();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
