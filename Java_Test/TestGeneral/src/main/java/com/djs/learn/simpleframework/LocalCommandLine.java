
package com.djs.learn.simpleframework;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Local command line.
 * <p>
 * Update log: (date / author : comments)
 * <ul>
 * <li>2008-03-20 / Du Jiang : Creation
 * <li>2008-03-25 / Du Jiang : Impl.
 * </ul>
 * 
 * @version 1.0.0.0
 */
public class LocalCommandLine
{
	public String szApplicationName = null;
	public String szVersion = null;
	public String szCopyright = null;
	public String szAuthor = null;

	public Options options = null;
	public Option optHelp = null;
	public Option optQuiet = null;
	public Option optFrame = null;
	public Option optJLog = null;
	public Option optLog = null;
	public Option optCheck = null;
	public Option optProcess = null;
	public CommandLine commandLine = null;

	public String szOptionLine = null;

	/**
	 * @param szApplicationName
	 *        Application name.
	 * @param szVersion
	 *        Version, format as "1.0".
	 * @param szCopyright
	 *        Copyright info.
	 * @param szAuthor
	 *        Author.
	 */
	public LocalCommandLine(String szApplicationName, String szVersion, String szCopyright, String szAuthor){
		this.szApplicationName = szApplicationName;
		this.szVersion = szVersion;
		this.szCopyright = szCopyright;
		this.szAuthor = szAuthor;
	}

	/**
	 * Prepare command line options.
	 */
	public void fnPrepare(){
		options = new Options();

		optHelp = new Option("h", "print this message");
		optQuiet = new Option("q", "quiet");
		optFrame = new Option("f", "show frame interface");
		optJLog = OptionBuilder.withArgName("file").hasArg().withDescription("use given file for java log").create("jl");
		optLog = OptionBuilder.withArgName("file").hasArg().withDescription("use given file for log").create("l");
		optCheck = OptionBuilder.withArgName("config file").hasArg().withDescription("check validation of report entries").create("c");
		optProcess = OptionBuilder.withArgName("config file").hasArg().withDescription("process report entries").create("p");

		options.addOption(optHelp);
		options.addOption(optQuiet);
		options.addOption(optFrame);
		options.addOption(optJLog);
		options.addOption(optLog);
		options.addOption(optCheck);
		options.addOption(optProcess);

		szOptionLine = "[-f | {[-q | -jl <file> | -l <file>] [-h | [-c | -p] <config file> ]}]";
	}

	/**
	 * Show usage.
	 */
	public void fnUsage(){
		HelpFormatter formatter = new HelpFormatter();

		System.out.println("");
		System.out.println("Application: " + szApplicationName);
		System.out.println("Version    : " + szVersion);
		System.out.println("Copyright  : " + szCopyright);
		System.out.println("Author     : " + szAuthor);
		System.out.println("");
		formatter.printHelp(szOptionLine, options);
	}

	/**
	 * Parse command line.
	 * 
	 * @param args
	 *        Command lines.
	 * @return boolean - true = valid.
	 */
	public boolean fnParse(String[] args){
		boolean bRet = false;

		CommandLineParser cliParser = new GnuParser();

		try {
			commandLine = cliParser.parse(options, args);

			bRet = true;
		} catch (ParseException e) {
		}

		return bRet;
	}

	/**
	 * Show parsed options.
	 */
	public void fnParsedOptions(){
		Option[] ayOption = null;
		int i;

		ayOption = commandLine.getOptions();

		if (ayOption == null) {
			System.out.println("No valid option.");
		} else {
			for (i = 0; i < ayOption.length; i++) {
				System.out.println("Option[" + i + "] = " + ayOption[i]);
			}
		}
	}
}
