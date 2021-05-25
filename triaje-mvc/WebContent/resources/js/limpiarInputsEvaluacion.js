window.addEventListener("load", function(){
    var inputs = document.getElementsByClassName("short")
    for(let i=0; i<inputs.length; i++){
        inputs[i].value = "";
    }
})