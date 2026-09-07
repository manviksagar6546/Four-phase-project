package org.manvik.jdbc.dto;

import org.manvik.jdbc.dto.confi.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAO {

    public void addBook(int id,String title, String auther, String genre, String status, String rating, String notes){

        String sql = "insert into books (id,title, auther, genre, status, rating, notes) values (?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);
            stmt.setString(2, title);
            stmt.setString(3, auther);
            stmt.setString(4, genre);
            stmt.setString(5, status);
            stmt.setString(6, rating);
            stmt.setString(7, notes);

            int rows = stmt.executeUpdate();
            System.out.println( "✅ Inserted " + rows + " row(s).");

        } catch (SQLException e) {
            System.out.println("inserting Failed");
            e.printStackTrace();
        }
    }

    public void readAllBooks(){
        String sql = "select * from books";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while(rs.next()){
                System.out.println(
                rs.getInt("id") + " | " +
                rs.getString("title") + " | " +
                rs.getString("auther") + " | " +
                rs.getString("genre") + " | " +
                rs.getString("status") + " | " +
                rs.getString("rating") + " | " +
                rs.getString("notes")
                );
            }
        } catch (SQLException e) {
            System.out.println("reading Failed");
            e.printStackTrace();
        }
    }

    public void updateDAO(int id, String newStatus, int newRating, String newNotes) {

        String sql = "update books set status = ?, rating = ?, notes = ? where id = ?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setString(1, newStatus);
            stmt.setInt(2, newRating);
            stmt.setString(3, newNotes);
            stmt.setInt(4, id);

            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "✅ Updated." : "⚠️ No book found with that id.");
        }
        catch (SQLException e){
            System.out.println("Updating Failed");
            e.printStackTrace();
        }
    }

    public void deleteBook(int id){

        String sql = "delete from books where id = ?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "✅ Deleted." : "⚠️ No book found with that id.");
        }
        catch (SQLException e){
            System.out.println("deleting Failed");
            e.printStackTrace();
        }
    }

}
