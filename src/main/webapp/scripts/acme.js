/* acme.js
 *
 * Copyright (C) 2013 Universidad de Sevilla
 *  
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

function redirect(url) {
	
	var base, destination;
	
	base = document.getElementsByTagName('base');
	destination = url;
	if (destination.charAt(0) != '/' && destination.substring(0, 5) != "http:" && base[0] && base[0].href) {
		if (base[0].href.charAt(base[0].href.length - 1) == '/' )
			destination = base[0].href + destination;
		else 
			destination = base[0].href + "/" + destination;
	}
	
	window.location.replace(destination);
}

Object.keys = (function () {
    var hasOwnProperty = Object.prototype.hasOwnProperty,
        hasDontEnumBug = !{toString:null}.propertyIsEnumerable("toString"),
        DontEnums = [ 
            'toString', 'toLocaleString', 'valueOf', 'hasOwnProperty',
            'isPrototypeOf', 'propertyIsEnumerable', 'constructor'
        ],
        DontEnumsLength = DontEnums.length;

    return function (obj) {
        if (typeof obj != "object" && typeof obj != "function" || obj === null)
            throw new TypeError("Object.keys called on a non-object");

        var result = [];
        result.push("type=" + typeof(obj));
        for (var name in obj) {        	
            if (hasOwnProperty.call(obj, name))
                result.push(name);
        }

        if (hasDontEnumBug) {
            for (var i = 0; i < DontEnumsLength; i++) {            	
                if (hasOwnProperty.call(obj, DontEnums[i]))
                    result.push(DontEnums[i]);
            }   
        }

        return result;
    };
})();