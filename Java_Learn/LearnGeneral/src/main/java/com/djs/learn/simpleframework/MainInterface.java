
package com.djs.learn.simpleframework;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Main interface (frame).
 * <p>
 * Update log: (date / author : comments)
 * <ul>
 * <li>2008-03-24 / Du Jiang : Creation
 * <li>2008-03-25 - 2008-03-26 / Du Jiang : Impl.
 * </ul>
 * 
 * @version 1.0.0.0
 */
public class MainInterface
{
	private MainInterface mi = null;
	private JFrame mainFrame = null;
	private LocalCommandLine lcl = null;

	// Config file name for test case.
	private JLabel labelConfigFileName_TC = null;
	private JTextField textConfigFileName_TC = null;
	private JPanel panelConfig_TC = null;
	private JButton buttonOpenFile_TC = null;

	private JButton buttonCheck = null;
	private JButton buttonProcess = null;
	private JPanel panelCommandGroup1 = null;

	private JButton buttonClean = null;
	private JButton buttonHelp = null;
	private JButton buttonQuit = null;
	private JPanel panelCommandGroup2 = null;

	private JTextArea textLog = null;
	private JScrollPane panelLog = null;

	private JFileChooser fileChooser = null;

	/**
	 * @param lcl
	 *        LocalCommandLine
	 */
	public MainInterface(LocalCommandLine lcl){
		this.lcl = lcl;
		this.mi = this;
	}

	/**
	 * Setup frame.
	 */
	private void fnSetupFrame(){
		mainFrame = new JFrame(lcl.szApplicationName + "  v" + lcl.szVersion + "  " + lcl.szCopyright);
		Container container = mainFrame.getContentPane();

		// mainFrame.setSize( 300, 150 );
		mainFrame.setVisible(true);

		// container.setLayout( new FlowLayout() );
		// container.setLayout( new BorderLayout( 10, 7 ) );
		// container.setLayout( new BorderLayout() );
		// container.setLayout( new GridLayout( 0, 1 ) );
		container.setLayout(new GridBagLayout());

		WindowListener l = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		};

		mainFrame.addWindowListener(l);
	}

	/**
	 * Setup frame content.
	 */
	private void fnSetupFrameContent(){
		Container container = mainFrame.getContentPane();
		GridBagConstraints constraints = new GridBagConstraints();
		JButton buttonDummy = new JButton("123456789012");

		//

		labelConfigFileName_TC = new JLabel("Config file name (test case):");
		textConfigFileName_TC = new JTextField(30);
		panelConfig_TC = new JPanel();
		buttonOpenFile_TC = new JButton("Open", new ImageIcon("../image/open_file.gif"));
		buttonOpenFile_TC.setToolTipText("Open a config file of test cases");

		buttonCheck = new JButton("Check", new ImageIcon("../image/check.png"));
		buttonCheck.setToolTipText("Check validation of report entries");
		buttonProcess = new JButton("Process", new ImageIcon("../image/process.gif"));
		buttonProcess.setToolTipText("Process report entries");
		panelCommandGroup1 = new JPanel();

		buttonClean = new JButton("Clean", new ImageIcon("../image/clean.gif"));
		buttonClean.setToolTipText("Clean log window");
		buttonHelp = new JButton("Help", new ImageIcon("../image/help.gif"));
		buttonHelp.setToolTipText("Show help");
		buttonQuit = new JButton("Quit", new ImageIcon("../image/quit.gif"));
		buttonQuit.setToolTipText("Quit applicaiton");
		panelCommandGroup2 = new JPanel();

		textLog = new JTextArea(25, 100);
		panelLog = new JScrollPane(textLog);

		//

		labelConfigFileName_TC.setLabelFor(textConfigFileName_TC);

		panelConfig_TC.setLayout(new BoxLayout(panelConfig_TC, BoxLayout.X_AXIS));
		panelConfig_TC.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		panelConfig_TC.add(labelConfigFileName_TC);
		panelConfig_TC.add(Box.createRigidArea(new Dimension(15, 0)));
		panelConfig_TC.add(textConfigFileName_TC);
		panelConfig_TC.add(Box.createRigidArea(new Dimension(15, 0)));
		panelConfig_TC.add(buttonOpenFile_TC);

		//

		panelCommandGroup1.setLayout(new BoxLayout(panelCommandGroup1, BoxLayout.X_AXIS));
		panelCommandGroup1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		buttonCheck.setPreferredSize(buttonDummy.getPreferredSize());
		buttonProcess.setPreferredSize(buttonDummy.getPreferredSize());

		panelCommandGroup1.add(Box.createHorizontalGlue());
		panelCommandGroup1.add(buttonCheck);
		panelCommandGroup1.add(Box.createHorizontalGlue());
		panelCommandGroup1.add(buttonProcess);
		panelCommandGroup1.add(Box.createHorizontalGlue());

		//

		panelCommandGroup2.setLayout(new BoxLayout(panelCommandGroup2, BoxLayout.X_AXIS));
		panelCommandGroup2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		buttonClean.setPreferredSize(buttonDummy.getPreferredSize());
		buttonHelp.setPreferredSize(buttonDummy.getPreferredSize());
		buttonQuit.setPreferredSize(buttonDummy.getPreferredSize());

		panelCommandGroup2.add(Box.createHorizontalGlue());
		panelCommandGroup2.add(buttonClean);
		panelCommandGroup2.add(Box.createHorizontalGlue());
		panelCommandGroup2.add(buttonHelp);
		panelCommandGroup2.add(Box.createHorizontalGlue());
		panelCommandGroup2.add(buttonQuit);
		panelCommandGroup2.add(Box.createHorizontalGlue());

		//

		textLog.setFont(new Font("Courier New", Font.PLAIN, 12));
		textLog.setEditable(false);

		//

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridy = 0;
		container.add(panelConfig_TC, constraints);

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridy++;
		container.add(panelCommandGroup1, constraints);

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridy++;
		container.add(panelCommandGroup2, constraints);

		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridy++;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		container.add(panelLog, constraints);

		mainFrame.pack();
	}

	/**
	 * Setup output stream.
	 */
	private void fnSetupOutputStream(){
		PrintStream outNew = new PrintStream(new TextAreaOutputStream(textLog));

		System.setOut(outNew);
		System.setErr(outNew);
	}

	/**
	 * Setup actions (event).
	 */
	private void fnSetupActions(){
		fileChooser = new JFileChooser();

		fileChooser.setCurrentDirectory(new File("."));

		buttonOpenFile_TC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				int iRet = fileChooser.showOpenDialog(mainFrame);

				if (iRet == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();

					textConfigFileName_TC.setText(file.getPath());
				}
			}
		});

		buttonClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				textLog.setText("");
			}
		});

		buttonHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				lcl.fnUsage();
			}
		});

		buttonQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				System.out.println("Quit...");

				System.exit(0);
			}
		});

		buttonCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				MainCommandManager mcm = null;

				mcm = new MainCommandManager(lcl);
				mcm.fnPrepareThread(mi, MainCommandManager.COMMAND_TYPE_CHECK, textConfigFileName_TC.getText());
				mcm.start();
			}
		});

		buttonProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				MainCommandManager mcm = null;

				mcm = new MainCommandManager(lcl);
				mcm.fnPrepareThread(mi, MainCommandManager.COMMAND_TYPE_PROCESS, textConfigFileName_TC.getText());
				mcm.start();
			}
		});
	}

	/**
	 * Setup main interface.
	 */
	public void fnSetupInterface(){
		fnSetupFrame();
		fnSetupFrameContent();
		fnSetupOutputStream();
		fnSetupActions();
	}

	/**
	 * Set if doing something.
	 * 
	 * @param bDoing
	 *        true = doing something now, enable/disable buttons.
	 */
	public void fnDoingSomething(boolean bDoing){
		buttonCheck.setEnabled(!bDoing);
		buttonProcess.setEnabled(!bDoing);
		buttonHelp.setEnabled(!bDoing);
	}
}
