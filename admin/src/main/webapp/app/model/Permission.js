Ext.define('YesdoApp.model.Permission', {
	extend: 'Ext.data.Model',

	requires: [
		'Ext.data.validator.*',
		'Ext.data.proxy.Rest'
	],

	fields: [
		{name: 'name', type: 'string'},
		{name: 'value', type: 'string'}
	]
});
