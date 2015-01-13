window.provide = function (name, exist) {
	var ns = (typeof name == "string" || name instanceof String) ? name.split('.') : [], i, g = this;
	for (i = 0; i < ns.length; i++) {
		if (!g[ns[i]]) {
			if (exist) return g[ns[i]];
			g[ns[i]] = {}
		}
		g = g[ns[i]]
	}
	return g
};