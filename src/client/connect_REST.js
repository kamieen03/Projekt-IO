
function POST(){
    fetch('http://localhost:8080/popek')
        .then(function(response) {
            return response.text();
        })
        .then(function(text) {
            document.getElementById("ppp").innerHTML = text;
        });


}

