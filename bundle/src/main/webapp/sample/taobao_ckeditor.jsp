<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="http://assets.taobaocdn.com/tbsp/reset-grids.css?t=20090602.css" rel="stylesheet"/>
<!--[if lt IE 8]>
<link href="http://a.tbcdn.cn/s/kissy/1.1.5/editor/theme/cool/editor-pkg-min-mhtml.css" rel="stylesheet"/>
<![endif]-->
<!--[if gte IE 8]><!-->
<link href="http://a.tbcdn.cn/s/kissy/1.1.5/editor/theme/cool/editor-pkg-min-datauri.css" rel="stylesheet"/>
<!--<![endif]-->

<script src="http://a.tbcdn.cn/s/kissy/1.1.5/kissy-min.js"></script>
<script src="http://a.tbcdn.cn/s/kissy/1.1.5/editor/editor-all-pkg-min.js?t=20100827a"></script>
<script src="http://a.tbcdn.cn/s/kissy/1.1.5/editor/biz/bangpai/editor-plugin-pkg-min.js"></script>
<script src="editor.js"></script>
<script src="suggest.js"></script>
<style>
    #page { padding: 50px 50px 300px; width: 750px; margin: 0 auto; }
    h1, h2, h3 { margin: 1em 0 0.3em; }
    .section { margin-bottom: 50px; }
    .section ol { margin: 5px 20px; }
    .section ol li { list-style: decimal inside; margin: 5px 0; }
    .search-input { width: 300px; height: 20px; padding: 3px 2px 2px 4px; }
    .search-submit { padding: 4px 10px; margin-left: 5px; }
    input.g-submit { padding: 2px 8px; margin-left: 5px; }
    html { overflow-y: scroll; }
</style>
</head>
<body>
        <form name="search" method="get" action="http://search1.taobao.com/browse/search_auction.htm">

            <input name="name" id="q" class="search-input"/>

            <button type="submit" class="search-submit">淘我喜欢</button>
        </form>
                <script>
            KISSY.ready(function(S) {

                var dataUrl = '/admin/byNameSearchProduct';
                var sug = new S.Suggest('#q', dataUrl, {
                    containerCls: 'youa-suggest-container',
                    charset: 'utf-8',
                    callbackFn: 'data',
                    queryName: 'term'
                });
                //g_ks_suggest_callback({"result": [["nike 正品", "2170000"], ["nike 专柜 正品", "834000"], ["nike 短袖", "242000"], ["nike 板 鞋", "989000"], ["nike 女鞋", "253000"], ["nike 运动鞋", "550000"], ["nike 包", "295000"], ["nike 鞋", "3160000"], ["nike 单肩包", "38500"], ["nike 09", "786000"]]})
                sug.on('dataReturn', function() {
                    this.returnedData = this.returnedData.s || [];
                });
            });
        </script>
<textarea id="editor" tabindex="1" style="width:100%;height:400px;margin:0 auto;">
</textarea>

</body>
</html>

