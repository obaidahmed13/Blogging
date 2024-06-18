package genspark.blogging.Blogging;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import genspark.blogging.Blogging.controllers.PostController;
import genspark.blogging.Blogging.models.Post;
import genspark.blogging.Blogging.services.PostServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PostController.class)
class BloggingApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PostServiceImp postServ;

	@Test
	public void createPost() throws Exception {
		Post post = new Post();
		post.setTitle("Test Title");
		post.setContent("Test Content");
		post.setAuthor("Test Author");

		when(postServ.createPost(any(Post.class))).thenReturn(post);

		mockMvc.perform(post("/post/add")
						.with(user("user").roles("NORMAL")) // or authorities(() -> "NORMAL")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"title\": \"Test Title\", \"content\": \"Test Content\", \"author\": \"Test Author\"}"))
				.andExpect(status().isOk());
	}

	@Test
	public void getAllApproved() throws Exception {
		List<Post> posts = Arrays.asList(new Post(), new Post());
		when(postServ.getAllApprovedPosts()).thenReturn(posts);

		mockMvc.perform(get("/post/approved")
						.with(user("user").roles("NORMAL"))) // Correct usage for setting roles
				.andExpect(status().isOk());
	}

	@Test
	public void getAllUnapproved() throws Exception {
		List<Post> posts = Arrays.asList(new Post(), new Post());
		when(postServ.getAllApprovedFalse()).thenReturn(posts);

		mockMvc.perform(get("/post/unapproved")
						.with(user("admin").roles("ADMIN"))) // Correct usage for setting roles
				.andExpect(status().isOk());
	}

}
