Ext.define('YesdoApp.view.user.User', {
	extend: 'Ext.window.Window',

	alias: 'widget.user',
	controller: 'users',

	width: 360,
	//minHeight: 250,

	bodyPadding: 5,
	layout: {
		type: 'vbox',
		align: 'stretch'
	},

	bind: 'Пользователь: {theUser.login}',

	modal: true,

	items: [
		{
			xtype: 'form',
			//height: 400,
			defaults: {
				width: 340
			},
			items: [
				{
					xtype: 'textfield',
					fieldLabel: 'Логин',
					allowBlank: false,
					bind: '{theUser.login}',
					publishes: ['value']
				},
				{
					xtype: 'textfield',
					fieldLabel: 'Пароль',
					inputType: 'password',
					minLength: 6,
					//hidden: true,
					name: 'password'
				},
				{
					xtype: 'itemselectorfield',
					name: 'permissions',
					height: 200,
					width: 700,
					buttons: [ 'add', 'remove'],
					fieldLabel: 'permissions',
					store: 'Permissions',
					displayField: 'name',
					valueField: 'value',
					allowBlank: true,
					fromTitle: ('accessiblePermissions'),
					toTitle: ('installedPermissions')
				}
			],

			dockedItems: [
				{
					xtype: 'toolbar',
					dock: 'bottom',
					ui: 'footer',
					items: [
						{
							text: 'Сохранить',
							formBind: true,
							disabled: true,
							listeners: {
								click: 'onSaveClick'
							}
						},
						'->',
						{
							text: 'Закрыть',
							listeners: {
								click: 'closeView'
							}
						}
					]
				}
			]
		}
	]
});
