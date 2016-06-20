package contract;

import java.awt.Point;

import view.MapFrame;

public interface IMapFrame {
	
	void setTextChange();
	void showMessage(final String message);
	void setMeeting(final IMapWorld mapWorld);
	IOrderPerformed getMapPlay();
	void setX(final int x);
	void refresh(final Point center);
	void setBack();
	void repainture(final int i);

}
