package tinnapat.demo.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import tinnapat.demo.gui.http.DemoServer;

public class Snippet1 {

	private static Text text;

	public static void main(String[] args) throws Exception {

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("SWT DEMO APPLICATION WITH HTTP ENDPOINT");

		text = new Text(shell, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.READ_ONLY);
		text.setBounds(10, 10, 600, 300);

		shell.open();

		// Start server
		DemoServer demoServer = new DemoServer();
		demoServer.startServer();

		// Thread to update the text box in the background
		Thread updateThread = new Thread() {
			public void run() {
				while (true) {
					if (!CommandQueue.isEmpty()) {
						Display.getDefault().syncExec(new Runnable() {
							public void run() {
								while (!CommandQueue.isEmpty()) {
									text.append(CommandQueue.poll() + "\n");
								}
							}
						});
					}

					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		// Start the background thread
		updateThread.setDaemon(true);
		updateThread.start();

		// SWT event loop
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		// Stop server
		demoServer.stopServer();

		// Dispose view
		display.dispose();
	}
}