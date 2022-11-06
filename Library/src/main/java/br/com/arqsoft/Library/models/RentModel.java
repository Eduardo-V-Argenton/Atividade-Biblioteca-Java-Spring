package br.com.arqsoft.Library.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "rent")
public class RentModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;
    @Column(nullable = false)
    private LocalDate rentDate;
    @Column(nullable = false)
    private LocalDate returnDateLimit;
    @Column()
    private LocalDate returnDate;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user", referencedColumnName = "id")
    private UserModel user;
    @ManyToOne(optional = false)
    @JoinColumn(name = "book", referencedColumnName = "id")
    private BookModel book;

    public RentModel() {
    }

    public RentModel(Integer id, LocalDate rentDate, LocalDate returnDateLimit, LocalDate returnDate, UserModel user, BookModel book) {
        this.id = id;
        this.rentDate = rentDate;
        this.returnDateLimit = returnDateLimit;
        this.returnDate = returnDate;
        this.user = user;
        this.book = book;
    }

    public RentModel(LocalDate returnDate, UserModel user, BookModel book) {
        LocalDate date = LocalDate.now();
        this.rentDate = date;
        this.returnDateLimit = date.plusDays(7);
        this.returnDate = returnDate;
        this.user = user;
        this.book = book;
    }

    public RentModel(UserModel user, BookModel book) {
        LocalDate date = LocalDate.now();
        this.rentDate = date;
        this.returnDateLimit = date.plusDays(7);
        this.user = user;
        this.book = book;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }

    public LocalDate getReturnDateLimit() {
        return returnDateLimit;
    }

    public void setReturnDateLimit(LocalDate returnDateLimit) {
        this.returnDateLimit = returnDateLimit;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public BookModel getBook() {
        return book;
    }

    public void setBook(BookModel book) {
        this.book = book;
    }
}
