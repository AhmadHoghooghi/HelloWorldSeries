/**
 *	to add project-bound dependencies copy this file to your project (to src/main/webapp/angular) and :
 *  - replace '${appNameToLower}' with your app name 
 *  - add custom modules,libraries, ... like this:
 *		angular.module(appName+ '.common.module', [[
 *			'/myapp/angular/my-module.js',
 *			'/myapp/angular/my-style.css'
 *		]]);
 */
'use strict';
(function () {
  'use strict';
  var appName = '${appNameToLower}';
  angular.module(appName + '.common.module', [[]])
  	.constant(appName + '.configs', {defaultSuggestPageSize:5});
})();