// npm install -y async
const async = require('async');

function allDone(err, result) {
    console.log('Message sent:', result);
}

function sleep(time) {
    return new Promise((resolve) => setTimeout(resolve, time));
}

async.parallel([
    callback => {
        for (let i = 0; i < 4; i++) {
            sleep(1100).then(() => {
                console.log('The 1st message %d.', i);
            });
        }
        callback(null, '1st');
    },
    callback => {
        for (let i = 0; i < 4; i++) {
            sleep(900).then(() => {
                console.log('The 2nd message %d.', i);
            });
        }
        callback(null, '2nd');
    },
    callback => {
        for (let i = 0; i < 4; i++) {
            sleep(1000).then(() => {
                console.log('The 3rd message %d.', i);
            });
        }
        callback(null, '3rd');
    },
], allDone);

console.log('You man see this message in random.');
