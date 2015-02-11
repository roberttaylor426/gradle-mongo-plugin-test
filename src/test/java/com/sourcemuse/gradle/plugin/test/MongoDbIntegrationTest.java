package com.sourcemuse.gradle.plugin.test;

import com.mongodb.*;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class MongoDbIntegrationTest {

    @Test
    public void saveAndLoadAnObject() {
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.append("key", "value");
        try {
            MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
            DB db = mongoClient.getDB("test");
            DBCollection dbCollection = db.createCollection("test-collection", basicDBObject);
            dbCollection.insert(basicDBObject);
            DBObject dbObject = dbCollection.findOne();
            assertThat(basicDBObject, equalTo(dbObject));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
