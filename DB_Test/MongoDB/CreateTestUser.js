mongo = new Mongo("localhost")
db = mongo.getDB("test")
db.createUser(
	{
		user: "test",
		pwd: "test",
		roles: [{role: "dbOwner", db: "test"}]
   }
)
