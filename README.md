# blog-rest-api

## Model Mapper

## Error/Exception Handler

## Validation

## repos change
**Don't Pull Here**

### Entity & Post
practice Entity and basic Controller, Service, Dao
```
git remote set-url origin https://github.com/TAIsRich/icc-blog.git
```

### Post & Comment
practice Controller, Service, Dao, entity
```
git remote set-url origin https://github.com/TAIsRich/blog-rest-api.git
```

### Blog-mapper-exception-validation
* Mapper
* Exception
* Validation
* Unit Test

```
git remote set-url origin https://github.com/TAIsRich/Blog-mapper-exception-validation.git
```

### Blog-Securing-APIs
```
git remote set-url origin https://github.com/TAIsRich/Blog-Securing-APIs.git
```
The main/swagger would be the one I added swagger.



## Red-Boook DB

![myblog-myblog[myblog]-202271200320](myblog-myblog[myblog]-202271200320.png)

## Red-Boook example APIs

#### 1,PostLikes

**URL** : `/api/post/like`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : None

##### Success Response

**Code** : `200 OK`

**Content examples**

```json
{
  "post_id":1234,
  "user_id":456, //Or, get from UserSession 
  "status":1 // 0 - unlike it
}
```

**URL** : `/api/post/like/{post_id}`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : None

##### Success Response

**Code** : `200 OK`

**Response examples**

```json
{
  "user_id":[1234,456,789]
  //"total_like": 3 
}
```

#### ToDo APIs

* Unlike Post 



#### 2, Favorites

**URL** : `/api/post/favorite`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : None

##### Success Response

**Code** : `200 OK`

**Request examples**

```json
{
  "post_id":1234,
  "user_id":456, //Or, get from UserSession 
  //"status":1  // default = 1, default = 0 - unfavorite  
}
```

**URL** : `/api/post/favorite/{post_id}`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : None

##### Success Response

**Code** : `200 OK`

**Request examples**

```json
{
  "user_id":[1234,456,789]
  //"total_favorite": 3 
}
```

#### ToDo APIs

* Unfavorite Post 



#### 3, CommentLikes

**URL** : `/api/post/{postId}/comments/like`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : None

##### Success Response

**Code** : `200 OK`

**Content examples**

```json
{
  "comment_id":1234,
  "user_id":456, //Or, get from UserSession 
  "status":1 // 0 - unlike it
}
```

**URL** : `/api/post/{postId}/comments/like/{commentId}`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : None

##### Success Response

**Code** : `200 OK`

**Response examples**

```json
{
  "user_id":[1234,456,789]
  //"total_like": 3 
}
```

#### ToDo APIs

* Unlike Comment 



#### 4, CommentReply

**URL** : `/api/post/{postId}/comments/{commentId}/reply`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : None

##### Success Response

**Code** : `200 OK`

**Content examples**

```json
{
  "user_id":456, //Or, get from UserSession 
  "body":"this is a reply to comment_id 1234"
}
```



**URL** : `/api/post/{postId}/comments/{commentId}/reply`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : None

##### Success Response

**Code** : `200 OK`

**Response examples**

```json
{
  "commentId": 123,
  "reply":[{"id":456,
  				 "name:":"Alice", 
           "body":"this reply one"},
				   {"id":457,
  				 "name:":"Billy", 
           "body":"this reply two"},
					 ...]
  //"total_reply": 3 
}
```

```java
class Reply{
	private long id;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "comment_id", nullable = false)
  private Comment comment;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  private String body;
  //...
}

class ReplyDTO{
	private long id;
  private String name;
  private String body; 
	//...  
}

//Inside yoru Comment & CommentDto

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty("name")
    private String name;
    private String email;
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
  
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private List<Reply> replies;
}

public class CommentDto {
    private long id;
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;
    @NotEmpty
    @Size(min = 5, message = "Comment body must be minimum 5 characters")
    private String body;
  
    //Need to add the following 
    private List<ReplyDTO> replies;  //Nested replyDTO 
}

	 //Now to make sure update your `CommentServiceImpl` to support the ReplyDTO convert by add the line: 
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

```

#### ToDo APIs

* delete reply
* edit reply 



#### 5, Follower

**URL** : `/api/follower`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : None

##### Success Response

**Code** : `200 OK`

**Request examples**

```json
{
  "user_id":456, //Or, get from UserSession 
  "following_userId": 678 //user 456 following 678. 
  "status":1  // 0 - unfollow   
}
```



**URL** : `/api/follower/{userId}`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : None

##### Success Response

**Code** : `200 OK`

**Request examples**

```json
{
  "user_id":[1234,456,789]
  //"total_follower": 3 
}
```

#### ToDo APIs

* unfollow users; 
