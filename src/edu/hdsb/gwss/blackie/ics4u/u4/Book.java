package edu.hdsb.gwss.blackie.ics4u.u4;

import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author 1blackiekyl
 */
public class Book {

    //class variables
    private static final String[] GENRES = { "UNKNOWN","COMEDY", "ADVENTURE", "ROMANCE", "DRAMA", "HORROR", "HISTORY"};

    public static final int UNKNOWN = 0;
    public static final int COMEDY = 1;
    public static final int ADVENTURE = 2;
    public static final int ROMANCE = 3;
    public static final int DRAMA = 4;
    public static final int HORROR = 5;
    public static final int HISTORY = 6;

    public static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);

    private static int idGenerator = 0;
    
    //object variables
    private int isbn;
    private int dop;
    private int genreID;
    private boolean bestSeller;
    private String title;
    private String author;
    private Library lib;

    //empty constructor
    public Book() {
        //create isbn number if book does not have one
        isbn = ++idGenerator;
        //trace message to let client know that a Book has been created
        System.out.println("Book object has been created.");
    }

    //primary
    public Book(int isbn) {
        this();
        this.isbn = isbn;
    }

    //secondary (if other less necessary data is to be initialised as well)
    public Book(int isbn, int dop, int genre, boolean bestSeller, String title, String author) {
        this(isbn);
        this.dop = dop;
        this.genreID = genre;
        this.bestSeller = bestSeller;
        this.title = title;
        this.author = author;
    }

    //getters
    public Library getLib() {
        return lib;
    }

    public int getIsbn() {
        return isbn;
    }

    public int getDop() {
        return dop;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBestSeller() {
        return bestSeller;
    }

    public String getGenre() {
        return GENRES[getGenreID()];
    }

    public int getGenreID() {
        return genreID;
    }

    //setters
    public void setLib(Library lib) {
        if (lib == null) {
            System.out.println("Invalid Library");
        } else {
            this.lib = lib;
        }
    }

    public void setId(int id) {
        this.isbn = id;
    }

    public void setDop(int dop) {
        if (dop <= 0 || dop > CURRENT_YEAR) {
            System.out.println("Error date of publication is invalid");
        } else {
            this.dop = dop;
        }
    }

    public void setBestSeller(boolean bestSeller) {
        this.bestSeller = bestSeller;
    }

    public void setTitle(String title) {
        if (title == null) {
            System.out.println("Error title is null");
        } else if (title.length() < 1 || title.length() > 50) {
            System.out.println("Error title is either too long or too short");
        } else {
            this.title = title;
        }
    }

    public void setAuthor(String author) {
        if (author == null) {
            System.out.println("Error author is null");
        } else if (author.length() < 1 || author.length() > 50) {
            System.out.println("Error author's name is either too long or too short");
        } else {
            this.author = author;
        }
    }

    public void setGenre(int genre) {
        if (genre < COMEDY || genre > HISTORY) {
            System.out.println("ERROR - NOT A GENRE");
        } else {
            this.genreID = genre;
        }
    }

    //check if the book is a valid book
    public boolean isValid() {
        //primary variables must have proper values to identify the Book with
        if (this.author == null || this.title == null || dop == 0) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Book { " + "Book Identification = " + isbn + ", Date of Publication = " + dop + ", Book Genre = " + getGenre() + ", Title = " + title + ", Author = " + author + " }";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        //if id is used to identify book then check if they are the same
        if (this.isbn != -1) {
            if (this.isbn == other.isbn) {
                return true;
            }
        }
        if (this.dop != other.dop) {
            return false;
        }
        if (this.genreID != other.genreID) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        return true;
    }

}
