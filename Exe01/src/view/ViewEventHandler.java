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
	private IView view;

	public ViewEventHandler(IView view, IPresenterView presenter) {
		this.presenter = presenter;
		this.view = view;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_S) {
			presenter.savePressed(this.view);
		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			presenter.fillPressed(this.view);
		} else {
			// do nothing
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		presenter.mouseClickedAt(this.view, e.getX(), e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		presenter.mouseMovedTo(this.view, e.getX(), e.getY());
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
	public void mouseDragged(MouseEvent e) {
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
