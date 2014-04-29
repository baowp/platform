/**
 * 
 * @return
 */
function treeInit() {
	buildTree();
}
function buildTree() {
	tree = new YAHOO.widget.TreeView("categoryTree");
	var root = tree.getRoot();
	var list = categoryList || [];

	for ( var i = 0, j = list.length; i < j; i++) {
		var tempNode = new YAHOO.widget.TextNode(
				list[i].image2 || list[i].name, root, false);
		tempNode.categoryId = list[i].categoryId;
		if (list[i].sonCate && list[i].sonCate.length)
			buildBranch(tempNode, list[i].sonCate);
	}

	tree.subscribe("labelClick", function(node) {
		if (typeof window.loadProductsById == "function") {
			loadProductsById(node.categoryId);
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
function buildBranch(node, list) {
	for ( var i = 0, j = list.length; i < j; i++) {
		var tempNode = new YAHOO.widget.TextNode(
				list[i].image2 || list[i].name, node, false);
		tempNode.categoryId = list[i].categoryId;
		if (list[i].sonCate.length)
			buildBranch(tempNode, list[i].sonCate);
	}
}

YAHOO.util.Event.onDOMReady(treeInit);