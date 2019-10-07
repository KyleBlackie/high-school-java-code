package edu.hdsb.gwss.blackie.ics4u.u4;

import java.util.ArrayList;

/**
 *
 * @author 1blackiekyl
 */
public class Library {

    //object variables
    private ArrayList<Book> books;

    //constructors
    public Library() {
        books = new ArrayList();
        //trace message to let client know that a Library has been created
        System.out.println("Library Object has been created");
    }

    //getter 
    public ArrayList<Book> getBooks() {
        return books;
    }
    

    //add book 
    public void addBook(Book bk) {
        if (bk == null) {
            System.out.println("Error book is null");
        } else if (!bk.isValid()) {
            System.out.println("Error book is not valid");
        } else if (books.contains(bk) || bookExists(bk.getIsbn())) {
            System.out.println("Error book is already in library");
        }else{
            //set book to this library object (ie. one library has N books)
            bk.setLib(this);
            books.add(bk);
            System.out.println("Book has been successfully added");
        }
    }

    //remove book
    public void removeBook(Book bk) {
        if (bk == null) {
            System.out.println("Error book is null");
        } else if (!books.contains(bk)) {
            System.out.println("Error book is not in library");
        } else {
            books.remove(bk);
            System.out.println("Book has been successfully removed");
        }
    }

    //get book
    public Book getBook(Book bk) {
        if (bk == null) {
            System.out.println("Error book is null");
            return null;
        } else if (!books.contains(bk)) {
            System.out.println("Error book is not in library");
            return null;
        }
        System.out.println("Book has been successfully returned");
        return books.get(books.indexOf(bk));
    }

    //get a book using its id
    public Book getBook(int id) {
        for (Book book : books) {
            if (book.getIsbn() == id) {
                System.out.println("Book has been successfully returned");
                return book;
            }
        }
        System.out.println("Book not found");
        return null;
    }
    
    //check if there is a book in library with the same isbn number
    private boolean bookExists(int isbn){
        for(Book book : books){
            if(book.getIsbn() == isbn){
                return true;
            }
        }
        return false;
    }

}
