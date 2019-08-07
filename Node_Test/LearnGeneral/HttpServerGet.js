var http = require('http');
var messages = [
    'Hello World',
    'From a basic Node.js server'];
var counter = 1;

http.createServer(function (request, response) {
    console.log('Client request for URL %s', request.url);
    if (request.url == '/') {
        console.log('Prepare html %s', counter++);
        response.setHeader('Content-Type', 'text/html');
        response.writeHead(200);
        response.write('<html><head><title>Simple HTTP Server</title></head>');
        response.write('<body>');
        for (var index in messages) {
            response.write('\n<h1>' + messages[index] + '</h1>');
        }
        response.end('\n</body></html>');
    }
}).listen(5050);
