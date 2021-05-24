window.addEventListener("load", function(){
	var modal = document.getElementById("myModal");
	console.log(modal);
	var small = document.getElementById("enfermedades");
	console.log(small);
	var span = document.getElementById("close");
	console.log(span);
	small.onclick = function(){
	    modal.style.display = "block";
	}
	
	span.onclick = function() {
	    modal.style.display = "none";
	}
  
	window.onclick = function(event) {
	    if (event.target == modal) {
      		modal.style.display = "none";
    	}
	}
})
