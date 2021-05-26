
$(document).ready(function() {
    var dt = $('#tablaEvaluaciones').DataTable( {
        "processing": true,
        "columns": [
            {
                "class":          "details-control",
                "orderable":      false,
                "data":           null,
                "defaultContent": ""
            },
            { "data": "IDevaluacion" },
            { "data": "Fecha y hora" },
            { "data": "ID evaluador" },
            { "data": "Nombre paciente" },
			{ "data": "Prioridad" },
			{ "data": "Temperatura" },
			{ "data": "Altura" },
			{ "data": "Peso" },
        ],
        "order": [[1, 'asc']]
    } );
 
    // Array to track the ids of the details displayed rows
    var detailRows = [];
 
    $('#tablaEvaluaciones tbody').on( 'click', 'tr td.details-control', function () {
        var tr = $(this).closest('tr');
        var row = dt.row( tr );
        var idx = $.inArray( tr.attr('IDevaluacion'), detailRows );
		var idEv = tr.find("td").eq(1).text();
		console.log(idEv)
 
        if ( row.child.isShown() ) {
            tr.removeClass( 'details' );
            row.child.hide();
 
            // Remove from the 'open' array
            detailRows.splice( idx, 1 );
        }
        else {
            tr.addClass( 'details' );
			$.ajax({
				url: "verDolencia",
        		method: "POST",
        		dataType:'json',
        		data: {
					id: idEv,
        		}
    		}).then(function(data){
				row.child('Dolencia: ' + data).show();
			})
    		.catch(function(resp, msg, ex){
				console.log("Error (" + resp.status + "): " + msg);
    		});
 
            // Add to the 'open' array
            if ( idx === -1 ) {
                detailRows.push( tr.attr('id') );
            }
        }
    } );
 
    // On each draw, loop over the `detailRows` array and show any child rows
    dt.on( 'draw', function () {
        $.each( detailRows, function ( i, id ) {
            $('#'+id+' td.details-control').trigger( 'click' );
        } );
    } );
} );
