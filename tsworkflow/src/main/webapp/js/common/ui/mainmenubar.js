/**
 * 
 */

;
(function($, window, document, undefined) {
	var pluginName = "mainMenuBar";
	var defaults = {
		title : "",
		greeting : "",
		user : "",
		menuname : "",
		mopt : "",
		websocket : "",
	};

	var MainMenuBar = function(element, options) {
		this.element = element;
		this.options = $.extend({
			title : "",
			greeting : "",
			user : "",
			menuname : "",
			mopt : "",
			websocket : "",
		}, defaults, options);
		this._defaults = defaults;
		this._name = pluginName;
		this.topparent;
		this.title = options.title;
		this.init(options);
		this.manager = null;
		this.timer = 0;
		this.msg = null;
		this.socket = this.options.websocket;
		this.counting = 0;
	};

	MainMenuBar.prototype.init = function(options) {
		var menuNav = document.createElement("NAV");
		menuNav.className = "navbar navbar-default navbar-static-top";
		this.element.appendChild(menuNav);

		var mainbar = document.createElement("DIV");
		mainbar.className = "container-fluid";
		menuNav.appendChild(mainbar);

		var barheader = document.createElement("DIV");
		barheader.className = "navbar-header";
		mainbar.appendChild(barheader);

		var headerButton = document.createElement("button");
		headerButton.className = "navbar-toggle collapsed";
		headerButton.type = "button";
		headerButton.className = "navbar-toggle collapsed";
		headerButton.setAttribute("data-toggle", "collapse");
		headerButton.setAttribute("data-target", "#navbar");
		headerButton.setAttribute("aria-expanded", "false");
		headerButton.setAttribute("aria-controls", "navbar");

		barheader.appendChild(headerButton);

		var headerSpan = document.createElement("span");
		headerSpan.className = "sr-only";
		headerButton.appendChild(headerSpan);
		headerSpan = document.createElement("span");
		headerSpan.className = "icon-bar";
		headerButton.appendChild(headerSpan);
		headerSpan = document.createElement("span");
		headerSpan.className = "icon-bar";
		headerButton.appendChild(headerSpan);
		headerSpan = document.createElement("span");
		headerSpan.className = "icon-bar";
		headerButton.appendChild(headerSpan);

		var titleA = document.createElement("a");
		titleA.className = "navbar-brand";
		titleA.setAttribute("href", "http://www.xuanqiyun.com");
		titleA.setAttribute("target", "_blank");
		titleA.innerHTML = "<strong>" + this.title + "</strong>";
		barheader.appendChild(titleA);

		var navbar = document.createElement("DIV");
		navbar.id = "navbar";
		navbar.className = "navbar-collapse collapse";
		mainbar.appendChild(navbar);

		var barUL = document.createElement("ul");
		barUL.className = "nav navbar-nav";
		navbar.appendChild(barUL);

		// 添加主菜单项。
		this.addMenuItems(barUL, options);

		var barRightUL = document.createElement("ul");
		barRightUL.className = "nav navbar-nav navbar-right";
		navbar.appendChild(barRightUL);

		var titleB = document.createElement("a");
		barRightUL.appendChild(titleB);
		titleB.className = "navbar-brand";
		titleB.setAttribute("href", "javascript:void(0);");

		var greetingFont = document.createElement("FONT");
		titleB.appendChild(greetingFont);
		greetingFont.style.fontSize = "16px";
		greetingFont.innerHTML = options.greeting;


		var barLiItem = document.createElement("li");
		barRightUL.appendChild(barLiItem);
		barLiItem.className = "dropdown";

		var barLiItemA = document.createElement("A");
		barLiItem.appendChild(barLiItemA);
		barLiItemA.setAttribute("href", "#");
		barLiItemA.className = "dropdown-toggle";
		barLiItemA.setAttribute("data-toggle", "dropdown");
		barLiItemA.setAttribute("role", "button");
		barLiItemA.setAttribute("aria-haspopup", "true");
		barLiItemA.setAttribute("aria-expanded", "false");

		var otherFont = document.createElement("FONT");
		barLiItemA.appendChild(otherFont);
		otherFont.style.fontSize = "16px";
		otherFont.innerHTML = "其他";

		var otherSPAN = document.createElement("SPAN");
		barLiItemA.appendChild(otherSPAN);
		otherSPAN.className = "caret";

		var barLiItemUl = document.createElement("UL");
		barLiItem.appendChild(barLiItemUl);
		barLiItemUl.className = "dropdown-menu";

		for (var i = 0; i < otherComponents.length; i++) {
			this.createDropdownItem(otherComponents[i].name, barLiItemUl,
					otherComponents[i].url);
		}

		this.createMenuabarItem("logout", barRightUL,
				"glyphicon glyphicon-log-out", "退出", 1,
				"/login/logout.jsp?usr=" + options.user);

	};

	MainMenuBar.prototype.addMenuItems = function(barUL, options) {
		this.newItem = this.createMenuabarItem("newmodel", barUL,
				"glyphicon glyphicon-plus", "新建", 1, "javascript:void(0);");
		this.newItem.className = "disabled";
		if (options.mopt != undefined) {
			if (options.mopt[0] == "0") {
				this.newItem.style.display = "none";
			} else {
				this.newItem.style.display = "";
			}
		}

		this.saveItem = this.createMenuabarItem("saveG", barUL,
				"glyphicon glyphicon-floppy-disk", "保存", 1,
				"javascript:void(0);");
		this.saveItem.className = "disabled";
		if (options.mopt != undefined) {
			if (options.mopt[1] == "0") {
				this.saveItem.style.display = "none";
			} else {
				this.saveItem.style.display = "";
			}
		}

		this.helpItem = this.createMenuabarItem("helpG", barUL,
				"glyphicon glyphicon-question-sign", "帮助", 1,
				"javascript:void(0);");
	};

	MainMenuBar.prototype.createDropdownItem = function(name, parent, url) {
		var barLiItemLi = document.createElement("li");
		parent.appendChild(barLiItemLi);
		var barLiItemLiA = document.createElement("A");
		barLiItemLi.appendChild(barLiItemLiA);
		barLiItemLiA.setAttribute("href", url);
		barLiItemLiA.setAttribute("target", "_blank");
		barLiItemLiA.innerHTML = name;
	};

	MainMenuBar.prototype.createMenuItem = function (id, parent, icon, title,
														type, url) {
		var barLiItem = document.createElement("li");
		barLiItem.name = "li" + id;
		parent.appendChild(barLiItem);
		var liA = document.createElement("a");
		liA.setAttribute("href", url);
		barLiItem.appendChild(liA);
		barLiItem.name = "a" + id;

		if (type == 1) { // glyphicon
			var iconSpan = document.createElement("span");
			liA.appendChild(iconSpan);
			iconSpan.className = icon;
			iconSpan.name = "ic" + id;
			iconSpan.addEventListener("click", this, false);
			var itemTitle = document.createElement("span");
			liA.appendChild(itemTitle);
			itemTitle.addEventListener("click", this, false);
			itemTitle.style.fontSize = "16px";
			itemTitle.name = "tl" + id;
			itemTitle.innerHTML = " " + title;
		} else if (type == 2) { // font-awesome icon
			var iconI = document.createElement("i");
			liA.appendChild(iconI);
			iconI.addEventListener("click", this, false);
			iconI.className = icon;
			iconI.name = "ic" + id;
			var itemTitle = document.createElement("span");
			liA.appendChild(itemTitle);
			itemTitle.addEventListener("click", this, false);
			itemTitle.style.fontSize = "16px";
			itemTitle.innerHTML = " " + title;
			itemTitle.name = "tl" + id;

			this.redDot = document.createElement("span");
			liA.appendChild(this.redDot);
			this.redDot.id = "redDot" + id;
			this.redDot.style.position = "absolute";
			this.redDot.style.left = "72px";
			this.redDot.style.top = "12px";
			this.redDot.style.width = "10px";
			this.redDot.style.height = "10px";
			this.redDot.style.background = "#ff4039";
			this.redDot.style.borderRadius = "50%";
			this.redDot.style.display = "none";
		}
		return barLiItem;
	};

	MainMenuBar.prototype.unReadShow = function(isShowCount) {
		if(isShowCount == 0){
			this.redDot.style.display = "none";
		}else{
			this.redDot.style.display = "block";
		}
	};

	var isShow = false;
	MainMenuBar.prototype.allReceiveMsg = function(data) {
		if(isShow){
			this.redDot.style.display = "none";
		}else{
			this.redDot.style.display = "block";
		}
	};

	MainMenuBar.prototype.isShow = function() {
		isShow = false;
	};

	MainMenuBar.prototype.createMenuabarItem = function(id, parent, icon,
			title, type, url) {
		var barLiItem = document.createElement("li");
		barLiItem.id = id;
		parent.appendChild(barLiItem);
		var liA = document.createElement("a");
		liA.setAttribute("href", url);
		barLiItem.appendChild(liA);

		if (type == 1) { // glyphicon
			var iconSpan = document.createElement("span");
			iconSpan.className = icon;
			iconSpan.addEventListener("click", this, false);
			liA.appendChild(iconSpan);
			var itemTitle = document.createElement("span");
			itemTitle.style.fontSize = "16px";
			itemTitle.addEventListener("click", this, false);
			itemTitle.innerHTML = " " + title;
			liA.appendChild(itemTitle);
		} else if (type == 2) { // font-awesome icon
			var iconI = document.createElement("i");
			iconI.addEventListener("click", this, false);
			iconI.className = icon;
			liA.appendChild(iconI);
			var itemTitle = document.createElement("span");
			itemTitle.addEventListener("click", this, false);
			itemTitle.style.fontSize = "16px";
			itemTitle.innerHTML = " " + title;
			liA.appendChild(itemTitle);
		}
		return barLiItem;
	}

	MainMenuBar.prototype.handleEvent = function(e) {
		switch (e.type) {
		case "click":
			this.doClick(e);
			break;
		}
	};

	MainMenuBar.prototype.setManager = function(manager) {
		this.manager = manager;
	};

	MainMenuBar.prototype.doClick = function(evt) {
		var t = evt.target;
		if ((t.tagName == "LI" && t.id == "newmodel" && t.className == "")
				|| (t.tagName == "A" && t.parentElement.id == "newmodel" && t.parentElement.className == "")
				|| (t.tagName == "SPAN"
						&& t.parentElement.parentElement.id == "newmodel" && t.parentElement.parentElement.className == "")) {
			if (this.manager != null
					&& this.manager.doCreateNewAction != undefined) {
				this.manager.doCreateNewAction();
			}
		} else if ((t.tagName == "LI" && t.id == "saveG" && t.className == "")
				|| (t.tagName == "A" && t.parentElement.id == "saveG" && t.parentElement.className == "")
				|| (t.tagName == "SPAN"
						&& t.parentElement.parentElement.id == "saveG" && t.parentElement.parentElement.className == "")) {
			if (this.manager != null && this.manager.doSaveAction != undefined) {
				this.manager.doSaveAction();
			}
		}
	};

	$.fn[pluginName] = function(options) {
		return this.each(function() {
			if (!$.data(this, pluginName)) {
				$.data(this, pluginName, new MainMenuBar(this, options));
			} else if ($.isFunction(Plugin.prototype[options])) {
				$.data(this, pluginName)[options]();
			}
		});
	};

})(jQuery, window, document);