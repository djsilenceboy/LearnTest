
function step1() {
    console.log('The 1st step without message.');
    next('Hello');
}

function step2(message) {
    console.log('The 2nd step with message: %s.', message);
    next('World');
}

function step3(message) {
    console.log('The 2rd step with message: %s.', message);
}

const tasks = [
    step1,
    step2,
    step3
];

function next(message) {
    console.log('Next message: %s.', message);
    const currentTask = tasks.shift();
    if (currentTask) {
        currentTask(message);
    }
}

next();
