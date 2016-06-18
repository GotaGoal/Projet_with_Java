package map;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Class DAOHelloWorld.
 *
 * @author Jean-Aymeric Diet
 */
class DAOMap extends DAOEntity<Map> {

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
		line = buffer.readLine();
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

	@Override
	public Map find(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	
}