package asset.pipeline

class ResourceTagLib {
    static defaultEncodeAs = 'none'
    //static encodeAsForTags = [tagName: 'raw']
	
	static namespace = "r"
	
	def layoutResources = { attrs ->
		def modules = request.getAttribute("_resourcesRequire");
		
		if (modules) {
			modules.each {
				out << asset.stylesheet(src:it);
				out << asset.javascript(src:it);
			}
			
			request.setAttribute('_resourcesRequire', null); // don't process twice...
		}				
	}
	
	def require = {attrs ->
		def modules = attrs.modules?.split(",")?.collect {it.trim()};
		
		if (!modules)
			modules = [attrs.module];
    	
		if (request.getAttribute("_resourcesRequire")) {
			modules = request.getAttribute("_resourcesRequire").append(modules); // Join as ONE
		}
		
		request.setAttribute('_resourcesRequire', modules);
	}
}
