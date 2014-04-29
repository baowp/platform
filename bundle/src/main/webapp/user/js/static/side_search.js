$("#searchBtnSide").click(
		function() {
			location = "/search-"
					+ $("#keywords_text_1").val().replace(/,/g, '') + ","
					+ $("#priceLowSide").val().replace(/,/g, '') + ","
					+ $("#priceHighSide").val().replace(/,/g, '') + ".html";
			return false;
		})