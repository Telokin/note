package pl.furmanj.oiw.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Entity

@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String creator;
    private String text;
    private String categories;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author user) {
        this.author = user;
    }

    public Note() {
    }

    public Note(String title, String creator, String text, String categories) {
        this.title = title;
        this.creator = creator;
        this.text = text;
        this.categories=categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getCategories() {
        return this.categories;
    }

    public List<String> getListCategories(String s) {
        return getListHashtags(this.categories);
    }

    public void setCategories(String categories) {
        this.categories=getHashtags(categories);
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creator='" + creator + '\'' +
                ", text='" + text + '\'' +
                ", categories='" + categories + '\'' +
                '}';
    }

    public static String getHashtags(String s){
        String out = "";
        if (s != null) {
            Pattern pattern = Pattern.compile("(#[A-Za-z0-9-_]+)");
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                out = out.concat(matcher.group(0));
            }
        }
        return out;
    }

    private List<String> getListHashtags(String s){
        List<String> out = new ArrayList<>();
        if (s != null) {
            Pattern pattern = Pattern.compile("(#[A-Za-z0-9-_]+)");
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                out.add(matcher.group(0));
            }
        }
        return out;
    }
}