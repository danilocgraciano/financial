(function() {
    var scripts = document.getElementsByTagName('script'),
        localhostTests = [
            /^localhost$/,
            /\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(:\d{1,5})?\b/ // IP v4
        ],
        host = window.location.hostname,
        isDevelopment = null,
        queryString = window.location.search,
        test, path, i, ln, scriptSrc, match;

    for (i = 0, ln = scripts.length; i < ln; i++) {
        scriptSrc = scripts[i].src;

        match = scriptSrc.match(/setup\.js$/);

        if (match) {
            path = scriptSrc.substring(0, scriptSrc.length - match[0].length);
            break;
        }
    }

    if (isDevelopment === null) {
        for (i = 0, ln = localhostTests.length; i < ln; i++) {
            test = localhostTests[i];

            if (host.search(test) !== -1) {
                isDevelopment = true;
                break;
            }
        }
    }

    if (isDevelopment === null && window.location.protocol === 'file:') {
        isDevelopment = true;
    }
    
    var pathCSS  = path.replace("/js/", "/css/"); 

    document.write('<script type="text/javascript" charset="UTF-8" src="' + path + 'angular' + (!isDevelopment ? '.min' : '') + '.js"></script>');
    document.write('<script type="text/javascript" charset="UTF-8" src="' + path + 'angular-resource' + (!isDevelopment ? '.min' : '') + '.js"></script>');
    document.write('<script type="text/javascript" charset="UTF-8" src="' + path + 'angular-route' + (!isDevelopment ? '.min' : '') + '.js"></script>');
    document.write('<script type="text/javascript" charset="UTF-8" src="' + path + 'angular-locale_pt-br' + (!isDevelopment ? '.min' : '') + '.js"></script>');
    document.write('<script type="text/javascript" charset="UTF-8" src="' + path + 'ui-bootstrap-tpls-1.3.2' + (!isDevelopment ? '.min' : '') + '.js"></script>');
    document.write('<script type="text/javascript" charset="UTF-8" src="' + path + 'jquery' + (!isDevelopment ? '.min' : '') + '.js"></script>');
    document.write('<link rel="stylesheet" href="' + pathCSS + 'bootstrap' + (!isDevelopment ? '.min' : '') + '.css">');
    document.write('<script type="text/javascript" charset="UTF-8" src="' + path + 'bootstrap' + (!isDevelopment ? '.min' : '') + '.js"></script>');

    
})();
