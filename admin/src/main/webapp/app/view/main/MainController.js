/**
 * This class is the main view for the application. It is specified in app.js as the
 * "autoCreateViewport" property. That setting automatically applies the "viewport"
 * plugin to promote that instance of this class to the body element.
 *
 * TODO - Replace this content of this view to suite the needs of your application.
 */
Ext.define('YesdoApp.view.main.MainController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.main',

    onClickUserName: function () {
        var data = this.getViewModel().getData();
        var win = new web.view.user.User({
            viewModel: {
                data: {
                    theUser: data.currentUser
                }
            }
        });

        win.show();
    },

    onClickLogout: function() {
        Ext.Ajax.request({
            url: 'logout',
            method: 'POST',
            scope: this,
            callback: function() {
                this.redirectTo('login');
            }
        });
    }
});
