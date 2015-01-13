Ext.define('YesdoApp.view.login.LoginController', {
	extend: 'Ext.app.ViewController',
	alias: 'controller.login',

	loginText: 'Аутентификация...',

	constructor: function () {
		this.callParent(arguments);

		this.loginManager = new YesdoApp.LoginManager({
			session: this.session,
			model: 'YesdoApp.model.User'
		});
	},

	onSpecialKey: function(field, e) {
		if (e.getKey() === e.ENTER) {
			this.doLogin();
		}
	},

	onLoginClick: function() {
		this.doLogin();
	},

	doLogin: function() {
		var form = this.lookupReference('form');

		if (form.isValid()) {
			Ext.getBody().mask(this.loginText);

			this.loginManager.login({
				data: form.getValues(),
				scope: this,
				success: 'onLoginSuccess',
				failure: 'onLoginFailure'
			});
		}
	},

	onLoginFailure: function(response) {
		var err = Ext.decode(response.responseText),
			statusBar = this.getView().down('statusbar');
		Ext.getBody().unmask();

		statusBar.setStatus({text: err.message, iconCls: 'x-status-error'});
	},

	onLoginSuccess: function(user) {
		Ext.getBody().unmask();
		this.fireViewEvent('login', this.getView(), user, this.loginManager);
	}

	//onLoginClick: function(){
	//
	//	// This would be the ideal location to verify the user's credentials via
	//	// a server-side lookup. We'll just move forward for the sake of this example.
	//
	//	// Set the localStorage value to true
	//	localStorage.setItem("TutorialLoggedIn", true);
	//
	//	// Remove Login Window
	//	this.getView().destroy();
	//
	//	// Add the main view to the viewport
	//	Ext.widget('app-main');
	//}
});