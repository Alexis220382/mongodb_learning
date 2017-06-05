package com.ivanovskiy;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aliaksei_Ivanouski on 6/3/2017.
 */
public class SparkPostHandling {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(SparkPostHandling.class, "/");

        Spark.get("/", new Route() {

            StringWriter writer = null;

            public Object handle(Request request, Response response) throws Exception {
                try {
                    writer = new StringWriter();
                    Template helloTemplate = configuration.getTemplate("ftl/fruitPicker.ftl");
                    Map<String, Object> fruitsMap = new HashMap<String, Object>();
                    fruitsMap.put("fruits", Arrays.asList("apple", "orange", "banana", "peach"));

                    helloTemplate.process(fruitsMap, writer);
                    System.out.println(writer);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if(writer != null) {
                        writer.close();
                    }
                }
                return writer;
            }
        });

        Spark.post("/favorite_fruit", (request, response) -> {
                final String fruit = request.queryParams("fruit");
                if (fruit == null) {
                    return "You need to choose fruit";
                } else {
                    return "Your favorite fruit is " + fruit;
                }
        });
    }
}
