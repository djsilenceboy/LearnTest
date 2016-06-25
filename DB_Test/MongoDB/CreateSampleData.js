function createSample()
{
	var vowelArr = "aeiou";
	var consenantArr = "bcdfghjklmnpqrstvwxyz";
	var words = "the,be,and,of,to,it,I,can't,shouldn't,say,middle-class,apology,till";
	var wordArr = words.split(",");
	var wordObjArr = new Array();

	for (var i = 0; i < wordArr.length; i++) {
		try {
			var word = wordArr[i].toLowerCase();
			var vowelCnt = ("|" + word + "|").split(/[aeiou]/i).length - 1;
			var consonantCnt = ("|" + word + "|").split(/[bcdfghjklmnpqrstvwxyz]/i).length - 1;
// print("Word: " + word);
// print("VowelCnt: " + vowelCnt);
// print("ConsonantCnt: " + consonantCnt);
			var letters = [];
			var vowels = [];
			var consonants = [];
			var others = [];

			for (var j = 0; j < word.length; j++) {
				var ch = word[j];

				if (letters.indexOf(ch) === -1) {
					letters.push(ch);
				}

				if (vowelArr.indexOf(ch) !== -1) {
					if (vowels.indexOf(ch) === -1) {
						vowels.push(ch);
					}
				} else if (consenantArr.indexOf(ch) !== -1) {
					if (consonants.indexOf(ch) === -1) {
						consonants.push(ch);
					}
				} else {
					if (others.indexOf(ch) === -1) {
						others.push(ch);
					}
				}
			}

			var charsets = [];

			if (consonants.length) {
				charsets.push({type: "consonants", chars: consonants});
			}

			if (vowels.length) {
				charsets.push({type: "vowels", chars: vowels});
			}

			if (others.length) {
				charsets.push({type: "others", chars: others});
			}

			var wordObj = {
				word: word,
				first: word[0],
				last: word[word.length - 1],
				size: word.length,
				letters: letters,
				stats: {vowels: vowelCnt, consonants: consonantCnt},
				charsets: charsets
			};

			if (others.length) {
				wordObj.otherChars = others;
			}

			wordObjArr.push(wordObj);
// printjson(wordObj);
		} catch (e) {
			print("Exception = " + e);
		}
	}

	return wordObjArr;
}

mongo = new Mongo("localhost");
db = mongo.getDB("test");
print("Current DB: " + db);
db.auth("test", "test");

print("Drop DB.");
db.word_stats.drop();
print("Create index.");
db.word_stats.createIndex({word: 1}, {unique: true});

print("Create sample data.");
var wordObjArr = createSample();
printjson(wordObjArr);

print("Insert sample data.");
db.word_stats.insert(wordObjArr);
