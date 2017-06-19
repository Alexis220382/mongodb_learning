package com.ivanovskiy;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Random;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Sorts.orderBy;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.inc;
import static com.mongodb.client.model.Updates.set;

/**
 * Created by Aliaksei_Ivanouski on 6/15/2017.
 */
public class Homework2 {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("course");
        MongoCollection<Document> collection = database.getCollection("findTest");

        collection.drop();

//        !!!!!!find, findOne, filter, projection!!!!!!!
//        for (int i = 0; i < 10; i++) {
//            collection.insertOne(new Document()
//                    .append("x", new Random().nextInt(2))
//                    .append("y", new Random().nextInt(100))
//                    .append("i", i));
//        }
//
////        Bson filter = new Document("x", 0)
////                .append("y", new Document("$gt", 60).append("$lt", 90));
//
//        Bson filter = and(eq("x", 0), gt("y", 10), lt("y", 90));
//
//        //Exclude fields from selection
////        Bson projection = new Document("x", 0).append("_id", 0);
//        //Include fields to selection (_id include by default that's why exclude it manually)
////        Bson projection = new Document("x", 1).append("i", 1).append("_id", 0);
////        Bson projection = exclude("x", "_id");
//        Bson projection = fields(include("x", "y"), excludeId());
//
//        System.out.println("Find one:");
////        Document first = collection.find().first();
//        Document first = collection.find(filter).first();
//        System.out.println(first);
//
//        System.out.println("Find all with into:");
////        ArrayList<Document> into = collection
////                .find()
////                .into(new ArrayList<>());
////        ArrayList<Document> into = collection
////                .find(filter)
////                .into(new ArrayList<>());
//        ArrayList<Document> into = collection
//                .find(filter)
//                .projection(projection)
//                .into(new ArrayList<>());
//        for (Document document : into) {
//            System.out.println(document);
//        }
//
//        System.out.println("Find all with iteration:");
////        MongoCursor<Document> cursor = collection.find().iterator();
//        MongoCursor<Document> cursor = collection.find(filter).iterator();
//        try {
//            while (cursor.hasNext()) {
//                Document cur = cursor.next();
//                System.out.println(cur);
//            }
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//        }
//
//        System.out.println("Count:");
////        long count = collection.count();
//        long count = collection.count(filter);
//        System.out.println(count);

//        //!!!!!!sort, skip, limit!!!!!
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                collection.insertOne(new Document()
//                .append("i", i)
//                .append("j", j));
//            }
//        }
//
//        Bson projection = fields(include("i", "j"), excludeId());
//
////        Bson sort = new Document("x", 1).append("j", -1);
////        Bson sort = orderBy(ascending("i"), descending("j"));
//        Bson sort = descending("j", "i");
//
//        ArrayList<Document> all = collection
//                .find()
//                .projection(projection)
//                .sort(sort)
//                .skip(13)
//                .limit(15)
//                .into(new ArrayList<Document>());
//
//        for (Document cur : all) {
//            System.out.println(cur);

//        //!!!!!!update and replace!!!!!
//        for (int i = 0; i < 8; i++) {
//            collection.insertOne(new Document()
//                    .append("_id", i)
//                    .append("x", i)
//                    .append("y", true));
//        }
//
//        //replace by new document
////        collection.replaceOne(eq("x", 5), new Document("x", 20).append("updated", true));
//        //update existing document
////        collection.updateOne(eq("x", 5), new Document("$set", new Document("x", 20).append("updated", true)));
////        collection.updateOne(eq("x", 5), combine(set("x", 20), set("updated", true)));
//        //with Upsert
////        collection.updateOne(eq("_id", 9), combine(set("x", 20), set("updated", true)),
////                new UpdateOptions().upsert(true));
//        //update many
//        collection.updateMany(gte("x", 5), inc("x", 1));
//
//
//        for (Document cur : collection.find().into(new ArrayList<Document>())) {
//            System.out.println(cur);
//        }
        //!!!!!delete!!!!!!
        for (int i = 0; i < 8; i++) {
            collection.insertOne(new Document("_id", i));
        }

        collection.deleteMany(gt("_id", 4));

        for (Document cur : collection.find().into(new ArrayList<Document>())) {
            System.out.println(cur);
        }
    }
}
