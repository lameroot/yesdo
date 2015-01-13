Ext.define('YesdoApp.Format', {
	singleton: true,

	error: function (response) {
		if (!response) {
			return 'emptyResponse';
		}
		var date = response.errordate &&
			Ext.Date.format(new Date(response.errordate), 'd.m.Y H:i:s');

		return response.message;
	},

	center: function (value) {
		return '<table style="width: 99%; height: 99%; text-align: center; vertical-align: middle;"><tr><td>' + value + '</td></tr></table>';
	},

	required: function () {
		return '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';
	},

	currency: function (value) {
		return value == null || Ext.isEmpty(value) ? Ext.emptyString : Ext.util.Format.number(value, '0,0.00');
	},

	date: function(value) {
		return Ext.isEmpty(value) ? value :Ext.Date.format(new Date(value), Rbs.Format.Date.patterns.ISO8601Long);
	},

	/**
	 * Time formatting helper.
	 * Formats number of milliseconds to human-readable "elapsed" string
	 *(corresponding to current locale).
	 */
	elapsedStrict: function (ms) {
		var days, hours, minutes, string = '',
			MS_IN_MINUTE = 60 * 1000,
			MS_IN_HOUR = 60 * MS_IN_MINUTE,
			MS_IN_DAY = 24 * MS_IN_HOUR;

		if (ms >= MS_IN_DAY) {
			days = Math.floor(ms / MS_IN_DAY);
			ms -= days * MS_IN_DAY;
			string += days + 'd' + ' ';
		}

		if (ms >= MS_IN_HOUR) {
			hours = Math.floor(ms / MS_IN_HOUR);
			ms -= hours * MS_IN_HOUR;
			string += hours + 'h' + ' ';
		}

		if (ms >= MS_IN_MINUTE) {
			minutes = Math.floor(ms / MS_IN_MINUTE);
			ms -= minutes * MS_IN_MINUTE;
			string += minutes + 'm' + ' ';
		}

		if (ms >= 1000) {
			string += Math.floor(ms / 1000) + 's';
		}

		return string;
	},

	Date: {
		patterns: {
			ISO8601Long: "Y-m-d H:i:s",
			ISO8601Short: "Y-m-d",
			ShortDate: "n/j/Y",
			LongDate: "l, F d, Y",
			FullDateTime: "l, F d, Y g:i:s A",
			MonthDay: "F d",
			ShortTime: "g:i A",
			LongTime: "g:i:s A",
			SortableDateTime: "Y-m-d\\TH:i:s",
			UniversalSortableDateTime: "Y-m-d H:i:sO",
			YearMonth: "F, Y"
		}}
});