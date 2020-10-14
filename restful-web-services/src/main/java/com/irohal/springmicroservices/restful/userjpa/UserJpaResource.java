package com.irohal.springmicroservices.restful.userjpa;

import com.irohal.springmicroservices.restful.user.PostNotFoundException;
import com.irohal.springmicroservices.restful.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    // retrieve all users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    // retrieve single user
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id) {
        final Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("id=" + id);
        }

        final EntityModel<User> userHateoasResource = EntityModel.of(userOpt.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserJpaResource.class).retrieveAllUsers())
                        .withRel("all-users"));

        return userHateoasResource;
    }

    // create new user
    @PostMapping("/jpa/users")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        final User createdUser = userRepository.save(user);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(createdUser.getId());
        return ResponseEntity.created(location).build();
    }

    // delete user
    @DeleteMapping("/jpa/users/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // retrieve all user's posts
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllUsersPosts(@PathVariable Integer id) {
        final Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("id=" + id);
        }
        return userOpt.get().getPosts();
    }

    // retrieve single user's post
    @GetMapping("/jpa/users/{userId}/posts/{postId}")
    public Post retrieveUsersPost(@PathVariable Integer userId, @PathVariable Integer postId) {
        final Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("id=" + userId);
        }
        final Optional<Post> postOpt = postRepository.findById(postId);
        if (postOpt.isEmpty()) {
            throw new PostNotFoundException("id=" + postId);
        }
        final Post post = postOpt.get();
        if (post.getUser().getId() != userId) {
            throw new PostNotFoundException("Post with id=" + postId + " does not belong to User with id=" + userId);
        }
        return post;
    }

    // create new users' post
    @PostMapping("/jpa/users/{userId}/posts")
    public ResponseEntity createUsersPost(@PathVariable Integer userId, @RequestBody Post post) {
        final Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("id=" + userId);
        }

        post.setUser(userOpt.get());
        final Post createdPost = postRepository.save(post);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{postId}").build(createdPost.getId());
        return ResponseEntity.created(location).build();
    }

}
