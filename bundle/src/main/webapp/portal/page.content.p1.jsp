<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="news_001" class="module normala">
	<div class="news001_left mr newsBox">
		<div class="news001_left_title">
			<div class="title_right">
	              <a href="#"><b>全国电子新闻中心</b></a>
	        </div>
		</div>
		<div class="news001_left_content2">
			<div class="news001_left_content1">
                	<div class="news_jiaodian">
                    	<a target="_blank" href="http://news.eccn.com/news_2010030408055357.htm"><img alt="" src="http://news.eccn.com/images/common/no_pic.gif"></a>
                    </div>
                    <div class="jiaodian_title">
                        	<span>
                            	<a target="_blank" class="channel" href="http://news.eccn.com/news_2010030408055357.htm"><b>集成电路设计要打造产业经济链</b></a>
                            </span>
                            <div class="jiaodian_descripts hui"><a target="_blank" href="http://news.eccn.com/news_2010030408055357.htm">我国集成电路（IC）设计业是一个根植于本土的产业，是一个拥有庞大市场需求的产业。在...</a> 		
                            </div>
                    </div>
                </div>
			<ul>       
			<s:iterator value="#request['news']">
				<li class="one_row"><a target="_blank" href="#">${title }</a></li>
            </s:iterator> 
            </ul>
         </div>
	</div>
	<div class="news001_left mr supplyBox">
		<div class="news001_left_title">
			<div class="title_right">
	              <a href="#"><b>全国供求中心</b></a>
	        </div>
		</div>
		<div class="news001_left_content2">
			<div>
				<div style="float: left;margin: 2px 5px; text-align: center;"><a href="http://detail.china.alibaba.com/buyer/offerdetail/615000052.html?tracelog=chinaindex_recom_b2" title="四通铃木遥控车玩具 明轮带灯 J668D" class="u5"><div><img class="img2" border="0" style="border:1px solid #D4D4D4;" src="http://img.china.alibaba.com/img/offer2/2010/000/052/615000052_f32ab75cfc89befb9a645c0c3633a1dc.summ.jpg"></div><div><span>四通铃木遥控车</span></div></a></div>
				<div style="float: left;margin: 2px 5px; text-align: center;"><a href="http://detail.china.alibaba.com/buyer/offerdetail/531455147.html?tracelog=chinaindex_recom_b2" title="供应出口韩国化妆包(图)" class="u5"><div><img class="img2" border="0" style="border:1px solid #D4D4D4;" src="http://img.china.alibaba.com/img/offer/53/14/55/14/7/531455147.summ.jpg"></div><div><span>供应韩国化妆包</span></div></a></div>
			</div>
			<ul>       
			<s:iterator value="#request['supplies']">
				<li class="one_row"><a target="_blank" href="<s:url value="/vip/%{username}/supply_detail?supplyId=%{supplyId}"/>">[<s:property value="type"/>]${title }</a></li>
            </s:iterator> 
            </ul>
         </div>
	</div>	
	<div class="news001_left productBox">
		<div class="news001_left_title">
			<div class="title_right">
	              <a href="#"><b>全国产品中心</b></a>
	        </div>
		</div>
		<div class="news001_left_content2">
			<div class="tab_falsh">
                <iframe scrolling="no" height="75" frameborder="0" width="280" marginheight="0" marginwidth="0" src="http://www.eccn.com/uploads/gg/2010010214081246.htm"></iframe>
            </div>
			<ul>       
			<s:iterator value="#request['products']">
				<li class="one_row"><a target="_blank" href="<s:url value="/vip/%{username}/product_detail?productId=%{productId}"/>">${name}</a></li>
            </s:iterator> 
            </ul>
         </div>
	</div>
</div>

