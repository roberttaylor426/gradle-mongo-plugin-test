package com.sourcemuse.gradle.plugin.test;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class MongoDbIntegrationTest {

    @Test
    public void saveAndLoadAnObject() {
        Document document = new Document();
        document.append("key", "value");
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase db = mongoClient.getDatabase("test");
        db.createCollection("test-collection");
        MongoCollection<Document> collection = db.getCollection("test-collection");
        collection.insertOne(document);
        Document dbObject = collection.find().first();
        assertThat(document, equalTo(dbObject));
    }
}
