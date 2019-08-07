const net = require('net');
const server = net.createServer(socket => {
    socket.on('data', data => {
        console.log('Incoming: %s', data);
        socket.write(data);
    });
    socket.on('error', message => {
        console.error('Error: %s', message);
    });
});

server.listen(5050);
