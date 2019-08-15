const express = require('express');
const app = express();

app.get('/', (request, response) => {
    response.send('Hello, World!');
});

app.listen(5050, () => {
    console.log('Express listen on port 5050.');
});
