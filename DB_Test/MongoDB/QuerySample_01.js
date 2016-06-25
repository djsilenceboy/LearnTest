// mongo = new Mongo("localhost");
// db = mongo.getDB("test");
// db.auth("test", "test");

print("DB = " + db + "\n");

// If omit "var", cursor will automatically iterate first 20 documents.

// var cursor = db.word_stats.find();
var cursor = db.word_stats.find({}, {word: 1});
// var cursor = db.word_stats.find({}, {word: 1}).limit(5);
print("Cursor = " + cursor);
print("Cursor.count = " + cursor.count());
var word = cursor.next();
print("Word: " + JSON.stringify(word));
cursor.forEach(function(item) {
	print("Word: " + item.word);
});

print("\n");

var cursor = db.word_stats.find({}, {word: 1}).limit(5).skip(5);
var word = cursor.next();
print("Word: " + JSON.stringify(word));
db.runCommand({getLastError: 1});

print("\n");

var cursor = db.word_stats.find({first: {$in: ['a', 'b', 'c']}}, {word: 1, first: 1});
print("Cursor = " + cursor);
cursor.forEach(function(item) {
	print(JSON.stringify(item));
});

print("\n");

var cursor = db.word_stats.find({}, {word: 1}).sort({word: 1});
print("Cursor = " + cursor);
cursor.forEach(function(item) {
	print(JSON.stringify(item));
});
