package org.example;

import java.sql.*;

public class AlbumControler {
    Database database;
    Connection con;

    public AlbumControler(Database database, Connection con) {
        this.database = database;
        this.con = con;
    }


    private int countAlbums() throws SQLException {
        Statement stmt3 = con.createStatement();
        int count = 0;
        ResultSet rs3 = stmt3.executeQuery("SELECT COUNT id FROM albums AS count");
        while (rs3.next()) {
            count = rs3.getInt("count");
        }
        return count;
    }

    public String findByAlbum(int albumId) throws SQLException {
        Statement stmt3 = con.createStatement();
        String artist;
        ResultSet rs3 = stmt3.executeQuery("SELECT name FROM artists a join albums b where a.id=b.artist_id");
        artist = rs3.getString("name");
        return artist;
    }//returneaza artist name


    public int createAlbum(String albumName, int artistId, int releaseYear) throws SQLException {
        int count1 = 0;
        int count2 = 0;
        Statement stmt3 = con.createStatement();


        ResultSet rs1 = stmt3.executeQuery("SELECT COUNT(*) FROM albums a join artists b on a.artist_id=b.id AS count");
        while (rs1.next()) {
            count1 = rs1.getInt("count");
        }

        ResultSet rs2 = stmt3.executeQuery("SELECT COUNT(*) FROM albums a join artists b on a.artist_id=b.id AS count");
        while (rs2.next()) {
            count1 = rs2.getInt("count");
        }

        if (count1 != 0 && count2 != 0)
            return 0;


        ResultSet rs3 = stmt3.executeQuery("insert into albums values(" + (countAlbums() + 1) + ",'" + albumName + "'," + artistId + "," + releaseYear + ")");
        return countAlbums();
    }
}
