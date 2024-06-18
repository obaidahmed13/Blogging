package genspark.blogging.Blogging.services;

import genspark.blogging.Blogging.models.Post;
import genspark.blogging.Blogging.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImp implements PostService{
    @Autowired
    PostRepository postRepo;
    @Override
    public Post createPost(Post post) {
        return this.postRepo.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return this.postRepo.findAll();
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return this.postRepo.findById(id);
    }

    @Override
    public String deletePost(Long id) {
        return "Post deleted!";
    }

    @Override
    public List<Post> getAllApprovedPosts() {
        return this.postRepo.findByApprovedTrue();
    }

    @Override
    public List<Post> getAllApprovedFalse() {
        return this.postRepo.findByApprovedFalse();
    }

    @Override
    public void approvePost(Long id) {
    }
}
