package org.manvik;

import org.manvik.confi.DBConnection;
import org.manvik.dto.BookDAO;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        BookDAO bookDAO = new BookDAO();

//        bookDAO.addBook(1,"Ice breaker","Hanneh Grace","contemporary romance and sports romance and for Adult reader only","Start","3","No comment now");
//        bookDAO.addBook(2,"Yoga the spritual path","Swami Vivekananda","Body, Mind & Spirit","chapter 1 - Completed","5","Best book ever - Starting life of swami ji is just lik me");

//        Read all Row of Table
//        bookDAO.readAllBooks();

//        Update
        bookDAO.updateDAO(1, "Love story and english is on harder to read",3,"No comments for now");

//        Delete Any table using id
//        bookDAO.deleteBook(5);
//        bookDAO.deleteBook(6);
//        bookDAO.deleteBook(3);
//        bookDAO.deleteBook(4);

    }
}
