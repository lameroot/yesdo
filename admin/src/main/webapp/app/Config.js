Ext.define('YesdoApp.Config', {
	singleton: true,

	get: function (attribute) {
		return App.config[attribute];
	},

	hasRole: function (role) {
		if (Ext.isDefined(App.config.permissions)) {
			return Ext.Array.contains(App.config.permissions, role);
		}
		return false;
	}
});