Ext.define('YesdoApp.view.user.User', {
	extend: 'Ext.window.Window',

	alias: 'widget.user',
	controller: 'users',
	plugins: 'access', access: 'MANAGE_USERS',
	//width: 360,
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
					bind: {
						value: '{theUser.login}',
						readOnly: '{!phantom}'
					},
					publishes: ['value']
				},
				{
					xtype: 'textfield',
					fieldLabel: 'Пароль',
					inputType: 'password',
					minLength: 6,
					//hidden: true,
					name: 'password',
					bind: {
						hidden: '{!phantom}'
					}
				},
				{
					fieldLabel: 'merchant',
					xtype: 'combo',
					name: 'merchantId',
					emptyText: 'empty',
					editable: false,
					allowBlank: false,
					displayField: 'name',
					valueField: 'id',
					bind: {
						value: '{theUser.merchant.id}',
						readOnly: '{!phantom}'
					},
					store: {
						model: 'YesdoApp.model.Merchant',
						autoLoad: true
					}
				},
				{
					xtype: 'itemselectorfield',
					bind: {
						value: '{theUser.permissions}'
					},
					height: 200,
					width: 700,
					buttons: ['add', 'remove'],
					fieldLabel: 'permissions',
					//store: 'Permissions',
					store: {
						model: 'YesdoApp.model.Permission',
						proxy: {
							type: 'ajax',
							url: 'user/permissions',
							reader: {
								type: 'array'
							}
						},
						autoLoad: true
					},
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
