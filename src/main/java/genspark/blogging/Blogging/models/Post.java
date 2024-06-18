package genspark.blogging.Blogging.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    private boolean approved = false;
    private String author;

    public Post(String title, String content, boolean approved, String author ) {
        this.title = title;
        this.content = content;
        this.approved = approved;
        this.author = author;
    }

}
