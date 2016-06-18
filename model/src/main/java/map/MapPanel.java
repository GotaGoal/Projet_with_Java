package map;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import aedt.showboard.IPawn;
import aedt.showboard.ISquare;
import map.element.Element;
import map.element.interactions.Interactions;
import map.element.mobile.Mobile;

public class MapPanel extends aedt.showboard.BoardPanel {
	private static final long serialVersionUID = 2361367180781892671L;
	

	public MapPanel(final Dimension dimension, final ISquare[][] squares, final ArrayList<? extends IPawn> pawns, final Point center, final int zoom) {
		super(dimension, squares, pawns, center, zoom);
		
	}

	

}