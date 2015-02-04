Ext.define('YesdoApp.model.Base', {
	extend: 'Ext.data.Model',

	requires: [
		'Ext.data.validator.*',
		'Ext.data.proxy.Rest'
	],

	fields: [
		{name: 'id', type: 'int', persist: false}
	],

	schema: {
		namespace: 'YesdoApp.model',
		proxy: {
			type: 'rest',
			paramsAsJson: true,
			url: '{prefix}/{entityName:uncapitalize}',
			pageParam: '',
			startParam: '',
			limitParam: ''
		},
		urlPrefix: '/admin'
	}
});
