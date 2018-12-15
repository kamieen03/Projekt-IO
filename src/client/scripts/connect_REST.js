function GET(param){
    var sel = $("#textarea").getSelection().text;
    fetch('http://localhost:8080/' + sel + "/?transforms=" + param)
        .then(function(response) {
            return response.text();
        })
        .then(function(text) {
            $("#textarea").replaceSelectedText(text);
        });
}

var expanded = false;

function showCheckboxes() {
    var checkboxes = document.getElementById("checkboxes");
    if (!expanded) {
        checkboxes.style.display = "block";
        expanded = true;
    } else {
        checkboxes.style.display = "none";
        expanded = false;
    }
}