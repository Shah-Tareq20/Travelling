package com.example.travelling.models;

public class PostModel {

    private String postId;
    private String postImage;
    private String postedBy;
    private String postDescription;
    private long postedAt;
    private long postLikes;
    private long postComments;

    public PostModel() {
    }

    public PostModel(String postId, String postImage, String postedBy, String postDescription, long postedAt) {
        this.postId = postId;
        this.postImage = postImage;
        this.postedBy = postedBy;
        this.postDescription = postDescription;
        this.postedAt = postedAt;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public long getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(long postedAt) {
        this.postedAt = postedAt;
    }

    public long getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(long postLikes) {
        this.postLikes = postLikes;
    }

    public long getPostComments() {
        return postComments;
    }

    public void setPostComments(long postComments) {
        this.postComments = postComments;
    }

}
