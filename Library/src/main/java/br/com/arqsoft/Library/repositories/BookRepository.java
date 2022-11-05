package br.com.arqsoft.Library.repositories;

import br.com.arqsoft.Library.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookModel, Integer> {
}
