Ext.define('YesdoApp.plugin.Access', {
	extend: 'Ext.AbstractPlugin',
	alias: 'plugin.access',

	requires: [
		'YesdoApp.Config'
	],

	defaultConfig: {},

	init: function (target) {

		target.on(
			'render',
			this.onRender,
			this,
			{single: true}
		);
	},

	onRender: function (target) {
		this.performAccess(target);
	},

	performAccess: function (target) {
		var access = target.access = target.access || this.defaultConfig;
		if (Ext.isEmpty(access)) {
			return;
		}
		if (Ext.isString(access)) {
			if (!YesdoApp.Config.hasRole(access)) {
				target.hide();
			} else {
				target.show();
			}
		}
	}
});