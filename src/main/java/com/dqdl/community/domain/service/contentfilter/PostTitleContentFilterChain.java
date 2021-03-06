/**
 * 
 */
package com.dqdl.community.domain.service.contentfilter;

import java.util.Iterator;
import java.util.Set;

import com.dqdl.community.domain.model.post.Post;

/**
 * @author DAOQIDELV
 * @CreateDate 2017年9月17日
 * 帖子标题内容过滤器 责任链。标题没有图片，故不涉及图片过滤
 */
public class PostTitleContentFilterChain {
	
	private Set<ContentFilter> contentFilters;
	
	public PostTitleContentFilterChain() {
		TextContentFilter localTextContentFilter = new LocalTextContentFilter();
		TextContentFilter remoteTextContentFilter = new RemoteTextContentFilter();
		contentFilters.add(localTextContentFilter); //优先校验本地的敏感词
		contentFilters.add(remoteTextContentFilter);
	}
	
	/**
	 * 过滤标题
	 * @param post
	 * @return
	 *  true —— 通过
	 *  false —— 未通过
	 */
	public boolean filtTitle(Post post) {
		for(Iterator<ContentFilter> it = contentFilters.iterator(); it.hasNext();) {
			if(!it.next().filtContent(post.getTitle())) {
				return false;
			}
		}
		return true;
	}

}
