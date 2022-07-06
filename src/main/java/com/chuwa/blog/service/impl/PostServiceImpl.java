package com.chuwa.blog.service.impl;

import com.chuwa.blog.controller.AuthJWTController;
import com.chuwa.blog.entity.Post;
import com.chuwa.blog.exception.ResourceNotFoundException;
import com.chuwa.blog.payload.PostDto;
import com.chuwa.blog.payload.PostResponse;
import com.chuwa.blog.repository.PostRepository;
import com.chuwa.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author b1go
 * @date 6/17/22 12:05 AM
 */
@Service
public class PostServiceImpl implements PostService {

    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

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

        Post newPost = postRepository.save(post);

        PostDto postResponse = mapToDTO(newPost);

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

        logger.info("service getAllPost with pageable are called");
        logger.info("creating a sort object");
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create pageable instance
        logger.info("creating a PageRequest object");
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
        logger.info("calling postRepository to get the data from database");
        Page<Post> pagePosts = postRepository.findAll(pageRequest);

        // get content for page abject
        logger.info("Fetching data successfully and converting data to Dtos");
        List<Post> posts = pagePosts.getContent();
        List<PostDto> postDtos = posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());

        logger.info("adding meta data to the response");
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

        return postDto;
    }

    /**
     * @description convert DTO to entity
     * @param postDto
     * @return
     */
    private Post mapToEntity(PostDto postDto){
        Post post = modelMapper.map(postDto, Post.class);

        return post;
    }
}
