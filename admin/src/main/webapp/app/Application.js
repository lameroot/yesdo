/**
 * The main application class. An instance of this class is created by app.js when it calls
 * Ext.application(). This is the ideal place to handle application launch and initialization
 * details.
 */
Ext.define('YesdoApp.Application', {
	extend: 'Ext.app.Application',

	name: 'YesdoApp',
	//defaultToken: 'login',

	requires: [
		'Ext.state.CookieProvider',
		'Ext.tip.QuickTipManager',
		'Ext.app.bindinspector.*',
		'YesdoApp.LoginManager',
		'YesdoApp.Format',
		'YesdoApp.view.*',
		'YesdoApp.plugin.Access'
	],

	views: [
		'YesdoApp.view.login.Login',
		'YesdoApp.view.main.Main'
	],

	controllers: [
		'Root'
	],

	launch: function () {
		Ext.tip.QuickTipManager.init();
	}
});
