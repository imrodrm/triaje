window.addEventListener('load', function(){
    $('#nombre').on('keyup', function(){

        function timer(){
            input = document.getElementById('nombre')
            console.log(input.value)
            $.ajax({
                url: "buscarPacientes",
                method: "POST",
                dataType:'json',
                data: {
                    nombre: input.value,
                }
            }).then(muestraPacientes)
            .catch(function(resp, msg, ex){
                console.log("Error (" + resp.status + "): " + msg);
            });
        }
    
        setTimeout(timer, 1000)
    })
})


function muestraPacientes(data){
    var divPadre = document.getElementById('pacientesSugerencias')
    var tamanio = data.length > 5 ? 5 : data.length
    var pacientesSelect = '<select onchange="rellenaInputs(this)" size="' + tamanio + '">'
    pacientesSelect+='<option value="">-Elija-</option>'
    for(let i = 0; i < data.length; i++){
        pacientesSelect += '<option class="inputTexto" value="' + data[i] + '">' + data[i] + '</option>'
    }
    pacientesSelect += '</select>'
    divPadre.innerHTML = pacientesSelect;
}

function rellenaInputs(sel) {
	inputNombre = document.getElementById("nombre");
	inputDomicilio = document.getElementById("domicilio");
	console.log(inputNombre)
	console.log(inputDomicilio)
	if(sel.value !=''){
		var split = sel.value.split("-")
		inputNombre.value = split[0];
		inputDomicilio.value = split[1];
	} else{
		inputNombre.value = ''
		inputDomicilio.value = ''
	}
}