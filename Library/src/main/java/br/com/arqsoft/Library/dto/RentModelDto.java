package br.com.arqsoft.Library.dto;

import br.com.arqsoft.Library.models.UserModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * A DTO for the {@link br.com.arqsoft.Library.models.RentModel} entity
 */
public class RentModelDto implements Serializable {
    private final Date rentDate;
    private final Date returnDateLimit;
    private final Date returnDate;
    private final UserModel user;
    private final BookModelDto book;

    public RentModelDto(Date rentDate, Date returnDateLimit, Date returnDate, UserModel user, BookModelDto book) {
        this.rentDate = rentDate;
        this.returnDateLimit = returnDateLimit;
        this.returnDate = returnDate;
        this.user = user;
        this.book = book;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public Date getReturnDateLimit() {
        return returnDateLimit;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public UserModel getUser() {
        return user;
    }

    public BookModelDto getBook() {
        return book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentModelDto entity = (RentModelDto) o;
        return Objects.equals(this.rentDate, entity.rentDate) &&
                Objects.equals(this.returnDateLimit, entity.returnDateLimit) &&
                Objects.equals(this.returnDate, entity.returnDate) &&
                Objects.equals(this.user, entity.user) &&
                Objects.equals(this.book, entity.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentDate, returnDateLimit, returnDate, user, book);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "rentDate = " + rentDate + ", " +
                "returnDateLimit = " + returnDateLimit + ", " +
                "returnDate = " + returnDate + ", " +
                "user = " + user + ", " +
                "book = " + book + ")";
    }
}