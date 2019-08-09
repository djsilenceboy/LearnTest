const events = require('events');
const net = require('net');

const options = {
    hostname: 'localhost',
    port: '5050',
};

var id;
var counter = 0;

const client = net.createConnection(options, () => {
    id = client.localAddress + ':' + client.localPort;
    console.log('Server : %s:%s', client.remoteAddress, client.remotePort);
    console.log('Client ID: %s', id);
});

client.setTimeout(1000);
client.setEncoding('utf8');

client.on('data', data => {
    // In case there are muliple messages, add a special string as line end.
    // Thus, the client can split messages by this special string.
    var messages = data.toString().split('#!#').filter(item => item.length > 0);
    for (var index in messages) {
        console.log('Received: %s', messages[index]);
    }
});

client.on('end', () => {
    console.log('Client disconnect.');
});

client.on('timeout', () => {
    const message = `Msg ${counter++}`;
    console.log('Send: %s', message);
    client.write(message);
    client.setTimeout(2000);
});

client.on('error', err => {
    console.error('Error: %s', JSON.stringify(err));
});
