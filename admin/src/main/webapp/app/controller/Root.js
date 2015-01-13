/**
 * The main application controller. This is a good place to handle things like routes.
 */
Ext.define('YesdoApp.controller.Root', {
	extend: 'Ext.app.Controller',

	views: [
		'YesdoApp.view.main.Main'
	],

	models: ['User'],

	routes: {
		'login': {
			action: 'showLogin'
		},
		'#': {
			action: 'showUI'
		},
		':view': {
			action: 'onNavigate',
			before: 'beforeNavigate'
		}
	},

	listen: {
		controller: {
			'#': {
				unmatchedroute: 'onUnmatchedRoute'
			}
		}
	},


	onLaunch: function () {
		this.session = new Ext.data.Session();
		Ext.Ajax.on({
			requestexception: this.onRequestException,
			scope: this
		});
		//this.checkUser();

		if (App.config.loggedIn) {
			this.redirectTo('#');
		} else {
			this.redirectTo('login');
		}
	},

	onRequestException: function (conn, response, options) {
		if (response.status == 401 && options.url !== 'login') {
			this.logout();
		}
	},

	logout: function () {
		Ext.destroy(this.login, this.viewport, this.session);

		Ext.Ajax.request({
			url: 'logout',
			method: 'POST',
			async: false,
			scope: this,
			callback: function () {
				this.redirectTo('login');
				window.location.reload();
			}
		});
	},

	showLogin: function () {
		var session = this.session;

		Ext.destroy(this.login, this.viewport, this.session);
		delete this.viewport;
		delete this.login;

		Ext.each(Ext.ComponentQuery.query('window'), function (window) {
			if (!window.isHidden()) {
				window.close();
			}
		});
		Ext.each(Ext.ComponentQuery.query('tooltip'), function (tip) {
			tip.hide();
		});

		this.login = Ext.widget('login',{
			session: session,
			autoShow: true,
			listeners: {
				scope: this,
				login: 'onLogin'
			}
		});
	},

	onLogin: function (loginController, user, loginManager) {
		this.login.destroy();
		delete this.login;

		this.loginManager = loginManager;
		this.user = user;

		this.redirectTo('#');
		window.location.reload();
	},

	showUI: function () {
		this.viewport = Ext.widget('main',{
			session: this.session,
			viewModel: {
				data: {
					currentUser: Ext.create('YesdoApp.model.User',{
						id: App.config.id,
						login: App.config.login,
						permissions: App.config.permissions
					})
				}
			}
		});
	},

	getSession: function () {
		return this.session;
	},

	beforeNavigate: function (view, action) {
		if ('login' == view) {
			action.stop();
		}
		else {
			if (this.viewport == null) {
				this.showUI();
			}

			if (Ext.isEmpty(Ext.ClassManager.getNameByAlias('widget.' + view))) {
				action.stop();
			} else {
				action.resume();
			}
		}
	},

	onNavigate: function (view) {
		var view = Ext.widget(view, {
				session: this.session
			}),
			panel = Ext.getCmp('content-panel');

		Ext.each(Ext.ComponentQuery.query('window'), function (window) {
			if (!window.isHidden()) {
				window.close();
			}
		});
		Ext.each(Ext.ComponentQuery.query('tooltip'), function (tip) {
			tip.hide();
		});

		panel.removeAll();

		panel.add(view);
	},

	onUnmatchedRoute: function (hash) {
		console.log('Unmatched', hash);
	}
});
