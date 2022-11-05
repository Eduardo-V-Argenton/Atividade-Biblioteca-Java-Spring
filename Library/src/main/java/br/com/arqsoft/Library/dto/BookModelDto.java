package br.com.arqsoft.Library.dto;

import br.com.arqsoft.Library.models.BookModel;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link br.com.arqsoft.Library.models.BookModel} entity
 */
public class BookModelDto implements Serializable {
    private final String name;
    private final Integer releaseYear;
    private final String location;
    private final String author;
    private final String genre;
    private final String publishingHouse;

    public BookModelDto(String name, Integer releaseYear, String location, String author, String genre, String publishingHouse) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.location = location;
        this.author = author;
        this.genre = genre;
        this.publishingHouse = publishingHouse;
    }

    public String getName() {
        return name;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public String getLocation() {
        return location;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookModelDto entity = (BookModelDto) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.releaseYear, entity.releaseYear) &&
                Objects.equals(this.location, entity.location) &&
                Objects.equals(this.author, entity.author) &&
                Objects.equals(this.genre, entity.genre) &&
                Objects.equals(this.publishingHouse, entity.publishingHouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, releaseYear, location, author, genre, publishingHouse);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "releaseYear = " + releaseYear + ", " +
                "location = " + location + ", " +
                "author = " + author + ", " +
                "genre = " + genre + ", " +
                "publishingHouse = " + publishingHouse + ")";
    }

    public BookModel toBook(BookModel book){
        book.setName(this.name);
        book.setAuthor(this.author);
        book.setGenre(this.genre);
        book.setReleaseYear(this.releaseYear);
        book.setLocation(this.location);
        book.setPublishingHouse(this.publishingHouse);
        return book;
    }

}