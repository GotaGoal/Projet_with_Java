package map;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import map.element.motionless.MotionlessElement;
import map.element.motionless.MotionlessElements;

/**
 * The Class DAOHelloWorld.
 *
 * @author Jean-Aymeric Diet
 */
class DAOMap extends DAOEntity {

	/**
	 * Instantiates a new DAO hello world.
	 *
	 * @param connection
	 *          the connection
	 * @throws SQLException
	 *           the SQL exception
	 */
	public DAOMap(final Connection connection) throws SQLException {
		super(connection);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see model.DAOEntity#create(model.Entity)
	 */
	

	/*
	 * (non-Javadoc)
	 *
	 * @see model.DAOEntity#delete(model.Entity)
	 */
	

	/*
	 * (non-Javadoc)
	 *
	 * @see model.DAOEntity#update(model.Entity)
	 */
	
	
	public void loadBDD(final String fileName, final int mapID) throws IOException{
		final BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		String line;
		int numLine = 0;
		line = buffer.readLine();
		int valeur_x = Integer.parseInt(line);
		line = buffer.readLine();
		int valeur_y = Integer.parseInt(line);
		final String SQL = "{call taille_map(?, ?, ?,)}";
		try {
		final CallableStatement CALL = this.getConnection().prepareCall(SQL);
		CALL.setInt(1, valeur_x);
		CALL.setInt(2, valeur_y);
		CALL.setInt(3, mapID);
		CALL.execute();
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		
		while ((line = buffer.readLine()) != null) {
			for (int x = 0; x < line.toCharArray().length; x++) {
				final String sql = "{call ajouter_element(?, ?, ?, ?)}";
				try {
					final CallableStatement call = this.getConnection().prepareCall(sql);
					call.setInt(1, x);
					call.setInt(2, numLine);
					switch (line.toCharArray()[x]){
					case 'V' :
						call.setInt(3, 1);
						break;
					case 'H' :
						call.setInt(3, 2);
						break;
					case 'B' :
						call.setInt(3, 4);
						break;
					default :
						call.setInt(3, 3);
						break;
					}
				
					call.setInt(4, mapID);
					call.execute();
					
					} 
				catch (final SQLException e) {
						e.printStackTrace();
				}
			}
			numLine++;
			//System.out.print(line);
		}
		buffer.close();
		
		
	}
	
	public int getTailleMapX(final int mapID) throws IOException {
		final String sql = "{call get_taille_map(?)}";
		int valeur_x = 0;
		try {
			final CallableStatement call = this.getConnection().prepareCall(sql);
			call.setInt(1, mapID);
			call.execute();
			final ResultSet resultSet = call.getResultSet();
			if(resultSet.first()){;
				valeur_x = resultSet.getInt(2);
			}	

		} 
		catch (final SQLException e) {
			e.printStackTrace();
		}
		return valeur_x;		
	}
	
	public int getTailleMapY(final int mapID) throws IOException {
		final String sql = "{call get_taille_map(?)}";
		int valeur_y = 0;
		try {
			final CallableStatement call = this.getConnection().prepareCall(sql);
			call.setInt(1, mapID);
			call.execute();
			final ResultSet resultSet = call.getResultSet();
			if(resultSet.first()){;
				valeur_y = resultSet.getInt(3);
			}
		
		}	 
		catch (final SQLException e) {
			e.printStackTrace();
		}
		return valeur_y;		
	}
	
	public int getElementBDD(final int mapID, final int x, final int y) throws IOException {
		final String sql = "{call get_element(?, ?, ?)}";
		int valeur_id = 0;
		try {
			final CallableStatement call = this.getConnection().prepareCall(sql);
			call.setInt(1, x);
			call.setInt(2, y);
			call.setInt(3, mapID);
			call.execute();
			final ResultSet resultSet = call.getResultSet();
			if(resultSet.first()){;
				valeur_id = resultSet.getInt(1);
			}
		
		}	 
		catch (final SQLException e) {
			e.printStackTrace();
		}
		return valeur_id;		
	}

	

	public int getEnergyX(final int mapID) throws IOException {
		final String sql = "{call get_objet(?, ?)}";
		int valeur_x = 0;
		try {
			final CallableStatement call = this.getConnection().prepareCall(sql);
			call.setInt(1, mapID);
			call.setInt(2, 1);
			call.execute();
			final ResultSet resultSet = call.getResultSet();
			if(resultSet.first()){;
				valeur_x = resultSet.getInt(2);
			}	

		} 
		catch (final SQLException e) {
			e.printStackTrace();
		}
		return valeur_x;		
	}
	
	public int getEnergyY(final int mapID) throws IOException {
		final String sql = "{call get_objet(?, ?)}";
		int valeur_y = 0;
		try {
			final CallableStatement call = this.getConnection().prepareCall(sql);
			call.setInt(1, mapID);
			call.setInt(2, 1);
			call.execute();
			final ResultSet resultSet = call.getResultSet();
			if(resultSet.first()){;
				valeur_y = resultSet.getInt(3);
			}	

		} 
		catch (final SQLException e) {
			e.printStackTrace();
		}
		return valeur_y;		
	}
	
	public int getDoorOpenX(final int mapID) throws IOException {
		final String sql = "{call get_objet(?, ?)}";
		int valeur_x = 0;
		try {
			final CallableStatement call = this.getConnection().prepareCall(sql);
			call.setInt(1, mapID);
			call.setInt(2, 3);
			call.execute();
			final ResultSet resultSet = call.getResultSet();
			if(resultSet.first()){;
				valeur_x = resultSet.getInt(2);
			}	

		} 
		catch (final SQLException e) {
			e.printStackTrace();
		}
		return valeur_x;		
	}
	
	public int getDoorOpenY(final int mapID) throws IOException {
		final String sql = "{call get_objet(?, ?)}";
		int valeur_y = 0;
		try {
			final CallableStatement call = this.getConnection().prepareCall(sql);
			call.setInt(1, mapID);
			call.setInt(2, 3);
			call.execute();
			final ResultSet resultSet = call.getResultSet();
			if(resultSet.first()){;
				valeur_y = resultSet.getInt(3);
			}	

		} 
		catch (final SQLException e) {
			e.printStackTrace();
		}
		return valeur_y;		
	}
	
	public int getDoorCloseX(final int mapID) throws IOException {
		final String sql = "{call get_objet(?, ?)}";
		int valeur_x = 0;
		try {
			final CallableStatement call = this.getConnection().prepareCall(sql);
			call.setInt(1, mapID);
			call.setInt(2, 4);
			call.execute();
			final ResultSet resultSet = call.getResultSet();
			if(resultSet.first()){;
				valeur_x = resultSet.getInt(2);
			}	

		} 
		catch (final SQLException e) {
			e.printStackTrace();
		}
		return valeur_x;		
	}
	
	public int getDoorCloseY(final int mapID) throws IOException {
		final String sql = "{call get_objet(?, ?)}";
		int valeur_y = 0;
		try {
			final CallableStatement call = this.getConnection().prepareCall(sql);
			call.setInt(1, mapID);
			call.setInt(2, 4);
			call.execute();
			final ResultSet resultSet = call.getResultSet();
			if(resultSet.first()){;
				valeur_y = resultSet.getInt(3);
			}	

		} 
		catch (final SQLException e) {
			e.printStackTrace();
		}
		return valeur_y;		
	}
	
	public int getTreasureX(final int mapID) throws IOException {
		final String sql = "{call get_objet(?, ?)}";
		int valeur_x = 0;
		try {
			final CallableStatement call = this.getConnection().prepareCall(sql);
			call.setInt(1, mapID);
			call.setInt(2, 2);
			call.execute();
			final ResultSet resultSet = call.getResultSet();
			if(resultSet.first()){;
				valeur_x = resultSet.getInt(2);
			}	

		} 
		catch (final SQLException e) {
			e.printStackTrace();
		}
		return valeur_x;		
	}
	
	public int getTreasureY(final int mapID) throws IOException {
		final String sql = "{call get_objet(?, ?)}";
		int valeur_y = 0;
		try {
			final CallableStatement call = this.getConnection().prepareCall(sql);
			call.setInt(1, mapID);
			call.setInt(2, 2);
			call.execute();
			final ResultSet resultSet = call.getResultSet();
			if(resultSet.first()){;
				valeur_y = resultSet.getInt(3);
			}	

		} 
		catch (final SQLException e) {
			e.printStackTrace();
		}
		return valeur_y;		
	}
	
	public int getLorannX(final int mapID) throws IOException {
		final String sql = "{call get_Lorann(?)}";
		int valeur_x = 0;
		try {
			final CallableStatement call = this.getConnection().prepareCall(sql);
			call.setInt(1, mapID);
			call.execute();
			final ResultSet resultSet = call.getResultSet();
			if(resultSet.first()){;
				valeur_x = resultSet.getInt(1);
			}	

		} 
		catch (final SQLException e) {
			e.printStackTrace();
		}
		return valeur_x;		
	}
	
	public int getLorannY(final int mapID) throws IOException {
		final String sql = "{call get_Lorann(?)}";
		int valeur_y = 0;
		try {
			final CallableStatement call = this.getConnection().prepareCall(sql);
			call.setInt(1, mapID);
			call.execute();
			final ResultSet resultSet = call.getResultSet();
			if(resultSet.first()){;
				valeur_y = resultSet.getInt(2);
			}	

		} 
		catch (final SQLException e) {
			e.printStackTrace();
		}
		return valeur_y;		
	}
	
	public int getMonstreX(final int mapID, final int monstreID) throws IOException {
		final String sql = "{call getPositionMonstre(?, ?)}";
		int valeur_x = 0;
		try {
		final CallableStatement call = this.getConnection().prepareCall(sql);
		call.setInt(1, mapID);
		call.setInt(2, monstreID);
		call.execute();
		final ResultSet resultSet = call.getResultSet();
		if(resultSet.first()){;
		valeur_x = resultSet.getInt(1);
		}

		} 
		catch (final SQLException e) {
		e.printStackTrace();
		}
		return valeur_x;
		}
		public int getMonstreY(final int mapID, final int monstreID) throws IOException {
		final String sql = "{call getPositionMonstre(?, ?)}";
		int valeur_y = 0;
		try {
		final CallableStatement call = this.getConnection().prepareCall(sql);
		call.setInt(1, mapID);
		call.setInt(2, monstreID);
		call.execute();
		final ResultSet resultSet = call.getResultSet();
		if(resultSet.first()){;
		valeur_y = resultSet.getInt(2);
		}

		} 
		catch (final SQLException e) {
		e.printStackTrace();
		}
		return valeur_y;
		}

}