// curl http://localhost:5050/messages
// curl -H 'Content-Type: application/json' -d '{"Hello": "World"}' -X POST http://localhost:5050/message
// curl -X DELETE http://localhost:5050/message
const express = require('express');
const bodyParser = require('body-parser')

const app = express();
const messages = ['I am already here.'];

app.use(bodyParser.urlencoded({
    extended: true
}));
app.use(bodyParser.json());

app.get('/messages', (request, response) => {
    console.log('GET :', messages)
    response.send(messages);
});

app.post('/message', (request, response) => {
    let message = JSON.stringify(request.body);
    console.log('POST :', message)
    messages.push(message);
    console.log('Cache :', messages)
    response.send(message);
});

app.delete('/message', (request, response) => {
    let message = messages.pop(0);
    console.log('DELETE :', message)
    response.send(message);
});

app.listen(5050, () => {
    console.log('Express listen on port 5050.');
});
