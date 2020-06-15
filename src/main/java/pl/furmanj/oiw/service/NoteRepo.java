package pl.furmanj.oiw.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.furmanj.oiw.model.Author;
import pl.furmanj.oiw.model.Note;



import java.util.List;


@Repository
public interface NoteRepo extends JpaRepository<Note, Long> {

    Note findAllById(Long id);




    @Query("SELECT note FROM  Note note WHERE note.categories LIKE %:name%")
    List<Note> findAllByCategoriesContains(@Param("name") String name);

    @Query("SELECT note FROM Note note LEFT JOIN Author a ON note.author =a WHERE note.author =:user")
    List<Note> findAllByAuthor(@Param("user") Author user);

    @Modifying
    @Query("DELETE FROM Note note WHERE note.id =:note_id")
    void deleteById(@Param("note_id") Long id);




}
