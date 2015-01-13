Ext.define('YesdoApp.view.user.UsersModel', {
	extend: 'Ext.app.ViewModel',
	alias: 'viewmodel.users' ,

	stores: {
		users: {
			model: 'User',

			autoLoad: true,

			autoSync: true
		}
	}
});