package map.element.mobile;

import map.MapWorld;
import map.element.Sprite;

public class Monster extends Mobile {
	
	private MapWorld mapWorld;
	private int sensTwo = 0;
	private int sensOne = 0;
	private int idMonster = 0;

	public Monster(Sprite sprite,MapWorld mapWorld,final int idMonster) {
		super(sprite);
		this.mapWorld = mapWorld;
		this.setIdMonster(idMonster);
		// TODO Auto-generated constructor stub
	}
	
	public void deplacement (final int idMonster)
	{
		switch (idMonster)
		{
		case 1:
			int saveX = this.getX();
			System.out.println("louch");
			System.out.print(this.getX());
			switch (this.sensOne)
		    {
		    case 0:
		    	this.moveLeft();
		    	 if (saveX == this.getX() )
		    	 {
		    		 this.sensOne = 1;
		    	 }
		    	 break;
		    case 1 :
		    	 this.moveRight();
		    	 if (saveX ==this.getX() )
		    	 {
		    		 this.sensOne = 0;
		    	 }
		    	 break;
		    default:
		    	break;
		    	
		    }
			break;
		case 2:
			int saveY = this.getY();
			switch (this.sensTwo)
		    {
		    case 0:
		    	
		    	 this.moveUp();
		    	
		    	 if (saveY == this.getY() )
		    	 {
		    		 this.sensTwo = 1;
		    	 }
		    	 break;
		    case 1 :
		    	
		    	 this.moveDown();
		    	 if (saveY ==this.getY() )
		    	 {
		    		 this.sensTwo = 0;
		    	 }
		    	 break;
		    default:
		    	break;
		    	
		    }
			break;
		case 3:
			int Random = (int)( Math.random()*( 5 - 1 + 1 ) ) + 1;
			switch(Random)
			   {
			   case 1:
				   this.moveDown();			   
				   break;
			   case 2:
				   this.moveUp();
				   break;
			   case 3:
				   this.moveLeft();
				   break;   
			   case 4:
				   this.moveRight();
				   break;
				  default:
					  break;
				   
			   }
			break;
		case 4:
			 if (this.getX() > this.mapWorld.getLorann().getX()){
			 		this.moveLeft();

			 	}
			 	if (this.getY() > this.mapWorld.getLorann().getY()){
			 		this.moveUp();

			 	}
			 	if (this.getX() < this.mapWorld.getLorann().getX()){
			 		this.moveRight();
			 	
			 		}
			 		
			 	if (this.getY() < this.mapWorld.getLorann().getY()){
			 		this.moveDown();
			 	
			 	
				}
			break;
		}
	}

	public int getIdMonster() {
		return idMonster;
	}

	public void setIdMonster(int idMonster) {
		this.idMonster = idMonster;
	}
	
}
