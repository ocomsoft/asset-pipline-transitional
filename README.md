asset-pipline-transitional
==========================

A Grails plugin to aid the transition from the Resources Plugin to Asset Plugin. The idea is to help make the transition easier. For example we had projects using plugins which use the resources plugin to declare assets to use these plugins with an Asset Pipline only project will fail. So we needed to add these tags to keep it running. They are simple implementations to keep it simple. 

This plugin add the following "tags" from the resources plugin


r:require modules="comma,seperated,modules" and r:require module="amodule"
-------------------------------------------------------------------------------------

This will simply store the "modules" in the request object. For the layoutResources tag.


r:layoutResources
-----------------

This will use the "required" resources in the same way as the resources plugin. No duplication is checked. So you might end up using an asset more than once. 

When required is used it keeps a list of modules that are required, When this tag is called (usually in the layout) then the modules that where required are loaded. 

We use the asset:stylesheet and asset:javascript tags to include the resource.

This code assumes that you have rewritten the resources in the asset pipeline style for example

modules = {
	'scaffolding-base' { 
	  resource url: [ dir: 'js/scaffold', file: 'app.js']
	 }
}

Would be re-written as 
scaffolding-base.js

//= require scaffold/app


The layoutResources plugin will attempt to load BOTH javasript and stylesheet resources by the names specified..

