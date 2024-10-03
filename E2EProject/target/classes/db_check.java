package resources;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import org.bson.types.ObjectId;

public class db_check {

	public String msisdn() {
		try (MongoClient mongoClient = new MongoClient("10.51.0.83", 27017)) {
			// Get Database handle by replacing {MongoDBName} with DB name
			MongoDatabase database = mongoClient.getDatabase("PR_DRM");
			// Get collection handle by giving collection name created in MongoDB.
			MongoCollection<Document> test_execution_info = database.getCollection("logicalresources");
			// Document result = new Document("name", "TECHMCMP1001");
//		//Retrieve the id of that particular document
			// ObjectId docId = result.getObjectId("_id");
			ObjectId objectId = new ObjectId("62e17a7f42fde1f11a36aea4");
//		 //Construct a MongoDB query where id is equals to particular id. Using Filters we can construct desired query
//		 Bson query = Filters.eq("_id", docId);
			Document result = new Document("resourceStatus", "Available").append("@type", "MSISDN")
					.append("category", objectId).append("isBundle", false)
					.append("value", new Document("$regex", "^[0-9]{8}$")).append("operationalState", "Functional");
			Document result1 = test_execution_info.find(result).first();
			System.out.println(result1.getString("value"));
			return result1.getString("value");
		}

	}

	public String Sim() {
		try (MongoClient mongoClient = new MongoClient("10.51.0.83", 27017)) {
			// Get Database handle by replacing {MongoDBName} with DB name
			MongoDatabase database = mongoClient.getDatabase("PR_DRM");
			// Get collection handle by giving collection name created in MongoDB.
			MongoCollection<Document> test_execution_info = database.getCollection("physicalresources");
			// Document result = new Document("name", "TECHMCMP1001");
//		//Retrieve the id of that particular document
			// ObjectId docId = result.getObjectId("_id");
			ObjectId objectId = new ObjectId("60d4af6049f11a4f00555ed1");
//		 //Construct a MongoDB query where id is equals to particular id. Using Filters we can construct desired query
//		 Bson query = Filters.eq("_id", docId);
			Document result = new Document("resourceStatus", "Available").append("@type", "SIM")
					.append("category", objectId).append("isBundle", false)
					.append("value", new Document("$regex", "^[0-9]{18}$")).append("operationalState", "Functional");
			Document result1 = test_execution_info.find(result).first();
			System.out.println(result1.getString("value"));
			return result1.getString("value");
		}
	}
}
