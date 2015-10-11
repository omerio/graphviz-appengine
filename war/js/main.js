$(document).ready(function() {
	
	$('.js-render').click(function (e) {
	  
	  var dot = $('.js-dot-source').val();
	  
	  $.ajax({
		  url: "/dotgraphicstest",
		  data: { 'dot' : dot },
		  type: "POST",
		  success: function(data) {
			  var graph = $(".js-svg-graph");
			  graph.empty();
			  var importedSVGRootElement = document.importNode(data.documentElement,true);
			  //append the imported SVG root element to the appropriate HTML element
			  graph.append(importedSVGRootElement);
			  $("svg").graphviz({statusbar: false});
		  }//,
		  //dataType: "application/svg+xml"
	  });
	  

	  e.preventDefault();
	  
	});

});