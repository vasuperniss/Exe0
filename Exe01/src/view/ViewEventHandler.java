package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import presenter.IPresenterView;

public class ViewEventHandler implements MouseListener, MouseMotionListener,
		KeyListener {

	private IPresenterView presenter;

	public ViewEventHandler(IPresenterView presenter) {
		this.presenter = presenter;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_S) {
			presenter.savePressed();
		} else if (e.getKeyCode() == KeyEvent.VK_P) {
			
		} else {
			// do nothing
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		presenter.mouseClickedAt(e.getX(), e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		presenter.mouseMovedTo(e.getX(), e.getY());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// do nothing
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// do nothing
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// do nothing
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// do nothing
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// do nothing
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// do nothing
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// do nothing
	}
}
