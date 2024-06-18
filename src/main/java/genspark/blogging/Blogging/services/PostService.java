package genspark.blogging.Blogging.services;

import genspark.blogging.Blogging.models.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post createPost(Post post);
    List<Post> getAllPosts();
    Optional<Post> getPostById(Long id);
    String deletePost(Long id);
    List<Post> getAllApprovedPosts();
    List<Post> getAllApprovedFalse();
    void approvePost(Long id);



}
