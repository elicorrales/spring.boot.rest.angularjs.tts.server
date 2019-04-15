'use strict';
const messageElem = document.getElementById('message');
const message = (type,content) => {
    messageElem.className = 'alert alert-' + type;
    messageElem.innerHTML = content;
};
