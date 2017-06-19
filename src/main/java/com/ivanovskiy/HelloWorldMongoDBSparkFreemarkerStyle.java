package com.ivanovskiy;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.bson.Document;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;

import static spark.Spark.halt;

/**
 * Created by Aliaksei_Ivanouski on 6/19/2017.
 */
public class HelloWorldMongoDBSparkFreemarkerStyle {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldMongoDBSparkFreemarkerStyle.class, "/freemarker");

        MongoClient client = new MongoClient();

        MongoDatabase database = client.getDatabase("source");
        MongoCollection<Document> collection = database.getCollection("findTest");

        collection.drop();

        collection.insertOne(new Document("name", "MongoDB"));

        Spark.get("/", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                StringWriter writer = new StringWriter();
                try{
                    Template template = configuration.getTemplate("hello.ftl");

                    Document first = collection.find().first();

                    template.process(first, writer);

                    System.out.println("Name: "+first.get("name"));
                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();
                }
                return writer;
            }
        });
    }
}
