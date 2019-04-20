package tests.internal;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Test1 {

   public static void main(String[] args) {
      Display display = new Display();
      Shell shell = new Shell(display);
      shell.open();

      // run the event loop as long as the window is open
      while (!shell.isDisposed()) {
         // read the next OS event queue and transfer it to a SWT event
         if (!display.readAndDispatch())
         {
            // if there are currently no other OS event to process
            // sleep until the next OS event is available
            display.sleep();
         }
      }

      // disposes all associated windows and their components
      display.dispose();}

}
