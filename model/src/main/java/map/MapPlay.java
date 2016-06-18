package map;
import java.io.IOException;



import map.element.Sprite;
import map.element.mobile.Lorann;
import map.element.motionless.IDoActionOnHeroes;



public class MapPlay implements IOrderPerformed{
	private  MapFrame mapFrame;

	

		private MapWorld mapWorld;
	

		public MapPlay(final MapWorld mapWorld) {
			this.mapWorld = mapWorld;
			this.mapWorld.addMobile(new Lorann(), 2, 2);
			
		
		}
		public MapWorld getMapWorld()
		{
			return this.mapWorld;
			
		}
		private MapFrame getMapFrame() {
			return this.mapFrame;
		}
		public void setMapFrame(final MapFrame mapFrame) {
			this.mapFrame = mapFrame;
		}
		private MapWorld getActuelMapWorld() {
			
			return this.getMapWorld();
		}
		
		public void orderPerform(final UserOrder userOrder) throws IOException {
			
				switch (userOrder) {
					case UP:
						this.getActuelMapWorld().getLorann().moveUp();
						this.mapWorld.getLorann().setSprite(new Sprite("☺!","lorann_u.png"));
						
						break;
					case RIGHT:
						this.getActuelMapWorld().getLorann().moveRight();
						this.mapWorld.getLorann().setSprite(new Sprite("☺!","lorann_r.png"));
						
						break;
					case DOWN:
						this.getActuelMapWorld().getLorann().moveDown();
						this.mapWorld.getLorann().setSprite(new Sprite("☺!","lorann_b.png"));
						
						break;
					case LEFT:
						this.getActuelMapWorld().getLorann().moveLeft();
						this.mapWorld.getLorann().setSprite(new Sprite("☺!","lorann_bl.png"));
						
						break;
					case NOP:
						
					default:
						break;
				
			}
				this.getWorldAnswer();
		}
		
		private void getWorldAnswer() throws IOException {
			final IDoActionOnHeroes element = this.getActuelMapWorld().getElements(this.getActuelMapWorld().getLorann().getX(),
					this.getActuelMapWorld().getLorann().getY());

			switch (element.getActionOnHeroes()) {
				case UP:
					MapView.displayMessage("You enter in a New Level.");
					this.resolveUp();
					break;
				/*case ENTER_TOWN:
					NettleView.displayMessage("You enter a town.");
					this.resolveEnterTown();
					break;
				case ENTER_MONASTERY:
					NettleView.displayMessage("You enter a monastery.");
					this.resolveEnterMonastery();
					break;
				case EXIT:
					NettleView.displayMessage("You leave this place.");
					this.exitMetting();
					break;
				case ESCAPE:
					NettleView.displayMessage("You escape this place.");
					this.escapeMetting();
					break;
				case NOP:*/
				
				default:
					break;
			}
		}
		
		private void resolveUp() throws IOException {
			
			this.setMapWorld(new MapWorld("test.txt"));
			
			
			this.resolveWorldAnswer();
		}
/*
		private void resolveEnterTown() throws IOException {
			this.setNettleMeeting(new NettleWorld("town.txt"));
			this.resolveWorldAnswer();
		}

		private void resolveEnterMonastery() throws IOException {
			this.setNettleMeeting(new NettleWorld("monastery.txt"));
			this.resolveWorldAnswer();
		}
*/
		private void resolveWorldAnswer() throws IOException {
			this.getMapWorld().addMobile(new Lorann(), 3, 3);
			this.getMapFrame().setMeeting(this.getMapWorld());
			System.out.println("je suis la ");
			this.getMapFrame();
			
		}
		
		private void setMapWorld(final MapWorld map) {
			this.mapWorld= map;
		}
}



