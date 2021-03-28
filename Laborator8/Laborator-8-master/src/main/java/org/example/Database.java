package org.example;

import java.sql.*;
import javax.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
    private static Database single_instance = null;
    AlbumControler albumcontroler;
    ArtistControler artistcontroler;

    //jdbc:mysql://localhost:3306/MusicAlbums
    public Database() throws SQLException {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:oracle:dba_user@//localhost:1521/xe", "dba_user", "sql_password");

            albumcontroler = new AlbumControler(this, con);
            artistcontroler = new ArtistControler(this, con);

            artistcontroler.createArtist("Silviu", "Romania");
            artistcontroler.createArtist("Badea Vasile", "Monaco");
            artistcontroler.createArtist("Gheorghe", "Ungaria");
            artistcontroler.createArtist("Mirela", "Moldova");
            System.out.println(artistcontroler.findByName("Badea Vasile"));
            System.out.println(artistcontroler.countAlbumsArtist(artistcontroler.findByName("Badea Vasile")));

            int albumid = albumcontroler.createAlbum("HIT 2018!!Se invarteste roata", artistcontroler.findByName("Badea Vasile"), 2018);
            albumcontroler.createAlbum("HIT 2019!!Se invarteste piatra", artistcontroler.findByName("Badea Vasile"), 2018);
            albumcontroler.createAlbum("HIT 2020!!Se invarteste hora", artistcontroler.findByName("Badea Vasile"), 2018);
            albumcontroler.createAlbum("HIT 2021!!Nu se mai invarte...", artistcontroler.findByName("Badea Vasile"), 2018);
            System.out.println("nr albume: " + artistcontroler.countAlbumsArtist(artistcontroler.findByName("Badea Vasile")));
            System.out.println(albumcontroler.findByAlbum(albumid));
        } catch (SQLException e) {
            System.err.println("Cannot connect to DB: " + e);
        } finally {
            if (con != null) con.close();
        }
    }
}
