Ext.define("YesdoApp.view.login.Login",{
	extend: 'Ext.window.Window',
	alias: 'widget.login',

	requires: [
		'Ext.tip.QuickTipManager',
		'Ext.ux.statusbar.StatusBar',
		'Ext.ux.statusbar.ValidationStatus'
	],

	viewModel: 'login',

	controller: 'login',
	bodyPadding: 10,
	title: 'Аутентификация',
	closable: false,

	cls: 'login',

	items: {
		xtype: 'form',
		reference: 'form',
		id: 'login-form',
		width: 300,
		defaults: {
			anchor: '95%',
			allowBlank: false,
			selectOnFocus: true
		},
		items: [
			{
				xtype: 'textfield',
				name: 'username',
				bind: '{username}',
				fieldLabel: 'Логин',
				allowBlank: false,
				enableKeyEvents: true,
				listeners: {
					specialKey: 'onSpecialKey'
				}
			},
			{
				xtype: 'textfield',
				name: 'password',
				inputType: 'password',
				fieldLabel: 'Пароль',
				allowBlank: false,
				minLength: 4,
				enableKeyEvents: true,
				cls: 'password',
				listeners: {
					specialKey: 'onSpecialKey'
				}
			}
		]
	},

	dockedItems: [
		{
			xtype: 'toolbar',
			dock: 'bottom',
			ui: 'footer',
			items: [
				'->',
				{
					text: 'Вход',
					listeners: {
						click: 'onLoginClick'
					}
				}
			]
		},
		{
			xtype: 'statusbar',
			dock: 'bottom',
			defaultText: 'Ready',
			plugins: Ext.create('Ext.ux.statusbar.ValidationStatus', {form:'login-form'})
		}
	]
});