package pl.furmanj.oiw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.furmanj.oiw.model.Note;
import pl.furmanj.oiw.model.Author;

import java.util.List;

@Component
public class NoteService {
    private NoteRepo noteRepo;
    private AuthorRepo userRepo;

    @Autowired
    public NoteService(NoteRepo noteRepo, AuthorRepo userRepo) {
        this.noteRepo = noteRepo;
        this.userRepo = userRepo;
    }



    public void saveNote(Note note) {
        if (note.getAuthor() == null) {
            Author author = new Author();
            author.setUserName("no-name");
            if(userRepo.findByUserName("no-name") == null) {
                userRepo.save(author);
            }else {
                author = userRepo.findByUserName("no-name");
            }
                note.setAuthor(author);

        }
        noteRepo.save(note);
    }

    public void saveNote(long id,Note note) {
        Note newNote =noteRepo.findAllById(id);
        newNote.setText(note.getText());
        newNote.setTitle(note.getTitle());
        newNote.setCreator(note.getCreator());
        newNote.setCategories(note.getCategories());
        newNote.setAuthor(note.getAuthor());
        noteRepo.save(newNote);
    }

    public List<Note> findAllNote() {
        List<Note> note = noteRepo.findAll();
        return note;
    }

    public Note findNoteByID(Long id) {
        Note note = noteRepo.findAllById(id);
        return note;
    }

    public  List<Note> findByUserName(String name){
        Author user = userRepo.findByUserName(name);
        return noteRepo.findAllByAuthor(user);
    }

    public void deleteNote(long id) {
        Note note = findNoteByID(id);
        note.setAuthor(null);
        noteRepo.save(note);
        noteRepo.deleteById(id);
    }

    public List<Note> findByCategory(String name){
        return noteRepo.findAllByCategoriesContains(name);
    }

    public void addUserToNote(Author author) {
        if (author == null){
            Author user = new Author();
            user.setUserName("no-name");
            if(userRepo.findByUserName("non-name")== null)userRepo.save(user);
        }else {
            userRepo.save(author);
        }

    }
}