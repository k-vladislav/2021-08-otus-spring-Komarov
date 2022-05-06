package ru.otus.homework8.changelog;


import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

@ChangeLog
public class DBChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "vlad", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertBookChaika", author = "vlad")
    public void insertBookChaika(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("Book");
        Document doc = new Document().append("title", "Chaika");
        myCollection.insertOne(doc);
    }

    @ChangeSet(order = "003", id = "insertBookOno", author = "vlad")
    public void insertBookOno(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("Book");
        Document doc = new Document().append("title", "Ono");
        myCollection.insertOne(doc);
    }

}
