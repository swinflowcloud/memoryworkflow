/**
 * 
 */

function PMService() {
	this.pm = "http://localhost:8080/tsworkflow/service1";
	this.pm1 = "http://localhost:8080/tsworkflow/service11";
	this.init();
};

PMService.prototype = {
	init : function() {
	},
	api : function(n) {
		url = this.pm + "/api" + n;
		return url;
	},
	api1 : function(n) {
		url = this.pm1 + "/api" + n;
		return url;
	},
};
