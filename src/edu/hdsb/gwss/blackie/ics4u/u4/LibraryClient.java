/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hdsb.gwss.blackie.ics4u.u4;



/**
 *
 * @author kyle
 */
public class LibraryClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //----------------------------------------------------------------------
        // TEST #1: Empty Constructor
        // PRE-CONDTION:
        //      - none
        // POST-CONDTION:
        // dop equals zero
        // id and genre equal to negative one
        // author and library are null
        System.out.println("--------------------------------------");
        System.out.println("Test #1 - Create Book Using Empty Constructor");
        Book b = new Book();
        assert (b.getDop() == 0);
        assert (b.getGenreID() == 0);
        assert (b.getAuthor() == null);
        assert (b.getLib() == null);
        System.out.println("SUCCESS");

        //----------------------------------------------------------------------
        // TEST #2: isValid()
        // PRE-CONDTION:
        //      - an invalid object
        // POST-CONDTION:
        //      - isValid() returns false
        System.out.println("\n--------------------------------------");
        System.out.println("Test #2 -  Check Invalid Book isValid() == false");
        assert (b.isValid() == false);
        System.out.println("B is valid: " + b.isValid());
        System.out.println("SUCCESS");

        //----------------------------------------------------------------------
        //TEST #3: creating a library object with empty constructor
        // PRE-CONDTION:
        //      - a library object
        // POST-CONDTION:
        //      - array list size will be zero 
        System.out.println("\n--------------------------------------");
        System.out.println("Test #3 -  Create Library With Empty Constructor ");
        Library lib = new Library();
        assert (lib.getBooks().size() == 0);
        System.out.println("SUCCESS");

        //----------------------------------------------------------------------
        // TEST #4: add(..) - try to add invalid Book object to library
        // PRE-CONDTION:
        //      - an invalid Book Object
        //      - a library object
        // POST-CONDTION:
        //      - Book will not be added becuase it is invalid
        //      - array list size will still be zero
        System.out.println("\n--------------------------------------");
        System.out.println("Test #4 - add(..) Try To Add Invalid Book to Library");
        assert (lib.getBooks().size() == 0);
        //try to add book that was invalid from before
        lib.addBook(b);
        assert (lib.getBooks().size() == 0);
        System.out.println("SUCCESS");

        //----------------------------------------------------------------------
        // TEST #5: add(..) - try to add a valid Book object to library
        // PRE-CONDTION:
        //      - an valid Book Object
        //      - a library object
        // POST-CONDTION:
        //      - Book will be added
        //      - array list size will increase to one
        System.out.println("\n--------------------------------------");
        System.out.println("Test #5 - add(..) Try To Add Valid Book to Library");
        //create a valid book and check that it is valid
        Book book = new Book(123456, 2001, Book.ADVENTURE, true, "Life of Pi", "Yann Martel");
        assert (book.isValid());
        assert (lib.getBooks().size() == 0);
        lib.addBook(book);
        assert (lib.getBooks().size() == 1);
        System.out.println("SUCCESS");

        //----------------------------------------------------------------------
        // TEST #6: add(..) - try to add a valid Book object to library that already exists
        // PRE-CONDTION:
        //      - an valid Book Object
        //      - a library object with an array list that already contains the book object
        // POST-CONDTION:
        //      - Book will be added
        //      - array list size will increase to one
        System.out.println("\n--------------------------------------");
        System.out.println("Test #6 - add(..) Try To Add Valid Book to Library That Already Exists In Library");
        //create a valid book and check that it is valid
        Book book2 = book;
        assert (book2.isValid());
        lib.addBook(book2);
        assert (lib.getBooks().size() == 1);
        System.out.println("SUCCESS");

        //----------------------------------------------------------------------
        // TEST #7 get(..) - try to get a valid Book object from the library
        // PRE-CONDTION:
        //      - a library object
        //      - a valid Book Object that is in the library object
        // POST-CONDTION:
        //      - Book will be returned
        System.out.println("\n--------------------------------------");
        System.out.println("Test #7 - get(..) Try To Get Valid Book from Library");
        assert (lib.getBook(book) == book);
        System.out.println("SUCCESS");

        //----------------------------------------------------------------------
        // TEST #8 get(..) - try to get a valid Book object from the library using its isbn number
        // PRE-CONDTION:
        //      - a library object
        //      - a valid Book Object that is in the library object with a valid isbn number
        // POST-CONDTION:
        //      - Book will be returned
        System.out.println("\n--------------------------------------");
        System.out.println("Test #8 - get(..) Try To Get Valid Book from Library With Its ISBN Number");
        assert (lib.getBook(123456) == book);
        System.out.println("SUCCESS");

        //----------------------------------------------------------------------
        // TEST #9 get(..) - try to get an invalid Book object from the library
        // PRE-CONDTION:
        //      - a library object with array list of Book objects
        // POST-CONDTION:
        //      - null will be returned and an error message will show
        System.out.println("\n--------------------------------------");
        System.out.println("Test #9 - get(..) Try To Get Invalid Book from Library");
        assert (lib.getBook(null) == null);
        System.out.println("SUCCESS");

        //----------------------------------------------------------------------
        // TEST #10 remove(..) - try to remove a valid Book object from library
        // PRE-CONDTION:
        //      - a library object with array list of Book objects
        //      - a Book object that is contained in the array list of Book objects
        // POST-CONDTION:
        //      - the book object will no longer be in the array
        System.out.println("\n--------------------------------------");
        System.out.println("Test #10 - remove(..) Try To Remove Valid Book from Library");
        assert (lib.getBooks().contains(book));
        System.out.println("Array List Length Before Removal Attempt: " + lib.getBooks().size());
        lib.removeBook(book);
        assert (lib.getBooks().contains(book) == false);
        System.out.println("Array List Length After Removal Attempt: " + lib.getBooks().size());
        System.out.println("SUCCESS");

        //----------------------------------------------------------------------
        // TEST #11 remove(..) - try to remove a Book object that does not exist in library from the library
        // PRE-CONDTION:
        //      - a library object with array list of Book objects
        //      - a Book object that is not contained in the array list of Book objects
        // POST-CONDTION:
        //      - an error message will return and the array list will remain unchanged
        System.out.println("\n--------------------------------------");
        System.out.println("Test #11 - remove(..) Try To Remove Book That Is Not In The Library");
        assert (lib.getBooks().contains(book) == false);
        lib.removeBook(book);
        System.out.println("SUCCESS");
    }

}
