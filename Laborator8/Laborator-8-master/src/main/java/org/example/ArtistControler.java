package org.example;

import java.sql.*;

public class ArtistControler {
    Database database;
    Connection con;

    public ArtistControler(Database database, Connection con) {
        this.database = database;
        this.con = con;
    }

    private int countArtists() throws SQLException {
        Statement stmt3 = con.createStatement();

        int count = 0;
        ResultSet rs3 = stmt3.executeQuery("SELECT COUNT id FROM artists AS count");
        while (rs3.next()) {
            count = rs3.getInt("count");
        }
        return count;
    }


    public void createArtist(String name, String Country) throws SQLException {
        Statement stmt3 = con.createStatement();
        ResultSet rs3 = stmt3.executeQuery("insert into artists values(" + (countArtists() + 1) + "," + name + "," + Country + ")");
    }


    public int findByName(String Name) throws SQLException {
        Statement stmt3 = con.createStatement();
        int id = 0;
        ResultSet rs3 = stmt3.executeQuery("SELECT od FROM artists where name='" + Name + "'");
        id = rs3.getInt("id");
        return id;
    }//returneaza Id


    public int countAlbumsArtist(int artistId) throws SQLException {
        Statement stmt3 = con.createStatement();

        int count = 0;
        ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) FROM albums a join artists b on a.artist_id=b.id AS count");
        while (rs3.next()) {
            count = rs3.getInt("count");
        }
        return count;
    }
}
