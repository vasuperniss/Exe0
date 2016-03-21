package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import presenter.IPresenterView;

/**
 * The Class ViewEventHandler.
 * @author Michael Vassernis 319582888
 * @author 
 */
public class ViewEventHandler implements MouseListener, MouseMotionListener,
		KeyListener {

	/** The presenter. */
	private IPresenterView presenter;
	
	/** The view. */
	private IView view;

	/**
	 * Instantiates a new view event handler.
	 *
	 * @param view the view
	 * @param presenter the presenter
	 */
	public ViewEventHandler(IView view, IPresenterView presenter) {
		this.presenter = presenter;
		this.view = view;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		if (String.valueOf(e.getKeyChar()).equalsIgnoreCase("S")) {
			presenter.savePressed(this.view);
		} else if (String.valueOf(e.getKeyChar()).equalsIgnoreCase("F")) {
			presenter.fillPressed(this.view);
		} else if (String.valueOf(e.getKeyChar()).equalsIgnoreCase("L")) {
			presenter.loadPressed(this.view);
		}else {
			// do nothing
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		presenter.mouseClickedAt(this.view, e.getX(), e.getY());
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		presenter.mouseMovedTo(this.view, e.getX(), e.getY());
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// do nothing
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// do nothing
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		// do nothing
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// do nothing
	}
}
