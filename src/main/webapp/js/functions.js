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
        error: function (response) {
            alert(response.getResponseHeader('error_message'))
            //alert("Some error");
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

$(document).ready(function() {
    $('.select-role').select2({
        placeholder: "Выберите уровень доступа",
        maximumSelectionLength: 2,
        language: "ru"
    });
});
function download(id) {
    window.open('downloadfileservlet?recordId=' + id, '_blank');
}

$('body').on('click', '.pass-control', function(){
    var passInp = $('#pass-input');

    if (passInp.attr('type') === 'password'){
        $('#pass-control').addClass('view');
        $('#pass-input').attr('type', 'text');
    } else {
        $('#pass-control').removeClass('view');
        $('#pass-input').attr('type', 'password');
    }
    return false;
});


function registration() {
    event.preventDefault();

    var errField = document.getElementById("error-field");
    errField.innerText = "";

    if (checkForm()) {
        return;
    }

    console.log('Works!');
    var formData = $('form').serialize();

    $.ajax({
        data: formData,
        url: 'addnewuser',
        method: 'POST',
        success: function (resultText) {
            alert("Success");
        },
        error: function () {
            alert("Some error");
            // var errField = document.getElementById("error-field");
            // errField.innerText = "Логин или пароль введён неверно!";
        }
    });
}

function checkForm() {
    var form = document.getElementById("form");
    var fields = form.querySelectorAll('.field');
    var files = document.getElementById("files");

    if (checkFiles(files)) {
        document.getElementById('error-field').innerText = "Файлы должны быть картинками!";
        return true;
    }

    for (var i = 0; i < fields.length; i++) {
        if (!fields[i].value) {
            document.getElementById('error-field').innerText = "Поля не могут быть пустыми!";
            return true;
        }
    }
    return false;
}

function checkForm() {
    var form = document.getElementById("form");
    var fields = form.querySelectorAll('.field');
    var files = document.getElementById("files");

    if (files !== null && checkFiles(files)) {
        document.getElementById('error-field').innerText = "Файлы должны быть картинками!";
        return true;
    }

    for (var i = 0; i < fields.length; i++) {
        if (!fields[i].value) {
            document.getElementById('error-field').innerText = "Поля не могут быть пустыми!";
            return true;
        }
    }
    return false;
}

function downloadAllPosts() {
    event.preventDefault();

    var arrStreets = [];

    $('#tbl > tbody  > tr').each(
        function() {
            arrStreets.push($(this).find('th').text());
        })

    console.log(arrStreets)

    var req = "";
    for (var i = 0; i < arrStreets.length; i++) {
        req += 'records=' + arrStreets[i];

        if (i !== arrStreets.length - 1) {
            req += '&';
        }
    }

    window.open('downloadfiles?' + req, '_blank');
}