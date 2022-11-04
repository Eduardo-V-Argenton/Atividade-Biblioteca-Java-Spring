package br.com.arqsoft.Library.repositories;

import br.com.arqsoft.Library.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<BookModel, UUID> {
}
