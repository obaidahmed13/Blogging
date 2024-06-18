package genspark.blogging.Blogging.controllers;

import genspark.blogging.Blogging.models.Post;
import genspark.blogging.Blogging.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postServ;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('NORMAL') or hasAuthority('ADMIN')")
    public ResponseEntity<Object> createPost(@RequestBody Post post) {
        Post addedPost = postServ.createPost(post);
        return ResponseEntity.ok(addedPost);
    }

    @GetMapping("/approved")
    @PreAuthorize("hasAuthority('NORMAL') or hasAuthority('ADMIN')")
    public ResponseEntity<Object> getAllApproved() {
        List<Post> approvedPosts = postServ.getAllApprovedPosts();
        return ResponseEntity.ok(approvedPosts);
    }

    @GetMapping("/unapproved")
    @PreAuthorize(" hasAuthority('ADMIN')")
    public ResponseEntity<Object> getAllUnapproved() {
        List<Post> unapprovedPosts = postServ.getAllApprovedFalse();
        return ResponseEntity.ok(unapprovedPosts);
    }


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getAllPosts() {
        return ResponseEntity.ok(postServ.getAllPosts());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getPostByID(@PathVariable Long id) {
        return ResponseEntity.ok(postServ.getPostById(id));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> deletePostByID(@PathVariable Long id) {
        return ResponseEntity.ok(postServ.deletePost(id));
    }

    @PostMapping("/approve/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Post> approvePost(@PathVariable Long id) {
        Optional<Post> postOp = postServ.getPostById(id);
        System.out.println(postOp);
        if (postOp.isPresent()) {
            Post post = postOp.get();
            System.out.println(post);
            post.setApproved(true);
            return ResponseEntity.ok(postServ.createPost(post));
        }
        return null;
    }


}
