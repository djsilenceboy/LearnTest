
package dj.test.simpleframework;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

/**
 * Output stream to a TextArea control.
 * <p>
 * Update log: (date / author : comments)
 * <ul>
 * <li>2008-03-24 / Du Jiang : Creation
 * </ul>
 * 
 * @author Du Jiang
 * @version 1.0.0.0
 */
public class TextAreaOutputStream extends OutputStream
{
	private JTextArea textControl = null;
	private StringBuffer szBuffer = null;

	/**
	 * @param textControl
	 *        JTextArea control.
	 */
	public TextAreaOutputStream(JTextArea textControl){
		this.textControl = textControl;
	}

	/**
	 * Writes the specified byte as a character.
	 * 
	 * @param b
	 *        The byte.
	 */
	@Override
	public void write(int b) throws IOException{
		char ch = (char)b;

		if (szBuffer == null) {
			szBuffer = new StringBuffer();
		}

		szBuffer.append(ch);

		if (String.valueOf(ch).equals("\r")) {
			textControl.append(szBuffer.toString());
			// Make sure the new text is visible, even if there was a selection in the text area.
			textControl.setCaretPosition(textControl.getDocument().getLength());

			szBuffer = null;
		}
	}
}
