package tech.kbtg.demo.gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import tech.kbtg.demo.gui.http.TestServer;

public class Snippet1 {

    public static void main (String [] args) throws Exception {

    	TestServer testServer = new TestServer();
    	testServer.startServer();
    	
        Display display = new Display ();
        Shell shell = new Shell(display);
        shell.setText("Snippet 1");
        shell.open ();
        while (!shell.isDisposed ()) {
            if (!display.readAndDispatch ()) display.sleep ();
        }
        display.dispose ();
        testServer.stopServer();
    }
}