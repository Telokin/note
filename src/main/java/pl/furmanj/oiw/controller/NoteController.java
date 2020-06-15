package pl.furmanj.oiw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.furmanj.oiw.model.Note;
import pl.furmanj.oiw.service.NoteService;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/notes")
public class NoteController {
    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public String showAll(Model model, HttpServletResponse response){
        if(noteService.findAllNote().size()<1){
            noteService.saveNote(new Note("Title 1","root","Content 1","#adds"));
            noteService.saveNote(new Note("Title 2","root","<b>Content 2</b>","#adasdd"));
        }
        model.addAttribute("notes",noteService.findAllNote());
        return "notes/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model){
        noteService.deleteNote(id);
        return "redirect:/notes";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model){
        model.addAttribute("note",noteService.findNoteByID(id));
        return "/notes/update-note";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Note note, Model model) {

        noteService.saveNote(id,note);
        return "redirect:/notes/";
    }

    @GetMapping("/add")
    public String showForm(Model model,Note note){
        return "notes/add-note";
    }

    @PostMapping(value ="/addnote")
    public String add(Model model,@Valid Note note){
        noteService.saveNote(note);
 //       noteService.addUserToNote(note.getAuthor());
        model.addAttribute("notes",noteService.findAllNote());
        return "redirect:/notes/";
    }

    @GetMapping("/{id}")
    public String showOne(@PathVariable("id") long id, Model model){
        model.addAttribute("note",noteService.findNoteByID(id));
        return "notes/one-note";
    }
    @GetMapping("/category")
    public String getAllByCategory(@RequestParam("hash") String category,Model model){
        model.addAttribute("notes",noteService.findByCategory(category));
        return "notes/index";
    }

    @GetMapping("/author/{user}")
    public String findAllByUser(@PathVariable("user") String user, Model model, HttpServletResponse response){
        model.addAttribute("notes", noteService.findByUserName(user));
        return "notes/index";
    }
}
