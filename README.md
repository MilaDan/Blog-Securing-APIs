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



### Red-Boook example APIs

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
  "status":1  // 0 - unfavorite  
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
  "reply":["this reply one", "this reply two", "this reply three"]
  //"total_reply": 3 
}
```

#### ToDo APIs

* delete comment
* edit comment 



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

**URL** : `/api/follower`

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
