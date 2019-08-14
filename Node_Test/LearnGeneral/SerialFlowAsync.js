// npm install -y async
const async = require('async');

function allDone(err, result) {
    console.log('Message sent:', result);
}

async.series([
    callback => {
        console.log('The 1st message.');
        callback(null, '1st');
    },
    callback => {
        setTimeout(() => {
            console.log('The 2nd message.');
            callback(null, '2nd');
        }, 100);
    },
    callback => {
        setTimeout(() => {
            console.log('The 3rd message.');
            callback(null, '3rd');
        }, 500);
    },
    callback => {
        setTimeout(() => {
            console.log('No more message.');
            callback(null, '4th');
        }, 1000);
    },
], allDone);

console.log('You man see this message first.');
