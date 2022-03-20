package com.ebook.bucket;

public enum BucketName {

    INFORMATION("nurbek-e-book");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
