const events = require('events');
const net = require('net');
const channel = new events.EventEmitter();

channel.clients = {};
channel.subscriptions = {};

channel.on('join', (id, clientSocket) => {
    channel.clients[id] = clientSocket;
    channel.subscriptions[id] = (senderId, data) => {
        if (id != senderId) {
            const message = `${senderId}: ${data}`;
            channel.clients[id].write(message);
        }
    };
    channel.on('broadcast', channel.subscriptions[id]);
});

channel.on('leave', id => {
    channel.removeListener('broadcast', channel.subscriptions[id]);
    channel.emit('broadcast', id, `<${id}> has left the chatroom.`);
});

const server = net.createServer(clientSocket => {
    const id = `${clientSocket.remoteAddress}:${clientSocket.remotePort}`;
    channel.emit('join', id, clientSocket);

    clientSocket.on('data', data => {
        console.log('Received: <%s>: %s', id, data.toString());
        // In case there are muliple messages, add a special string as line end.
        // Thus, the client can split messages by this special string.
        mesasge = data.toString() + '#!#';
        channel.emit('broadcast', id, mesasge);
    });

    clientSocket.on('close', () => {
        channel.emit('leave', id);
    });

    clientSocket.on('error', err => {
        console.error('Error: <%s>: %s', id, JSON.stringify(err));
    });
});

server.on('error', err => {
    console.error('Error: %s', JSON.stringify(err));
});

server.listen(5050);
