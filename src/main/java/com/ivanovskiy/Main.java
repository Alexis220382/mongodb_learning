package com.ivanovskiy;

import com.mongodb.*;

/**
 * Created by Aliaksei_Ivanouski on 6/12/2017.
 */
public class Main {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        DB database = client.getDB("students");
        DBCollection collection = database.getCollection("grades");

        /*
        Hint/spoiler: If you select homework grade-documents, sort by student
        and then by score, you can iterate through and find the lowest score
        for each student by noticing a change in student id. As you notice
        that change of student_id, remove the document.
         */
        QueryBuilder builder = QueryBuilder.start("type").is("homework");

        DBCursor cursor = collection.find(builder.get())
                .sort(new BasicDBObject("student_id", 1).append("score", 1));

        int curStudentId = -1;
        try {
            while (cursor.hasNext()) {
                BasicDBObject doc = (BasicDBObject) cursor.next();
                int studentId = doc.getInt("student_id");

                if (studentId != curStudentId) {
                    collection.remove(doc);
                    curStudentId = studentId;
                }
            }
        } finally {
            cursor.close();
        }
    }
}
