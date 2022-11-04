package br.com.arqsoft.Library.models;


import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Book")
public class BookModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column
    private Integer releaseYear;
    @Column(nullable = false)
    private String location;
    @Column
    private String author;
    @Column(nullable = false)
    private String genre;
    @Column(nullable = false)
    private String publishingHouse;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private List<RentModel> rentList;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public List<RentModel> getRentList() {
        return rentList;
    }

    public void setRentList(List<RentModel> rentList) {
        this.rentList = rentList;
    }
}
