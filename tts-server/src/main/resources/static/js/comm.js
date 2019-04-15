'use strict';

var comm = {};

const test = () => {
    console.log('test');
    axios.get('/test')
    .then(
        result => {
            console.log(result);
            message('success',result);
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
    let url = '/speech?language=' + language.selected + '&toFile=' + language.toFile;
    axios.post(url, {
        textToConvert:text
    })
    .then(
        result => {
            console.log(result);
            let resultMsg = result.data.message?result.data.message:result.data;
            message('success',resultMsg);
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

const killSpeech = () => {
    console.log('kill');
    axios.delete('/speech')
    .then(
        result => {
            console.log(result);
            message('success',JSON.stringify(result.data,null,'<br/>'));
        }
    )
    .catch(
        error => {
            console.log(error);
            message('danger',error);
        }
    )
};


const runCommand = (text) => {
    axios.post('/command', {
        command:text
    })
    .then(
        result => {
            console.log(result);
            let output = '';
            for (var i=0;i<result.data.length;i++) {
                output += result.data[i].line + '<br/>';
            }
            message('success',output);
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
comm.killSpeech = killSpeech;
comm.runCommand = runCommand;