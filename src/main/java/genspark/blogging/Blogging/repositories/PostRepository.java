package genspark.blogging.Blogging.repositories;

import genspark.blogging.Blogging.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByApprovedTrue();
    List<Post> findByApprovedFalse();
}
