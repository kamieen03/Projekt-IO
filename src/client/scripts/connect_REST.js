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

