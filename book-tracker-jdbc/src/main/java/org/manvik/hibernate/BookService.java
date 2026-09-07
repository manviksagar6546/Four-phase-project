package org.manvik.hibernate;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class BookService {

    public Book getBookById(int id)
    {
        SessionFactory Factory = HibernateUtil.getSessionFactory();

        try(Session session = Factory.openSession()){
            return session.find(Book.class, id);
        }
    }

    public List<Book> getAllBooks(){
        SessionFactory Factory = HibernateUtil.getSessionFactory();

        try(Session session = Factory.openSession()){
            Query query = session.createQuery("from Book");
            return query.getResultList();
        }
    }


//    Update
    public void updateBookStatus(int id, String newStatus)
    {
        SessionFactory Factory = HibernateUtil.getSessionFactory();
        Transaction tx = null;

        try(Session session = Factory.openSession()) {
            tx = session.beginTransaction();

            Book book = session.find(Book.class, id);

            if (book != null) {
                book.setStatus(newStatus);
                // NOTICE: no explicit "update" call here at all
            } else {
                System.out.println("⚠️ No book found with that id.");
            }

            tx.commit();
        }
        catch (Exception ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
        }
    }

//    Delete
public void deleteBookById(int id) {
    SessionFactory Factory = HibernateUtil.getSessionFactory();
    Transaction tx = null;

    try (Session session = Factory.openSession()) {
        tx = session.beginTransaction();

        Book book = session.find(Book.class, id);

        if (book != null) {
            session.remove(book);
            System.out.println("✅ Book deleted.");
        } else {
            System.out.println("⚠️ No book found with that id.");
        }

        tx.commit();
    } catch (Exception ex) {
        if (tx != null) tx.rollback();
        ex.printStackTrace();
    }
}

}
