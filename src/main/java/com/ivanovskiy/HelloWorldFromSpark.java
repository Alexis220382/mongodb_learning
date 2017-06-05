package com.ivanovskiy;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by Aliaksei_Ivanouski on 6/2/2017.
 */
public class HelloWorldFromSpark {
    public static void main(String[] args) {
        Spark.get("/", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                return "Hello World from Spark!!!";
            }
        });
    }
}
