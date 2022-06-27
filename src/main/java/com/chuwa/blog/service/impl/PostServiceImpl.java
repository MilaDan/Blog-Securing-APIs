package com.chuwa.blog.service.impl;

import com.chuwa.blog.entity.Post;
import com.chuwa.blog.exception.ResourceNotFoundException;
import com.chuwa.blog.payload.PostDto;
import com.chuwa.blog.payload.PostResponse;
import com.chuwa.blog.repository.PostRepository;
import com.chuwa.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author b1go
 * @date 6/17/22 12:05 AM
 */

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

//    @Autowired
//    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
//        this.postRepository = postRepository;
//        this.modelMapper = modelMapper;
//    }

    /**
     * "@Autowired" spring 4.3 onwards, the bean only have one constructor, so we omit constructor
     * @param postRepository
     */
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        // convert DTO to entity

        Post post = mapToEntity(postDto);
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());

        Post newPost = postRepository.save(post);

        PostDto postResponse = mapToDTO(newPost);
//        PostDto postResponse = new PostDto();
//        postResponse.setId(newPost.getId());
//        postResponse.setTitle(newPost.getTitle());
//        postResponse.setDescription(newPost.getDescription());
//        postResponse.setContent(newPost.getContent());

        return postResponse;
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> posts = postRepository.findAll();
        List<PostDto> postDtos = posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create pageable instance

        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
//        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
//        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<Post> pagePosts = postRepository.findAll(pageRequest);

        // get content for page abject
        List<Post> posts = pagePosts.getContent();
        List<PostDto> postDtos = posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNo(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalElements(pagePosts.getTotalElements());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setLast(pagePosts.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
//        Optional<Post> post = postRepository.findById(id);
//        post.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

//        Post post = postRepository.findById(id).orElseThrow(null);
//        Post post = postRepository.findById(id).get();

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));


        return mapToDTO(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        //  Question, why do we need to find it out firstly?
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatePost = postRepository.save(post);
        return mapToDTO(updatePost);
    }

    @Override
    public void deletePostById(long id) {
        //  Question, why do we need to find it out firstly?
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    /**
     *
     * @param post
     * @return
     * @description convert DTO to entity
     */
    private PostDto mapToDTO(Post post) {

        //                           source class  destination class
        PostDto postDto = modelMapper.map(post, PostDto.class);
//        use modelMapper then we comment off the below code
//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());

        return postDto;
    }

    /**
     * @description convert DTO to entity
     * @param postDto
     * @return
     */
    private Post mapToEntity(PostDto postDto){
        Post post = modelMapper.map(postDto, Post.class);

//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());

        return post;
    }
}
