package br.com.arjconsultoria.component;

import java.util.Map;

//import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;

public abstract class MySelectorComposer<T> extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	public static int OVERLAPPED = 1;
	public static int MODAL = 2;
//	protected Logger log = Logger.getLogger(this.getClass());

	@Wire("#win")
	private Window myWin;

	@WireVariable
	private Desktop desktop;

	protected transient T self;

	public void openWindow(String component, String title, Map<?,?> args) {
		this.openWindow(component, title, args, OVERLAPPED);
	}

	public void openWindowPosi(String component, String title, Map<?,?> args,
			String pos) {
		this.openWindowPosi(component, title, args, OVERLAPPED, pos);
	}

	public void openWindowPosi(String component, String title, Map<?,?> args,
			int type, String pos) {
		Window win1 = null;

		try {
			win1 = (Window) desktop.getExecution().createComponents(component,
					null, args);

			Component c = Path.getComponent("/win/" + win1.getId());
			if (c != null) {
				c.detach();
			}

			win1.setTitle(title);
			win1.setClosable(true);
			win1.setMaximizable(false);
			if (OVERLAPPED == type) {
				win1.doOverlapped();
			} else {
				win1.doHighlighted();
			}

			win1.setPosition(pos);
			win1.setPage(desktop.getPage("mainWin"));
			win1.setParent(desktop.getPage("mainWin").getFellow("win"));
		} catch (Exception e) {
//			log.error(e);
			if (win1 != null) {
				win1.detach();
			}
		}
	}

	public void openWindow(String component, String title, Map<?,?> args, int type) {
		Window win1 = null;

		try {
			win1 = (Window) desktop.getExecution().createComponents(component,
					null, args);

			Component c = Path.getComponent("/win/" + win1.getId());
			if (c != null) {
				c.detach();
			}

			win1.setTitle(title);
			win1.setClosable(true);
			win1.setMaximizable(false);
			if (OVERLAPPED == type) {
				win1.doOverlapped();
			} else {
				win1.doHighlighted();
			}

			win1.setPosition("center");
		} catch (Exception e) {
//			log.error(e);
			e.printStackTrace();
			if (win1 != null) {
				win1.detach();
			}
		}
	}

	public void openWindow(String component, String title, Map<?,?> args, int type,
			Boolean closabled) {
		Window win1 = null;

		try {
			win1 = (Window) desktop.getExecution().createComponents(component,
					null, args);

			Component c = Path.getComponent("/win/" + win1.getId());
			if (c != null) {
				c.detach();
			}

			win1.setTitle(title);
			win1.setClosable(closabled);
			win1.setMaximizable(false);
			if (OVERLAPPED == type) {
				win1.doOverlapped();
			} else {
				win1.doHighlighted();
			}

			win1.setPosition("center");
			// win1.setPage(desktop.getPage("mainWin"));
			// win1.setParent(desktop.getPage("mainWin").getFellow("win"));
		} catch (Exception e) {
//			log.error(e);
			if (win1 != null) {
				win1.detach();
			}
		}
	}

	public void closeWindow() {
		if (this.self instanceof Window) {
			((Window) this.self).detach();
		}
	}
	
	protected Window getMyWin() {
		return myWin;
	}
	
	protected Desktop getDesktop() {
		return desktop;
	}

}
