Ext.define('YesdoApp.model.User', {
	extend: 'YesdoApp.model.Base',

	fields: [
		{name: 'login', type: 'string'},
		{name: 'permissions', useNull: true},
		{name: 'merchantId', reference: 'Merchant', allowBlank: false }
	],

	validators: {
		login: { type: 'length', min: 6, max: 16 }
	}
});
