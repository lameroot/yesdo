Ext.define('YesdoApp.model.User', {
	extend: 'YesdoApp.model.Base',

	fields: [
		{name: 'login', type: 'string'},
		{name: 'permissions', useNull: true},
		{name: 'merchant', persist: false }
	],

	validators: {
		login: { type: 'length', min: 6, max: 16 }
	}
});
