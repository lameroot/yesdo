Ext.define('YesdoApp.store.Permissions', {
	extend: 'Ext.data.Store',

	fields: ['value', 'name'],
	proxy: {
		type: 'ajax',
		url: 'user/permissions',
		reader: {
			type: 'array'
		}
	},
	autoLoad: true
});