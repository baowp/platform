/**
 * 
 * @return
 */
function newsTreeInit() {
	buildNewsTree();
}
function loadNewsNodeData(node, fnLoadComplete) {

	// prepare URL for XHR request:
	var sUrl = contextRoot + "/site/*/fetchCategoryChildren2?categoryId="
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
						var tempNode = new YAHOO.widget.TextNode(list[i].name,
								node, false);
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

function buildNewsTree() {
	// create a new tree:
	var tree = new YAHOO.widget.TreeView("newsCategory");

	// turn dynamic loading on for entire tree:
	tree.setDynamicLoad(loadNewsNodeData, 1);

	// get root node for tree:
	var root = tree.getRoot();

	// add child nodes for tree; our top level nodes are
	// all the states in India:
	var list = newsRoots || [];

	for ( var i = 0, j = list.length; i < j; i++) {
		var tempNode = new YAHOO.widget.TextNode(list[i].name, root, false);
		tempNode.categoryId = list[i].id;
		tempNode.isLeaf = list[i].ifLeaf;
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
	// var tempNode = new YAHOO.widget.TextNode('This is a leaf node', root,
	// false);
	// tempNode.isLeaf = true;

	// render tree with these toplevel nodes; all descendants of these nodes
	// will be generated as needed by the dynamic loader.
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
YAHOO.util.Event.onDOMReady(newsTreeInit);