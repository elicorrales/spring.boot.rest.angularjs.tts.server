'use strict';

var comm = {};

const test = () => {
    console.log('test');
    return axios.get('/test')
};


const sendText = (text, language, saveToFile) => {
    let url = '/speech?language=' + language + '&toFile=' + saveToFile;
    return axios.post(url, {
        textToConvert:text
    });
};

const checkForWaveFile = (fileName) => {
    let url = '/wave.files/'+fileName;
    return axios.get(url);
};

const killSpeech = () => {
    console.log('kill');
    return axios.delete('/speech')
};


const runCommand = (text) => {
    return axios.post('/command', {
        command:text
    })
};


comm.test = test;
comm.sendText = sendText;
comm.checkForWaveFile = checkForWaveFile;
comm.killSpeech = killSpeech;
comm.runCommand = runCommand;