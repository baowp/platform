/**
 * 
 * @return
 */
function treeInit() {
	buildTree();
}
function loadNodeData(node, fnLoadComplete) {

	// prepare URL for XHR request:
	var sUrl = contextRoot + "/site/*/fetchCategoryChildren?categoryId="
			+ node.categoryId;

	// prepare our callback object
	var callback = {

		// if our XHR call is successful, we want to make use
		// of the returned data and create child nodes.
		success : function(oResponse) {
			YAHOO.log("XHR transaction was successful.", "info", "example");
			// YAHOO.log(oResponse.responseText);
			var oResults = eval("(" + oResponse.responseText + ")");
			var list = oResults.categoryList
			if (list) {
				// Result is an array if more than one result, string otherwise
				if (YAHOO.lang.isArray(list)) {
					for ( var i = 0, j = list.length; i < j; i++) {
						var tempNode = new YAHOO.widget.TextNode(list[i].image2
								|| list[i].name, node, false);
						tempNode.categoryId = list[i].categoryId;
						tempNode.isLeaf = list[i].ifLeaf;
					}
				} else {
				}
			}
			oResponse.argument.fnLoadComplete();
		},

		// if our XHR call is not successful, we want to
		// fire the TreeView callback and let the Tree
		// proceed with its business.
		failure : function(oResponse) {
			YAHOO.log("Failed to process XHR transaction.", "info", "example");
			oResponse.argument.fnLoadComplete();
		},

		// our handlers for the XHR response will need the same
		// argument information we got to loadNodeData, so
		// we'll pass those along:
		argument : {
			"node" : node,
			"fnLoadComplete" : fnLoadComplete
		},

		// timeout -- if more than 7 seconds go by, we'll abort
		// the transaction and assume there are no children:
		timeout : 7000
	};

	// With our callback object ready, it's now time to
	// make our XHR call using Connection Manager's
	// asyncRequest method:
	YAHOO.util.Connect.asyncRequest('GET', sUrl, callback);
}

function buildTree() {
	// create a new tree:
	tree = new YAHOO.widget.TreeView("categoryTree");

	// turn dynamic loading on for entire tree:
	tree.setDynamicLoad(loadNodeData, 1);

	// get root node for tree:
	var root = tree.getRoot();

	// add child nodes for tree; our top level nodes are
	// all the states in India:
	var list = categoryList || [];

	for ( var i = 0, j = list.length; i < j; i++) {
		var tempNode = new YAHOO.widget.TextNode(list[i].image || list[i].name,
				root, false);
		tempNode.categoryId = list[i].id;
		tempNode.isLeaf = list[i].ifLeaf;
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
	// var tempNode = new YAHOO.widget.TextNode('This is a leaf node', root,
	// false);
	// tempNode.isLeaf = true;

	// render tree with these toplevel nodes; all descendants of these nodes
	// will be generated as needed by the dynamic loader.
	tree.draw();
}
// Function creates the tree
function buildTree2() {

	// instantiate the tree:
	tree = new YAHOO.widget.TreeView("treeDiv");

	for ( var i = 0; i < Math.floor((Math.random() * 4) + 3); i++) {
		var tmpNode = new YAHOO.widget.TextNode("label-" + i, tree.getRoot(),
				false);
		// tmpNode.collapse();
		// tmpNode.expand();
		// buildRandomTextBranch(tmpNode);
		buildLargeBranch(tmpNode);
	}

	// Expand and collapse happen prior to the actual expand/collapse,
	// and can be used to cancel the operation
	tree.subscribe("expand", function(node) {
		YAHOO.log(node.index + " was expanded", "info", "example");
		// if (node.returns) {
		// node.returns = 0;
		// return false;
		// }
		// return false; // return false to cancel the expand
	});

	tree.subscribe("collapse", function(node) {
		YAHOO.log(node.index + " was collapsed", "info", "example");
	});

	// Trees with TextNodes will fire an event for when the label is clicked:
	tree.subscribe("labelClick", function(node) {
		YAHOO.log(node.index + " label was clicked", "info", "example");
		// node.returns = 1;
	});

	// The tree is not created in the DOM until this method is called:
	tree.draw();
}

// function builds 10 children for the node you pass in:
function buildLargeBranch(node) {
	if (node.depth < 10) {
		YAHOO.log("buildRandomTextBranch: " + node.index, "info", "example");
		for ( var i = 0; i < 10; i++) {
			new YAHOO.widget.TextNode(node.label + "-" + i, node, false);
		}
	}
}

// Add an onDOMReady handler to build the tree when the document is ready
YAHOO.util.Event.onDOMReady(treeInit);