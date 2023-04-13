package ui.gui.listeners;

import model.logging.Event;
import model.logging.EventLog;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class OnCloseListener implements WindowListener {
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        EventLog events = EventLog.getInstance();
        for (Event v : events) {
            String eventString = String.format("[[ %s ]] -- %s", v.getDate().toString(), v.getDescription());
            System.out.println(eventString);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
