// npm install -y async
const async = require('async');

async.series([
    callback => {
        console.log('The 1st message.');
        callback();
    },
    callback => {
        setTimeout(() => {
            console.log('The 2nd message.');
            callback();
        }, 100);
    },
    callback => {
        setTimeout(() => {
            console.log('The 3rd message.');
            callback();
        }, 500);
    },
    callback => {
        setTimeout(() => {
            console.log('No more message.');
            callback();
        }, 1000);
    },
]);

console.log('You man see this message first.');
