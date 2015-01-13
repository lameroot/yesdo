Ext.define('YesdoApp.view.user.Users', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.users',

	controller: 'users',

	bind: '{users}',
	reference: 'userGrid',
	viewModel: {
		type: 'users'
	},
	tbar: [
		{
			text: 'Добавить',
			handler: 'onAddUser'
		}
	],
	listeners: {
		scope: 'controller',
		itemdblclick: 'onEditUser'
	},
	columns: [
		{
			text: 'ID',
			dataIndex: 'id'
		},
		{
			text: 'Логин',
			dataIndex: 'login',
			width: 180
		}
	]
});