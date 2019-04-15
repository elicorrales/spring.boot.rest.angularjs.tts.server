'use strict';

var comm = {};

const test = () => {
    console.log('test');
    axios.get('/test')
    .then(
        result => {
            console.log(result);
            message('success',JSON.stringify(result));
        }
    )
    .catch(
        error => {
            console.log(error);
            message('danger',error);
        }
    )
};


const sendText = (text) => {
    axios.post('/text2speech', {
        textToConvert:text
    })
    .then(
        result => {
            console.log(result);
            message('success',JSON.stringify(result));
        }
    )
    .catch(
        error => {
            console.log(error);
            let errMsg = error.response && error.response.data && error.response.data.message?error.response.data.message:error;
            message('danger',errMsg);
        }
    )
};


comm.test = test;
comm.sendText = sendText;