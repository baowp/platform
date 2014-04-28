package com.abbcc.news.action.admin;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcComment;
import com.abbcc.news.models.NewsNews;
import com.abbcc.service.CommentService;
import com.abbcc.util.StringUtil;

public class CommentsAction extends BaseAction<AbcComment> {
	private CommentService commentService;
	public String newsId;
	public String commentIds;

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public String list() {
		DetachedCriteria dc = DetachedCriteria.forClass(entity.getClass());
		DetachedCriteria nc = DetachedCriteria.forClass(NewsNews.class);
		if (StringUtil.isNotBlank(entity.getTitle())) {
			nc.add(Restrictions.like("title", entity.getTitle(),
					MatchMode.ANYWHERE)).setProjection(
					Property.forName("newsId"));
			List<String> nIdList = baseService.findAllByCriteria(nc);
			dc.add(Restrictions.in("belongId", nIdList));
		}
		dc.add(Restrictions.eq("belongType", CommonConst.NEWSTYPEUSER));
		dc.addOrder(Order.desc("addTime"));
		pageList = commentService.findPageByCriteria(dc, pageSize, startIndex);
		return LIST;
	}

	public String remove() {
		commentService.delete(entity);
		newsId = entity.getBelongId();
		return SUCCESS;
	}

	public String removeAll() {
		try {
			String[] com = StringUtil.splitBySemicolon(commentIds);
			for (String cId : com) {
				String[] commentIds = StringUtil.splitBySemicolon(cId);
				for (String c : commentIds) {
					AbcComment ac = (AbcComment) baseService.findById(
							AbcComment.class, c);
					if (ac != null)
						baseService.delete(ac);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

}
