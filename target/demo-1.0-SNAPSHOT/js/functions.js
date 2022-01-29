function lock_unlock() {
    var image = document.getElementById("lock");
    var passInp = document.getElementById("password");

    if (passInp.getAttribute('type') == 'password'){
        image.src = "./images/lock_1.png";
        passInp.setAttribute('type', 'text');
    } else {
        image.src = "./images/lock_0.png";
        passInp.setAttribute('type', 'password');
    }
    return false;
}

function auth() {
    event.preventDefault();

    var errField = document.getElementById("error-field");
    errField.innerText = "";

    console.log('Works!');
    var formData = $('form').serialize();

    $.ajax({
        data: formData,
        url: 'auth',
        method: 'POST',
        success: function (resultText) {
            location.reload();
        },
        error: function () {
            var errField = document.getElementById("error-field");
            errField.innerText = "Логин или пароль введён неверно!";
        }
    });
}

function send() {
    event.preventDefault();

    // var errField = document.getElementById("error-field");
    // errField.innerText = "";

    var formData = new FormData(document.getElementById('form'));
    console.log('Works!');

    $.ajax({
        type: 'post',
        data: formData,
        url: 'sendservlet',
        method: 'POST',
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        success: function (resultText) {
            console.log('Works twice!');
            alert(resultText);
        },
        error: function () {
            alert("Some error");
            // var errField = document.getElementById("error-field");
            // errField.innerText = "Логин или пароль введён неверно!";
        }
    });
}

function getPosts() {
    event.preventDefault();

    // var errField = document.getElementById("error-field");
    // errField.innerText = "";

    console.log('Works!');
    var formData = $('form').serialize();


    $.ajax({
        data: formData,
        url: 'getpostsservlet',
        method: 'POST',
        success: function (resultText) {
            var place = document.getElementById("posts");
            place.innerHTML = resultText;
        },
        error: function () {
            alert("Some error");
            // var errField = document.getElementById("error-field");
            // errField.innerText = "Логин или пароль введён неверно!";
        }
    });
}

$(document).ready(function() {
    $('.select-street').select2({
        placeholder: "Выберите улицу",
        maximumSelectionLength: 2,
        language: "ru"
    });
});

$(document).ready(function() {
    $('.select-employee').select2({
        placeholder: "Выберите сотрудника",
        maximumSelectionLength: 2,
        language: "ru"
    });
});