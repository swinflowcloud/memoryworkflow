/**
 * 
 */

function ADMINService() {
	this.admin = "http://localhost:8080/tsworkflow/service13";
	this.svr = "http://localhost:8080/tsworkflow/service11";
	this.init();
};

ADMINService.prototype = {
	init : function() {

	},
	api : function(n) {
		url = this.admin + "/api" + n;
		return url;
	},
	api1 : function(n) {
		url = this.svr + "/api" + n;
		return url;
	},
};
