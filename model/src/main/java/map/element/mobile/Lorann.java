package map.element.mobile;


import map.element.Sprite;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Lorann extends Mobile   {
	private Image image;
	
	public Lorann() {
		super(new Sprite("☺!","lorann.png"));
	}
	
	public void setContextuelImage(final int pos) throws IOException
	{
		switch (pos)
		{
		 case 1:
			 /*
			 this.setImage("land.png");
			 this.getMapWorld().setMobileHasChanged();
			 */
		}
	}
/*
	@Override
	public void setImage(String image) throws IOException {
		this.image = ImageIO.read(new File("images/" + image));
		
	}
	*/

	
	
}
