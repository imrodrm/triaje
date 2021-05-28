$(document).ready(function() {
    var dt = $('#tablaEnfermedades').DataTable( {
        "processing": true,
        "language": {
            "lengthMenu": "Mostrar _MENU_ entradas por página",
            "zeroRecords": "No se ha encontrado nada",
            "info": "Mostrando página _PAGE_ de _PAGES_",
            "infoEmpty": "No se ha encontrado nada",
            "infoFiltered": "(Filtrado de un total de _MAX_ entradas)",
            "search": "Búsqueda: ",
            "paginate": {
            	"first": "Primera",
            	"last": "Última",
            	"next": "Siguiente",
            	"previous": "Anterior"
            }
        },
        "order": [[0, 'asc']]
    } );
})