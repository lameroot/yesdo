Ext.define('YesdoApp.view.user.UserController', {
	extend: 'Ext.app.ViewController',
	alias: 'controller.users',
	requires: [
		'Ext.window.Toast',
		'YesdoApp.model.Permission',
		'YesdoApp.model.Merchant'
	],


	onAddUser: function () {
		var user;

		user = Ext.create('YesdoApp.model.User', {
		});

		this.editUser(user);
	},

	onEditUser: function (ctrl, record) {
		this.editUser(record);
	},

	editUser: function (userRecord) {
		var win = Ext.widget('user', {
			viewModel: {
				data: {
					theUser: userRecord,
					phantom: userRecord.phantom
				},
				stores: {
					users: this.getStore('users')
				}
			}
		});

		win.show();
	},

	onSaveClick: function (button) {
		var form = button.up('form').getForm(), rec, phantom,
			 win = this.getView(), users;

		if (form.isValid()) {
			rec = this.getViewModel().getData().theUser;
			users = this.getViewModel().getData().users;
			phantom = rec.phantom;
			Ext.Msg.wait('Сохранение', 'Сохранение пользователя...');

			var extraParams = {};
			if (rec.phantom) {
				extraParams.password = form.getValues().password;
			}
			extraParams.merchantId = form.getValues().merchantId;
			rec.save({
				scope: this,
				params: extraParams,
				callback: function (record, operation, success) {
					Ext.Msg.hide();
					if (success) {
						if (users && phantom) {
							users.reload();
						}
						win.close();
						Ext.toast('Пользователь сохранен', 'Сохранение', 't')
					} else {
						var exception = Ext.decode(operation.getError().response.responseText),
							errors = Ext.isEmpty(exception) ? [] : exception.errors;
						Ext.toast('Неудалось сохранить пользователя  ', 'Ошибка', 't');
					}
				}
			});
		}
	}

});
