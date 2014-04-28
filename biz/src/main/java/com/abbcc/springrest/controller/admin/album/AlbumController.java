package com.abbcc.springrest.controller.admin.album;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

//import magick.MagickException;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.helper.ImageMagickHelper;
import com.abbcc.models.AbcAlbum;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcUser;
import com.abbcc.models.AbcWatermark;
import com.abbcc.service.AlbumService;
import com.abbcc.service.AttachmentService;
import com.abbcc.service.UserService;
import com.abbcc.springrest.controller.BaseController;
import com.abbcc.util.constant.ModelType;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@RequestMapping("/admin/album")
public class AlbumController extends BaseController<AbcUser> {

	@Autowired
	private UserService userService;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private AlbumService albumService;

	@RequestMapping(value = "/fillPage")
	public String fillPage() {
		return "admin/album/fill";
	}

	/**
	 * 补全用户信息图片
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/fill")
	public String fill(@ModelAttribute("model") @Valid AbcUser user,
			BindingResult result) {
		if (!hasFieldError(result, "username")) { // username 不为空
			AbcUser tempUser = new AbcUser();
			tempUser.setUsername(user.getUsername());
			List<AbcUser> list = userService.findByExample(tempUser);
			if (list.size() > 0) {
				String enterpriseId = list.get(0).getEnterpriseId();
				List<AbcAlbum> albumList = this.getAlbum(enterpriseId);
				List<String> ids = new ArrayList<String>();
				for (AbcAlbum a : albumList) {
					ids.add(a.getAlbumId());
				}
				DetachedCriteria dc = DetachedCriteria.forClass(AbcAttachment.class);
					dc.add(Restrictions.eq("belongType", ModelType.AD));
					dc.add(Restrictions.in("belongId", ids));
				List<AbcAttachment> attList = attachmentService.findAllByCriteria(dc);
				try {
					this.createImage(attList);
				} catch (Exception e) {
					log.info(e);
					this.result = "补全用户图片失败" + e.toString();
				}
				this.result = "补全用户图片成功!";
			} else {
				this.result = "该用户不存在!";
			}
		}
		return "admin/album/fill";
	}

	/**
	 * 获得企业相册
	 * 
	 * @param enterpriseId
	 *            企业编号
	 * @return
	 */
	private List<AbcAlbum> getAlbum(String enterpriseId) {
		List<AbcAlbum> list = new ArrayList<AbcAlbum>();
		AbcAlbum album = new AbcAlbum();
		album.setBelongId(enterpriseId);
		list = albumService.findByExample(album);
		return list;
	}

	/**
	 * 补全小图片
	 * 
	 * @param list
	 * @throws Exception
	 * @throws MagickException
	 */
	private void createImage(List<AbcAttachment> list) throws Exception {
		String dstDir = ConfConst.FILE_UPLOAD_DIR;
		File file;
		String mainPicPath;
		String picName;
		for (AbcAttachment a : list) {
			mainPicPath = dstDir + a.getServerPath();
			file = new File(mainPicPath);
			if (file.exists()) {
				picName = file.getName(); // 获得图片文件的后缀名与文件前缀
				String postfix = picName.substring(picName.lastIndexOf(".") + 1);
				String prefix = picName.substring(0, picName.lastIndexOf("."));
				String path = mainPicPath.replace(picName, "");		// 路径
				String imgPath7 = path + prefix + "_7." + postfix;
				String imgPath8 = path + prefix + "_8." + postfix;
				file = new File(imgPath7);
				if (!file.exists()) { // 图片文件不存在创建文件
					ImageMagickHelper.createThumbnailByCMD(mainPicPath,
							imgPath7, CommonConst.THUMBNAIL_BIG2,null);
				}
				file = new File(imgPath8);
				if (!file.exists()) {
					ImageMagickHelper.createThumbnailByCMD(mainPicPath,
							imgPath8, CommonConst.THUMBNAIL_SMALL2,null);
				}
			}
		}
	}

}
