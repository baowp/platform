package com.abbcc.springrest.controller.user.site.piece;

import java.util.ArrayList;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcAlbum;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcNews;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcSupply;
import com.abbcc.models.AbcUser;
import com.abbcc.util.constant.ModelType;

@SuppressWarnings("serial")
public class DetailContentPiece<T> extends NarrowContentPiece<AbcUser> {

	protected AbcProduct product;
	protected AbcNews news;
	protected AbcSupply supply;
	protected AbcAlbum album;

	@RequestMapping(value = "/{username}/detail_product")
	public String pieceProductDetail(String itemId) {
		if (before() && (product = (AbcProduct) take("product")) == null) {
			// 取具体产品
			product = productService.findById(itemId);
			productPic(product);
			deposit("product", product);
		}
		return viewName("group/dynamic/piece/detail/detail_product");
	}

	@RequestMapping(value = "/{username}/detail_news")
	public String pieceNewsDetail(String itemId) {
		if (before() && (news = (AbcNews) take("news")) == null) {
			news = newsService.findById(itemId);
			deposit("news", news);
		}
		return viewName("group/dynamic/piece/detail/detail_news");
	}

	@RequestMapping(value = "/{username}/detail_supply")
	public String pieceSupplyDetail(String itemId) {
		if (before() && (supply = (AbcSupply) take("supply")) == null) {
			// 供求信息
			supply = supplyService.findById(itemId);
			deposit("supply", supply);
		}
		return viewName("group/dynamic/piece/detail/detail_supply");
	}

	@RequestMapping(value = "/{username}/detail_album")
	public String pieceAlbumDetail(String itemId) {
		if (before() && (album = (AbcAlbum) take("album")) == null) {
			album = albumService.findById(itemId);
			eachPic(itemId);
			deposit("album", album);
		}
		return viewName("group/dynamic/piece/detail/detail_album");
	}

	/**
	 * 取产品图
	 * 
	 * @param abcProducts
	 */
	protected void productPic(AbcProduct... abcProducts) {
		for (AbcProduct product : abcProducts) {
			AbcAttachment example = new AbcAttachment();
			example.setBelongId(product.getProductId());
			example.setBelongType(ModelType.BG);
			example.setType(CommonConst.ATTACHTYPEPIC);
			for (AbcAttachment attach : attachmentService
					.findByExample(example)) {
				if (CommonConst.ATTACHPICMAIN.equals(attach.getFiledesc()))
					product.setMainPic(attach);
				else {
					if (product.getAttachList() == null)
						product.setAttachList(new ArrayList<AbcAttachment>());
					product.getAttachList().add(attach);
				}
			}
		}
	}

	protected void eachPic(String albumId) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcAttachment.class);
		detachedCriteria.add(Restrictions.and(
				Restrictions.ne("state", CommonConst.STATEDEL),
				Restrictions.eq("belongId", albumId)));
		detachedCriteria.addOrder(Order.desc("uploadTime"));
		deposit("pictrues",
				attachmentService.findAllByCriteria(detachedCriteria));
	}

	public AbcProduct getProduct() {
		return product;
	}

	public void setProduct(AbcProduct product) {
		this.product = product;
	}

}
