package org.manvik;

import org.manvik.hibernate.Book;
import org.manvik.hibernate.BookService;

import java.util.List;

public class App {
    public static void main(String[] args) {


//        ADD DATA INTO DATABSE -------------------------------------
//        SessionFactory factory = HibernateUtil.getSessionFactory();
//        Session session = factory.openSession();
//
//        Transaction tx = null;
//
//        try {
//            tx = session.beginTransaction();
//
////            Book book = new Book("Deep Work", "Cal Newport", "Productivity", "WISHLIST", 0, "Recommended by a friend");
//            Book book = new Book("Deep Work", "Cal Newport", "Productivity", "WISHLIST", 3, "Recommended by a friend");
//            session.persist(book);
//
//            tx.commit();
//            System.out.println("✅ Book saved via Hibernate!");
//
//        } catch (Exception e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//            factory.close();
//        }

//        READ DATA FROM DATABASE ------------------------------------
        BookService service = new BookService();
//        ---------------------------Fatch using ID

//        System.out.println("-----Fetch by ID-----");
//        Book book = service.getBookById( 1 );
//        if( book != null )
//        {
//            System.out.println( "Book ID: " + book.getAuthor() + "by" +  book.getTitle() );
//        }
//        else
//        {
//            System.out.println( "Book ID not found" );
//        }

//      -----------------------------Fetch all tables
//        System.out.println("-----Fetch ALL DATA-----");
//        List<Book> allBooks = service.getAllBooks();
//
//        System.out.println("Total books found: " + allBooks.size());
//
//        for (Book book : allBooks) {
//            System.out.println(book);
//        }

//        Update data -----------------
//        System.out.println("-----update data------");
//        service.updateBookStatus(1,"Completed");
//
//        System.out.println("--- Verify update ---");
//        Book updated = service.getBookById(1);
//        System.out.println(updated);


//        Delete call
        System.out.println("--- Delete ---");
        service.deleteBookById(2);

        System.out.println("--- Verify deletion (fetch all) ---");
        List<Book> allBooks = service.getAllBooks();

        System.out.println("Total books found: " + allBooks.size());

        for (Book book : allBooks) {
            System.out.println(book);
        }

    }
}