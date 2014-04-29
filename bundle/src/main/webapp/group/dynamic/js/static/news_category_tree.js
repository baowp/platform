/**
 * 
 * @return
 */
function newsTreeInit() {
	buildNewsTree();
}
function buildNewsTree() {
	var tree = new YAHOO.widget.TreeView("newsCategory");
	var root = tree.getRoot();
	var list = newsRoots || [];

	for ( var i = 0, j = list.length; i < j; i++) {
		var tempNode = new YAHOO.widget.TextNode(list[i].name, root, false);
		tempNode.categoryId = list[i].categoryId;
		if (list[i].sonCate && list[i].sonCate.length)
			buildNewsBranch(tempNode, list[i].sonCate);
	}

	tree.subscribe("labelClick", function(node) {
		if (typeof window.loadProductsById == "function") {
			loadNewsById(node.categoryId);
			node.returns = 1;
		}
	});
	tree.subscribe("expand", function(node) {
		return returns(node);
	});
	tree.subscribe("collapse", function(node) {
		return returns(node);
	});

	function returns(node) {
		if (node.returns) {
			node.returns = 0;
			return false;
		}
	}
	tree.draw();
}

function buildNewsBranch(node,list) {
	for ( var i = 0, j = list.length; i < j; i++) {
		var tempNode = new YAHOO.widget.TextNode(list[i].name, node, false);
		tempNode.categoryId = list[i].categoryId;
		if (list[i].sonCate && list[i].sonCate.length)
			buildNewsBranch(tempNode, list[i].sonCate);
	}
}

YAHOO.util.Event.onDOMReady(newsTreeInit);