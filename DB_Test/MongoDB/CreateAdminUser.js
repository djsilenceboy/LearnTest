mongo = new Mongo("localhost")
db = mongo.getDB("admin")
db.createUser(
	{
		user: "root",
		pwd: "admin",
		roles: [{role: "root", db: "admin"}]
	}
)
db.createUser(
	{
		user: "admin",
		pwd: "admin",
		roles: [{role: "userAdminAnyDatabase", db: "admin"}]
  }
)
