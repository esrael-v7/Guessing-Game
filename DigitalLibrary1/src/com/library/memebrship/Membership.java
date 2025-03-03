package com.library.memebrship;

public class Membership {
    private int membershipId;
    private int viewerId;
    private String comment;
    private int shareCount;
    private int likeCount;
    private int viewCount;

    // Constructor, Getters, and Setters
    public Membership(int membershipId, int viewerId, String comment, int shareCount, int likeCount, int viewCount) {
        this.membershipId = membershipId;
        this.viewerId = viewerId;
        this.comment = comment;
        this.shareCount = shareCount;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
    }

    public int getMembershipId() { return membershipId; }
    public void setMembershipId(int membershipId) { this.membershipId = membershipId; }
    public int getViewerId() { return viewerId; }
    public void setViewerId(int viewerId) { this.viewerId = viewerId; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public int getShareCount() { return shareCount; }
    public void setShareCount(int shareCount) { this.shareCount = shareCount; }
    public int getLikeCount() { return likeCount; }
    public void setLikeCount(int likeCount) { this.likeCount = likeCount; }
    public int getViewCount() { return viewCount; }
    public void setViewCount(int viewCount) { this.viewCount = viewCount; }
}
