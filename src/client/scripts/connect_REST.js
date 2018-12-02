function POST(param){
    var sel = $("#textarea").getSelection().text;
    fetch('http://localhost:8080/' + sel + "/?transforms=" + param)
        .then(function(response) {
            return response.text();
        })
        .then(function(text) {
            $("#textarea").replaceSelectedText(text);
        });
}