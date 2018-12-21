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
var checked = 0;

function showCheckboxes() {
    var checkboxList = document.getElementById("checkboxes");

    if(checked > 0){
        var checkboxes = checkboxList.getElementsByTagName("input");
        multipleGET(checkboxes);
        document.getElementById("lb1").innerHTML = "Choose...";
        for( i = 0 ; i < checkboxes.length; i++)
            checkboxes[i].checked = false;
        checked = 0;
    }

    if (!expanded) {
        checkboxList.style.display = "block";
        expanded = true;
    } else {
        checkboxList.style.display = "none";
        expanded = false;
    }
}

var checkboxes = document.getElementById("checkboxes").getElementsByTagName("input");

for(i=0; i < checkboxes.length; i++)
    checkboxes.item(i).addEventListener("click", multiTransform);

function multiTransform(){
    if(this.checked){
        checked++;
        if(checked === 1)
            document.getElementById("lb1").innerHTML = "Transform";
    }
    else{
        checked--;
        if(checked === 0)
            document.getElementById("lb1").innerHTML = "Choose...";
    }
}

function multipleGET(checkboxes){
    var params = [];

    for(i = 0; i < checkboxes.length; i++)
        if(checkboxes[i].checked)
            params.push(checkboxes[i].name);
    GET(params);
}

// text area handles tabs
$(document).delegate('#textarea', 'keydown', function(e) {
    var keyCode = e.keyCode || e.which;

    if (keyCode == 9) {
        e.preventDefault();
        var start = this.selectionStart;
        var end = this.selectionEnd;

        // set textarea value to: text before caret + tab + text after caret
        $(this).val($(this).val().substring(0, start)
            + "\t"
            + $(this).val().substring(end));

        // put caret at right position again
        this.selectionStart =
            this.selectionEnd = start + 1;
    }
});