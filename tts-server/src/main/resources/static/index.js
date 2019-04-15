'use strict';

const testBtnElem = document.getElementById('testBtn');

const onTestBtnClickDoServerTest = (event) => {
    event.preventDefault();
    comm.test();
};

const onTextToConvertChangeDoSubmit = (obj,event) => {
    if (event.code == 'Enter' && obj.value && obj.value.length > 1) {
        message('info','Submitting Text To Convert..please wait...');
        comm.sendText(obj.value);
    }
};

const onChangeLanguageSelect = (obj) => {

        language.selected = obj.id;
}

message('','');