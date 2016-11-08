function getDataFromForm(form) {
    if (form.tagName != 'FORM') {
        return;
    }

    var data = {};

    for (var i = 0; i < form.length; i++) {
        var input = form[i];
        if (input.type != 'submit' && input.name) {
            data[input.name] = input.value;
        }
    }

    return data;
}

function sendJson(httpMethod, url, data) {
    // отправляем ajax'ом json
    var xhr = new XMLHttpRequest();

    xhr.open(httpMethod, url, true);
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');

    var json = JSON.stringify(data);
    xhr.send(json);
}

function sendJsonFromForm(event) {
    event.preventDefault(); // отменяем действие на сабмит

    var form = event.target;
    var data = getDataFromForm(form);
    sendJson(data);
}

function addNewIngredient() {

}
