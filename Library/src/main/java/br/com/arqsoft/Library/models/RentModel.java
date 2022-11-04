package br.com.arqsoft.Library.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Rent")
public class RentModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;
    @Column(nullable = false)
    private Date rentDate;
    @Column(nullable = false)
    private Date returnDateLimit;
    @Column()
    private Date returnDate;
    @ManyToOne(optional = false)
    @JoinColumn(name = "User", referencedColumnName = "id")
    private UserModel user;
    @ManyToOne(optional = false)
    @JoinColumn(name = "Book", referencedColumnName = "id")
    private BookModel book;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
