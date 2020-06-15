package pl.furmanj.oiw.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.furmanj.oiw.model.Note;
import pl.furmanj.oiw.model.Author;


import java.util.List;


@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {





    Author findByUserName(String name);
}
