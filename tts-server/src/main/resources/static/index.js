'use strict';

const waveFileAudioDropInElem = document.getElementById('waveFileAudioDropIn');

var saveToFile;
var keepCheckingForWaveFile = false;

const onTestBtnClickDoServerTest = () => {
    comm.test()
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
    );
};

const onKillBtnClickDoKillSpeech = () => {
    comm.killSpeech()
    .then(
        result => {
            console.log(result);
            message('success',JSON.stringify(result.data,null,'<br/>'));
            waveFileAudioDropInElem.innerHTML = '';
        }
    )
    .catch(
        error => {
            console.log(error);
            message('danger',error);
        }
    );
};

const checkIfWaveFileIsAvailable = (waveFile) => {
    comm.checkForWaveFile(waveFile)
    .then(
        result => {
            waveFileAudioDropInElem.innerHTML = '<audio src="wave.files/' + waveFile + '" controls autoplay></audio>';
            message('success','Got File Loaded : ' + waveFile);
            keepCheckingForWaveFile = false;
        }
    )
    .catch(
        error => {
            console.log(error);
            let errMsg = error.response && error.response.data && error.response.data.message?error.response.data.message:error;
            message('danger',errMsg);
            keepCheckingForWaveFile = true;
		    checkForWaveFileStatusRunner(waveFile);
        }
    );
};

const checkForWaveFileStatusRunner = (waveFile) => {
	if (!keepCheckingForWaveFile) { return; }
	setTimeout(()=>{ checkIfWaveFileIsAvailable(waveFile); },400);
};

const onTextToConvertChangeDoSubmit = (obj,event) => {
    if (event.code == 'Enter' && obj.value && obj.value.length > 1) {
        message('info','Submitting Text To Convert..please wait...');
        comm.sendText(obj.value, language.selected, saveToFile)
        .then(
            result => {
                console.log(result);
                let resultMsg = result.data.message?result.data.message:result.data;
                if (saveToFile) {
                    keepCheckingForWaveFile = true;
                    checkForWaveFileStatusRunner(resultMsg);
                    message('success','wave file is ' + resultMsg);
                } else {
                    message('success',resultMsg);
                }
            }
        )
        .catch(
            error => {
                console.log(error);
                let errMsg = error.response && error.response.data && error.response.data.message?error.response.data.message:error;
                message('danger',errMsg);
            }
        );
    };
};

const onCommandToRunChangeDoSubmit = (obj,event) => {
    if (event.code == 'Enter' && obj.value && obj.value.length > 1) {
        message('info','Submitting Command To Run..please wait...');
        comm.runCommand(obj.value)
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
    }
};

const onChangeLanguageSelect = (obj) => {
        language.selected = obj.id;
}

const onChangeToFile = (obj) => {
        saveToFile = obj.checked;
}

message('','');