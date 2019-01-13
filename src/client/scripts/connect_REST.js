$( "#textarea" ).keyup( function() {
    $( "#output_div" ).html( $( this ).val() );
});


function GET(param){
    var sel = $("#textarea").getSelection().text;
    fetch('http://localhost:8080/' + encodeURIComponent(sel) + "/?transforms=" + param)
        .then(function(response) {
            return response.text();
        })
        .then(function(text) {
            $("#textarea").replaceSelectedText(text);
        });
}


function formatBlank() {
    var wholeText = document.getElementById("textarea").value;
    fetch('http://localhost:8080/' + encodeURIComponent(wholeText) + "/?transforms=formatBlankSigns", { mode: "cors"})
        .then(function(response) {
            return response.text();
        })
        .then(function(text) {
            document.getElementById("textarea").value = text;
        });
}


// checkboxes params
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


// add listeners to checkboxes
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


var langnames = ["Afrikaans", "Albanian", "Amharic", "Arabic", "Armenian", "Azerbaijani", "Basque", "Belarusian",
    "Bengali", "Bosnian", "Bulgarian", "Catalan", "Cebuano", "Chichewa", "Chinese (Simplified)",
    "Chinese (Traditional)", "Corsican", "Croatian", "Czech", "Danish", "Dutch", "English", "Esperanto", "Estonian",
    "Filipino", "Finnish", "French", "Frisian", "Galician", "Georgian", "German", "Greek", "Gujarati", "Haitian Creole",
    "Hausa", "Hawaiian", "Hebrew", "Hindi", "Hmong", "Hungarian", "Icelandic", "Igbo", "Indonesian", "Irish", "Italian",
    "Japanese", "Javanese", "Kannada", "Kazakh", "Khmer", "Korean", "Kurdish (Kurmanji)", "Kyrgyz", "Lao", "Latin",
    "Latvian", "Lithuanian", "Luxembourgish", "Macedonian", "Malagasy", "Malay", "Malayalam", "Maltese", "Maori",
    "Marathi", "Mongolian", "Myanmar (Burmese)", "Nepali", "Norwegian", "Pashto", "Persian", "Polish", "Portuguese",
    "Punjabi", "Romanian", "Russian", "Samoan", "Scots Gaelic", "Serbian", "Sesotho", "Shona", "Sindhi", "Sinhala",
    "Slovak", "Slovenian", "Somali", "Spanish", "Sundanese", "Swahili", "Swedish", "Tajik", "Tamil", "Telugu", "Thai",
    "Turkish", "Ukrainian", "Urdu", "Uzbek", "Vietnamese", "Welsh", "Xhosa", "Yiddish", "Yoruba", "Zulu"];

var langcodes = ["af", "sq", "am", "ar", "hy", "az", "eu", "be", "bn", "bs", "bg", "ca", "ceb", "ny", "zh", "zh-TW", "co",
    "hr", "cs", "da", "nl", "en", "eo", "et", "tl", "fi", "fr", "fy", "gl", "ka", "de", "el", "gu", "ht", "ha", "haw", "iw",
    "hi", "hmn", "hu", "is", "ig", "id", "ga", "it", "ja", "jw", "kn", "kk", "km", "ko", "ku", "ky", "lo", "la", "lv", "lt",
    "lb", "mk", "mg", "ms", "ml", "mt", "mi", "mr", "mn", "my", "ne", "no", "ps", "fa", "pl", "pt", "pa", "ro", "ru",
    "sm", "gd", "sr", "st", "sn", "sd", "si", "sk", "sl", "so", "es", "su", "sw", "sv", "tg", "ta", "te", "th", "tr",
    "uk", "ur", "uz", "vi", "cy", "xh", "yi", "yo", "zu"];


function createSelect(id) {
    // get select with id
    var select = document.getElementById(id);
    var option;

    for(i = 0; i < langcodes.length; i++){

        // create single option
        option = document.createElement("option");
        option.text = langnames[i];
        option.value = langcodes[i];

        // add option to select
        select.add(option);
    }
}


function translate(){
    GET("translate," + document.getElementById("lang").value);
}


var lastSearchIndices = [];
var lastSearchStr;
var isReplace = false;

function replace(){
    var input = document.getElementById("find");

    if(isReplace === false){
        input.value = "";
        input.placeholder = "replace...";
        isReplace = true;
    }
    else{
        var $textarea = $('#textarea');

        for(i = 0; i < lastSearchIndices.length; i++){
            $textarea.setSelection(lastSearchIndices[i], lastSearchIndices[i] + lastSearchStr.length);
            $textarea.replaceSelectedText(input.value);
        }

        input.value = "";
        input.placeholder ="find...";
        isReplace = false;
        document.getElementById("hl1").innerHTML = "";
    }
}

function find() {
    if(isReplace === false){
        var input = document.getElementById("find");
        lastSearchStr = input.value;
        var highlights = document.getElementById("hl1");
        highlights.innerHTML = "";

        if(lastSearchStr !== ""){
            var str = document.getElementById("textarea").value;

            lastSearchIndices =  getIndicesOf(lastSearchStr, str);
            if(lastSearchIndices.length > 0)
                highlights.innerHTML = applyHighlights(str, lastSearchStr);
        }
    }
}

function applyHighlights(text, replaceStr){
    return text
        .replace(new RegExp(replaceStr, "ig"), '<mark>$&</mark>');
}


function getIndicesOf(searchStr, str) {
    var searchStrLen = searchStr.length;
    var indices = [];

    if(str.length > 0){
        var startIndex = 0, index;
        while((index = str.indexOf(searchStr, startIndex)) > -1){
            indices.push(index);
            startIndex = index + searchStrLen;
        }
    }
    return indices;
}

