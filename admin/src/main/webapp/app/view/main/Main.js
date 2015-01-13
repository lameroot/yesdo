/**
 * This class is the main view for the application. It is specified in app.js as the
 * "autoCreateViewport" property. That setting automatically applies the "viewport"
 * plugin to promote that instance of this class to the body element.
 * TODO - Replace this content of this view to suite the needs of your application.
 */
Ext.define('YesdoApp.view.main.Main', {
	extend: 'Ext.container.Viewport',

	alias: 'widget.main',
	plugins: 'viewport',
	requires: [
		'YesdoApp.view.ContentPanel'
	],

	controller: 'main',
	viewModel: {
		type: 'main'
	},

	layout: {
		type: 'border'
	},

	items: [
		{
			xtype: 'toolbar',
			id: 'app-header',
			region: 'north',
			style: {
				backgroundColor: 'rgb(43, 150, 228)'
			},
			items: [
				{
					text: 'Документы',
					href: '#documentsearch',
					hrefTarget: '_self'
				},
				{
					text: 'Создать документ',
					handler: 'onDocumentCreate'
				},
				{
					text: 'Отчеты',
					menu: {
						items: [
							{
								text: 'Отчет',
								href: '#report'
							}
						]
					}
				},
				{
					text: 'Администрирование',
					menu: {
						items: [
							{
								text: 'Пользователи',
								href: '#users'
							},
							{
								text: 'Настройки',
								href: '#settings'
							}
						]
					}
				},
				'->',
				{
					xtype: 'tbtext',
					id: 'app-header-username',
					style: 'color: #fff;font-size: 14px; font-weight: bold;',
					bind: '{currentUser.login}',
					listeners: {
						click: 'onClickUserName',
						element: 'el'
					},
					margin: '0 10 0 0'
				},
				{
					text: 'Выход',
					handler: 'onClickLogout'
				}
			]
		},
		{
			region: 'center',
			xtype: 'contentpanel'
		}
	]
});
