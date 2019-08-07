const http = require('http');
const fs = require('fs');
var counter = 1;

http.createServer(function (request, response) {
    console.log('Client request for URL %s', request.url);
    if (request.url == '/') {
        console.log('Prepare html %s', counter++);
        getMessages(response);
    }
}).listen(5050);

function getMessages(response) {
    console.log("Get messages from file...");
    fs.readFile('./CallbackTestMessage.json', (err, data) => {
        if (err) return hadError(err, response);
        getTemplate(JSON.parse(data.toString()), response);
    });
}

function getTemplate(messages, response) {
    console.log("Get template from file...");
    fs.readFile('./CallbackTestTemplate.html', (err, data) => {
        if (err) return hadError(err, response);
        formatHtml(data.toString(), messages, response);
    });
}

function formatHtml(template, messages, response) {
    console.log("Format HTML from template and messages...");
    const html = template.replace('ReplaceMe', messages.join('</li><li>'));
    response.writeHead(200, { 'Content-Type': 'text/html' });
    response.end(html);
}

function hadError(err, response) {
    console.error(err);
    response.end('Server Error');
}
