'use strict';


const onTestBtnClickDoServerTest = () => {
    comm.test();
};

const onKillBtnClickDoKillSpeech = () => {
    comm.killSpeech();
};

const onTextToConvertChangeDoSubmit = (obj,event) => {
    if (event.code == 'Enter' && obj.value && obj.value.length > 1) {
        message('info','Submitting Text To Convert..please wait...');
        comm.sendText(obj.value);
    }
};

const onCommandToRunChangeDoSubmit = (obj,event) => {
    if (event.code == 'Enter' && obj.value && obj.value.length > 1) {
        message('info','Submitting Command To Run..please wait...');
        comm.runCommand(obj.value);
    }
};

const onChangeLanguageSelect = (obj) => {
        language.selected = obj.id;
}

const onChangeToFile = (obj) => {
        language.toFile = obj.checked;
}

message('','');