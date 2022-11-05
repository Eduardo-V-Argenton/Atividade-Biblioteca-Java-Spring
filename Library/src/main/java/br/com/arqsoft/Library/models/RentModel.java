package br.com.arqsoft.Library.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "rent")
public class RentModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;
    @Column(nullable = false)
    private Date rentDate;
    @Column(nullable = false)
    private Date returnDateLimit;
    @Column()
    private Date returnDate;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user", referencedColumnName = "id")
    private UserModel user;
    @ManyToOne(optional = false)
    @JoinColumn(name = "book", referencedColumnName = "id")
    private BookModel book;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Date getReturnDateLimit() {
        return returnDateLimit;
    }

    public void setReturnDateLimit(Date returnDateLimit) {
        this.returnDateLimit = returnDateLimit;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
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
