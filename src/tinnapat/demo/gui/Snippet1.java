package tinnapat.demo.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import tinnapat.demo.gui.http.TestServer;

public class Snippet1 {
	private static Text text;

	public static void main(String[] args) throws Exception {

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Snippet 1");

		text = new Text(shell, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.READ_ONLY);
		text.setBounds(10, 10, 500, 300);

		shell.open();

		// Start server
		TestServer testServer = new TestServer();
		testServer.startServer();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		// Stop server
		testServer.stopServer();

		// Dispose view
		display.dispose();
	}
}