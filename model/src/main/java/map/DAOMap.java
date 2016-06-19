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
class DAOMap extends DAOEntity<Map> {
	private MapWorld mapWorld;

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
	@Override
	public boolean create(final Map entity) {
		// Not implemented
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see model.DAOEntity#delete(model.Entity)
	 */
	@Override
	public boolean delete(final Map entity) {
		// Not implemented
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see model.DAOEntity#update(model.Entity)
	 */
	@Override
	public boolean update(final Map entity) {
		// Not implemented
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see model.DAOEntity#find(int)
	 */
	@Override
	public Map find(final int id) throws IOException {
		Map MapWorld = new Map();

		try {
			final String sql = "{call helloworldById(?)}";
			final CallableStatement call = this.getConnection().prepareCall(sql);
			call.setInt(1, id);
			call.execute();
			final ResultSet resultSet = call.getResultSet();
			if (resultSet.first()) {
				MapWorld = new Map(/*id, resultSet.getString("key"), resultSet.getString("message")*/);
			}
			return MapWorld;
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
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
<<<<<<< HEAD
			if(resultSet.first()){;
				valeur_x = resultSet.getInt(2);
			}
		
		}	 
=======
			mapWorld.elements = new MotionlessElement[resultSet.getInt(x)][resultSet.getInt(y)];
			for (int valeur_x = 0; valeur_x < resultSet.getInt(x); valeur_x++) {
				for (int valeur_y = 0; valeur_y < resultSet.getInt(y); valeur_y++) {
					final String SQL = "{call get_element(?, ?, ?)}";
					final CallableStatement CALL = this.getConnection().prepareCall(SQL);
					CALL.setInt(1, valeur_x);
					CALL.setInt(2, valeur_y);
					CALL.setInt(3, mapID);
					CALL.execute();
					final ResultSet result = CALL.getResultSet();
					
					switch(result.getInt(id_element)){
						case 1 :
							//mapWorld.addElement(MotionlessElements./getFromFileSymbol==>getFromNomBDD, x, y);
					}
				}
			} 

		} 
>>>>>>> origin/benjamin
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

	@Override
	public Map find(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	
}