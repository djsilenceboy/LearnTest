mongo = new Mongo("localhost")
db = mongo.getDB("test")
db.createUser(
	{
		user: "test",
		pwd: "test",
		roles: [{role: "readWrite", db: "test"}]
   }
)
db.updateUser(
	"test",
	{
		pwd: "test",
		roles: [{role: "dbOwner", db: "test"}]
   }
)
