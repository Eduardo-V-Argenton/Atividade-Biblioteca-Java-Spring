package br.com.arqsoft.Library.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "book")
public class BookModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;
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
    @Column(nullable = false)
    private Boolean available;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "book")
    private List<RentModel> rentList;

    public BookModel() {
        this.available = true;
    }

    public BookModel(String name, Integer releaseYear, String location, String author, String genre, String publishingHouse, Boolean available) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.location = location;
        this.author = author;
        this.genre = genre;
        this.publishingHouse = publishingHouse;
        this.available = available;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<RentModel> getRentList() {
        return rentList;
    }

    public void setRentList(List<RentModel> rentList) {
        this.rentList = rentList;
    }

}
